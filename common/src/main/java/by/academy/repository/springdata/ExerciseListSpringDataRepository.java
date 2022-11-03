package by.academy.repository.springdata;

import by.academy.domain.entity.HibernateExerciseList;
import by.academy.domain.entity.HibernateRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseListSpringDataRepository extends JpaRepository<HibernateExerciseList, Long> {

    @Cacheable("exercise_list")
    @Query(value = "select e from HibernateExerciseList e")
    List<HibernateExerciseList> findAllCustom();

}