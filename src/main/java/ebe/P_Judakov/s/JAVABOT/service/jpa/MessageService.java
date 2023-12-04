package ebe.P_Judakov.s.JAVABOT.service.jpa;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.MessageInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaMessage;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.MessageRepository;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.UserRepository;
import ebe.P_Judakov.s.JAVABOT.service.interfaces.MessageServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements MessageServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }


    @Override
    public MessageInterface sendMessage(long chatId, long userId, String text) {
        try {
            LOGGER.info("Trying to send message with text '{}' from user ID '{}' to chat ID '{}'", text, userId, chatId);

            Optional<JpaUser> optionalUser = userRepository.findById(userId);
            Optional<JpaChat> optionalChat = chatRepository.findById(chatId);

            if (optionalUser.isPresent() && optionalChat.isPresent()) {
                JpaUser user = optionalUser.get();
                JpaChat chat = optionalChat.get();

                JpaMessage newMessage = new JpaMessage();
                newMessage.setText(text);
                newMessage.setDate(new Date());
                newMessage.setUser(user);
                newMessage.setChat(chat);

                MessageInterface savedMessage = messageRepository.save(newMessage);
                LOGGER.info("Message with ID '{}' successfully sent to chat ID '{}'", savedMessage.getId(), chatId);
                return savedMessage;
            } else {
                LOGGER.warn("User or chat not found while trying to send message");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while sending message", e);
            return null;
        }
    }

    @Override
    public List<JpaMessage> getMessagesInChat(long chatId) {
        try {
            LOGGER.info("Trying to retrieve messages for chat ID '{}'", chatId);

            Optional<JpaChat> optionalChat = chatRepository.findById(chatId);
            if (optionalChat.isPresent()) {
                JpaChat jpaChat = optionalChat.get();
                List<JpaMessage> messages = jpaChat.getMessages();

                LOGGER.info("Retrieved {} messages for chat ID '{}'", messages.size(), chatId);
                return messages;
            } else {
                LOGGER.warn("Chat with ID '{}' not found", chatId);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving messages for chat ID '{}'", chatId, e);
            return Collections.emptyList();
        }
    }


    @Override
    public MessageInterface getMessageById(long messageId) {
        try {
            LOGGER.info("Trying to retrieve message by ID '{}'", messageId);

            MessageInterface message = messageRepository.findById(messageId);

            if (message != null) {
                LOGGER.info("Retrieved message with ID '{}'", messageId);
            } else {
                LOGGER.warn("Message with ID '{}' not found", messageId);
            }

            return message;
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving message by ID '{}'", messageId, e);
            return null;
        }
    }

    @Override
    public MessageInterface getLastMessageInChat(long chatId) {
        try {
            LOGGER.info("Trying to retrieve last message in chat with ID '{}'", chatId);

            MessageInterface lastMessage = messageRepository.findFirstByChatIdOrderByDateDesc((int) chatId);

            if (lastMessage != null) {
                LOGGER.info("Retrieved last message in chat with ID '{}'", chatId);
            } else {
                LOGGER.warn("Last message in chat with ID '{}' not found", chatId);
            }

            return lastMessage;
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving last message in chat with ID '{}'", chatId, e);
            return null;
        }
    }


    @Override
    public void saveMessage(long chatId, String messageText) {
        try {
            LOGGER.info("Received chatId in saveMessage: {}", chatId);
            long chatIdLong = Long.parseLong(String.valueOf(chatId));

            Optional<JpaChat> optionalChat = chatRepository.findById(chatIdLong);

            if (optionalChat.isPresent()) {
                JpaChat jpaChat = optionalChat.get();

                JpaMessage newMessage = new JpaMessage();
                newMessage.setText(messageText);
                newMessage.setDate(new Date());
                newMessage.setChat(jpaChat);

                messageRepository.save(newMessage);

                LOGGER.info("Сообщение успешно сохранено в чате с ID {}", chatId);
            } else {
                LOGGER.warn("Чат с ID {} не найден", chatId);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Ошибка преобразования chatId в long: {}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Ошибка при сохранении сообщения в базе данных", e);
        }
    }
}




