package by.academy.repository.springdata;

import by.academy.domain.Gender;
import by.academy.domain.entity.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface UserSpringDataRepository extends
        CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long> {
    List<HibernateUser> findByIdAndGender(Long id, Gender gender);

    List<HibernateUser> findByCredentialsLogin(String login);

    @Query(value = "select * from training_records_schema.users where gender = :gender", nativeQuery = true)
    List<HibernateUser> findUserByGender(String gender);

//    @Query(value = "select u from HibernateUser u")
//    List<HibernateUser> findByHQLQuery();
//
//    @Query(value = "select * from carshop.users", nativeQuery = true)
//    List<HibernateUser> findByHQLQueryNative();
//
//    @Query(value = "select u from HibernateUser u where u.userLogin = :login and u.userName = :userName")
//    List<HibernateUser> findByHQLQuery(String login, @Param("userName") String name);
//
//    @Query("select u.id, u.userName from HibernateUser u")
//    List<Object[]> getPartsOfUser();

    @Modifying
    @Query(value = "insert into training_records_schema.l_role_user(user_id, role_id) values (:user_id, :role_id)", nativeQuery = true)
    int createRoleRow(@Param("user_id") Long userId, @Param("role_id") Long roleId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update training_records_schema.users set " +
            "user_name = :user_name, " +
            "sur_name = :sur_name, " +
            "birth_date= :birth_date, " +
            "user_login = :user_login, " +
            "user_password = :user_password, " +
            "modification_date = :modification_date, " +
            "gender = :gender " +
            "where id = :id", nativeQuery = true)
    void updateUserSuccess(@Param("user_name") String userName,
                           @Param("sur_name") String surName,
                           @Param("birth_date") Timestamp birthDate,
                           @Param("user_login") String login,
                           @Param("user_password") String password,
                           @Param("modification_date") Timestamp modificationDate,
                           @Param("gender") String gender,
                           @Param("id") Long userId);

}
