package hr.vsite.java.chatserver.rest;


import hr.vsite.java.chatserver.db.Message;
import hr.vsite.java.chatserver.domain.MessageService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController()
public class MessageRestController {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MessageRestController.class);

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PutMapping("/messages")
    public void createMessage(@RequestBody MessageDTO messageDTO) {
        logger.info("Creating message {}", messageDTO);
        Message message = new Message();
        message.setMessage(messageDTO.getMessage());
        message.setGroup(messageDTO.getGroup());
        message.setUser(messageDTO.getUser());
        messageService.createMessage(message);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(Message message) {
        logger.info("Deleting message {}", message);
        messageService.deleteMessage(message);
    }

    @GetMapping("/messages")
    public List<MessageDTO> getMessages(@RequestParam(required = false) String messageId) {
        logger.info("Getting messages {}", messageId);
        List<Message> messages;
        if (StringUtils.hasText(messageId)) {
            messages = messageService.findMessages(Integer.parseInt(messageId));
        } else {
            messages = messageService.getMessages();
        }
        List<MessageDTO> messageDTOS = new LinkedList<>();
        for (Message message : messages) {
            messageDTOS.add(toDto(message));
        }
        return messageDTOS;
    }

    private MessageDTO toDto(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setMessage(message.getMessage());
        dto.setGroup(message.getGroup());
        dto.setUser(message.getUser());
        return dto;
    }
}
