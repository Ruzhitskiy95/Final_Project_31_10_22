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
    public Optional<User> findByLogin(String login) {

        final String searchByLoginQuery = "select * from training_records_schema.users where user_login = :login";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login", login);

        return Optional.of(namedParameterJdbcTemplate.queryForObject(searchByLoginQuery, mapSqlParameterSource, userRowMapper));
    }
}
