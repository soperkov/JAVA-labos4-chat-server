package hr.vsite.java.chatserver.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unused")
    public UserRepositoryCustomImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class Mapper implements RowMapper<User> {
        @Override
        public User mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
            User user = new User();
            user.setUserName(rs.getString("user_name"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            return user;
        }
    }

    @Override
    public List<User> search(String userName) {
        String SQL = "SELECT * FROM users WHERE upper(user_name) LIKE ?";
        List<User> users = jdbcTemplate.query(SQL, new Mapper(), "%" + userName.toUpperCase() + "%");
        System.out.println("Searching for user: " + userName);
        return users;
    }
}
