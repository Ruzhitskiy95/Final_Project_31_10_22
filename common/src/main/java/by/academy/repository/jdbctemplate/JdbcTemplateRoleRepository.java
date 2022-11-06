package by.academy.repository.jdbctemplate;


import by.academy.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateRoleRepository implements RoleRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final RoleRowMapper rowMapper;

    @Override
    public Role findById(Long id) {
        return jdbcTemplate.queryForObject("select * from training_records_schema.roles where id = " + id, rowMapper);
    }


    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return jdbcTemplate.query(
                "select * from training_records_schema.roles " +
                        " inner join training_records_schema.l_role_user lru on roles.id = lru.role_id " +
                        " where lru.user_id = " + userId,
                rowMapper);
    }
}
