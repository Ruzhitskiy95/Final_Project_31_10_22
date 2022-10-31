package by.academy.repository.jdbctemplate;

import by.academy.domain.Role;
import by.academy.repository.CRUDRepository;

import java.util.List;

public interface RoleRepositoryInterface extends CRUDRepository<Long, Role> {
    List<Role> findRolesByUserId(Long userId);
}
