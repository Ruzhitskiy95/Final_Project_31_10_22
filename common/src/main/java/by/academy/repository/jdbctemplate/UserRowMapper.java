package by.academy.repository.jdbctemplate;

import by.academy.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.academy.repository.colums.UserTableColumn.*;

@Component
public class UserRowMapper implements RowMapper<User> {

    public static final Logger log = Logger.getLogger(UserRowMapper.class);

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        log.info("UserRowMapping start");

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurName(rs.getString(SURNAME));
        user.setBirthDate(rs.getTimestamp(BIRTH_DATE));
        user.setIsDeleted(rs.getBoolean(MYDELETED));
        user.setLogin(rs.getString(LOGIN));
        user.setPassword(rs.getString(PASSWORD));
        user.setCreationDate(rs.getTimestamp(CREATION_DATE));
        user.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));


        log.info("UserRowMapping finish");

        return user;
    }
}
