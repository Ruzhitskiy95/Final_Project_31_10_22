package by.academy.repository.jdbctemplate;

//import by.academy.aop.CustomAspect;
import by.academy.domain.User;
import by.academy.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Primary

public class JdbcTemplateUserRepository implements UserRepositoryInterface {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    public static final org.apache.log4j.Logger log = Logger.getLogger(UserRowMapper.class);

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from training_records_schema.users where id = " + id, userRowMapper);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
//        return jdbcTemplate.query("select * from test_schema.table_name ",
//                userRowMapper);
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return  jdbcTemplate.query("select * from training_records_schema.users" +
                " order by id limit "  + limit + " offset " + offset, userRowMapper);
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into training_records_schema.users (user_name, sur_name, birth_date, is_deleted, user_login, user_password) " +
                        " values (:userName, :surName, :birthDate, :isDeleted, :login, :password);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userName", object.getUserName());
        mapSqlParameterSource.addValue("surName", object.getSurName());
        mapSqlParameterSource.addValue("birthDate", object.getBirthDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());
        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('training_records_schema.users_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public User update(User object) {
        final String updateQuery =
                "update training_records_schema.users set user_name = :userName, sur_name = :surName," +
                        " birth_date = :birthDate, is_deleted = :isDeleted where id = :id;";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userName", object.getUserName());
        mapSqlParameterSource.addValue("surName", object.getSurName());
        mapSqlParameterSource.addValue("birthDate", object.getBirthDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("id", object.getId());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {

        jdbcTemplate.update("delete from training_records_schema.users" +
                " where id = " + id);
        return id;
    }


//    @Override
//    public List<User> findNameSurname(String K) {
//        return null;
//    }
//
//    @Override
//    public List<User> findNameSurnameParam(String K) {
//        return null;
//    }

    @Override
    public Map<String, String> getUserStats() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {

        final String searchByLoginQuery = "select * from training_records_schema.users where user_login = :login";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login", login);

        return Optional.of(namedParameterJdbcTemplate.queryForObject(searchByLoginQuery, mapSqlParameterSource, userRowMapper));
    }
}
