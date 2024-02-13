package hr.vsite.java.chatserver.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unused")
    public GroupRepositoryCustomImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class Mapper implements RowMapper<Group> {
        @Override
        public Group mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
            Group group = new Group();
            group.setGroupName(rs.getString("group_name"));
            return group;
        }
    }

    @Override
    public List<Group> search(String groupName) {
        String SQL = "SELECT * FROM groups WHERE upper(group_name) LIKE ?";
        List<Group> groups = jdbcTemplate.query(SQL, new Mapper(), "%" + groupName.toUpperCase() + "%");
        System.out.println("Searching for group: " + groupName);
        return groups;
    }
}
