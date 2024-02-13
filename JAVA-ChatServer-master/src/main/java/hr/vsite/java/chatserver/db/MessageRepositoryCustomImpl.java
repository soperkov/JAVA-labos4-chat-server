package hr.vsite.java.chatserver.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @SuppressWarnings("unused")
    public MessageRepositoryCustomImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public class Mapper implements RowMapper<Message> {
        @Override
        public Message mapRow(java.sql.ResultSet rs, int i) throws java.sql.SQLException {
            Message message = new Message();
            message.setId(rs.getInt("id"));
            message.setMessage(rs.getString("message"));
            message.setUser(userRepository.findByUserNameStartsWith(rs.getString("user_name")).getFirst());
            message.setGroup(groupRepository.findByGroupNameStartsWith(rs.getString("group_name")).getFirst());
            return message;
        }
    }

    @Override
    public List<Message> search(int id) {
        String sql = "SELECT * FROM message WHERE id = ?";
        List<Message> messages = jdbcTemplate.query(sql, new Mapper(), id);
        System.out.println("Searching for message: " + id);
        return messages;
    }
}
