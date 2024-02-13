package hr.vsite.java.chatserver.domain;

import hr.vsite.java.chatserver.db.Message;
import hr.vsite.java.chatserver.db.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public List<Message> findMessages(int id) {
        return messageRepository.search(id);
    }

    public int createMessage(Message message) {
        if (!StringUtils.hasText(message.getMessage()) || message.getGroup() == null || message.getUser() == null) {
            throw new RuntimeException("Message, user and group must be provided");
        }
        return messageRepository.save(message).getId();
    }

    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }
}
