package by.academy.repository;


import by.academy.domain.User;
import by.academy.exeption.NoSuchEntityException;
import by.academy.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.academy.repository.colums.UserTableColumn.*;
import static by.academy.util.UUIDGenerator.generateUUID;

@Repository
//@Primary
@RequiredArgsConstructor
//@AllArgsConstructor
public class UserRepository implements UserRepositoryInterface{



    @Override
    public User findById(Long id) {
        final String findByIdQuery = "select * from training_records_schema.users where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return userRowMapping(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404, UUIDGenerator.generateUUID());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    private User userRowMapping(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurName(rs.getString(SURNAME));
        user.setBirthDate(rs.getTimestamp(BIRTH_DATE));
        user.setIsDeleted(rs.getBoolean(MYDELETED));

        return user;

/*        return User.builder()
                .id(rs.getLong(ID))
                .userName(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .birth(rs.getTimestamp(BIRTH_DATE))
                .creationDate(rs.getTimestamp(CREATED))
                .modificationDate(rs.getTimestamp(CHANGED))
                .weight(rs.getDouble(WEIGHT))
                .build();*/
    }


    public List<User> findAll(){
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);

    }

    private Connection getConnection() throws SQLException {
        try {
            String driver = "databaseProperties.getDriverName()";

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = "databaseProperties.getUrl()";
        String login = "databaseProperties.getLogin()";
        String password = "databaseProperties.getPassword()";

        String jdbcURL = StringUtils.join(url, login, password);

        return DriverManager.getConnection(jdbcURL, login, password);
    }

    @Override
    public List<User> findAll(int limit, int offset) {

        final String findAllQuery = "select * from training_records_schema.users" +
                " order by id limit "  + limit + "offset " + offset;
        List<User> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {

//                User user = new User();
//                user.setId(rs.getLong(ID));
//                user.setUser_name(rs.getString(NAME));
//                user.setSur_name(rs.getString(SURNAME));
//                user.setBirth(rs.getTimestamp(BIRTH_DATE));
//                user.set_deleted(user.is_deleted());

                result.add(userRowMapping(rs));

            }
            return result;

        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL ISSUES!");
        }
    }

    @Override
    public User create(User object) {

        final String insertQuery = "insert into training_records_schema.users (user_name," +
                "sur_name, birth_date, is_deleted) values (?, ?, ?, ?);";
            Connection connection;
            PreparedStatement statement;

            try {
                connection = getConnection();
                statement = connection.prepareStatement(insertQuery);
                statement.setString(1, object.getUserName());
                statement.setString(2, object.getSurName());
                statement.setTimestamp(3, object.getBirthDate());
                statement.setBoolean(4, object.getIsDeleted());

                statement.executeUpdate();
                ResultSet resultSet = connection.prepareStatement("select currval" +
                        "('training_records_schema.users_id_seq') as last_id").executeQuery();
                resultSet.next();

                long userLastInsertId = resultSet.getLong("last_id");
                return findById(userLastInsertId);

            }catch (SQLException e){
                System.err.println(e.getMessage());
                throw new RuntimeException("SQL ISSUES!");
            }
    }

    @Override
    public User update(User object) {
        final String updateQuery = "update training_records_schema.users set user_name = ?" +
                ", sur_name =?" +
                ", birth_date = ?" +
                ", is_deleted = ? where id =?;";
        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(updateQuery);
            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurName());
            statement.setTimestamp(3, object.getBirthDate());
            statement.setBoolean(4, object.getIsDeleted());
            statement.setLong(5,object.getId());

            statement.executeUpdate();
            return findById(object.getId());
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL ISSUES!");
        }
    }

    @Override
    public Long delete(Long id) {
        final String deleteQuery = "delete from training_records_schema.users" +
                " where id =?;";
        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1,id);

            statement.executeUpdate();
            return id;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL ISSUES!");
        }
    }

//    @Override
//    public List<User> findNameSurname(String search) {
//            final String findQuery =
//                    "select * from training_records_schema.users where (user_name like '%"
//                            + search + "%' or sur_name like '%"+ search+"%')";
//
//            List<User> resultsearch = new ArrayList<>();
//
//            Connection connection;
//            Statement statement;
//            ResultSet rs;
//
//            try {
//                connection = getConnection();
//                statement = connection.createStatement();
//                rs = statement.executeQuery(findQuery);
//
//                while (rs.next()) {
//                    resultsearch.add(userRowMapping(rs));
//                }
//                return resultsearch;
//
//            } catch (SQLException e) {
//                System.err.println(e.getMessage());
//                throw new RuntimeException("SQL Issues!");
//            }
//    }
//
//    @Override
//    public List<User> findNameSurnameParam(String search) {
//        final String findQuery =
//                "select * from training_records_schema.users where" +
//                        " (user_name like '%?' or sur_name like '%?%')";
//
//        List<User> resultSearch = new ArrayList<>();
//
//        Connection connection;
//        PreparedStatement statement;
//        ResultSet rs;
//
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(findQuery);
//
//            statement.setString(1, search);
//            statement.setString(2, search);
//            rs = statement.executeQuery(findQuery);
//
//            while (rs.next()) {
//                resultSearch.add(userRowMapping(rs));
//            }
//            return resultSearch;
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException("SQL Issues!");
//        }
//    }

    @Override
    public Map<String, String> getUserStats() {
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }
}
