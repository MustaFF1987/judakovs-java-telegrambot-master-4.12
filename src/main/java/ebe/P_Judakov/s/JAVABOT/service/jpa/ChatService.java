package ebe.P_Judakov.s.JAVABOT.service.jpa;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.UserRepository;
import ebe.P_Judakov.s.JAVABOT.service.interfaces.ChatServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements ChatServiceInterface {

    private static Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ChatInterface createChat(ChatInterface chat) {
        return chatRepository.save(chat); // Сохраняем чат в репозитории
    }

    @Override
    public ChatInterface getChatById(long chatId) {
        return chatRepository.findById(chatId).orElse(null); // Получаем чат по его идентификатору
    }

    @Override
    public ChatInterface addUserToChat(long chatId, ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User user) {
        Optional<JpaChat> optionalChat = chatRepository.findById(chatId);
        if (optionalChat.isPresent()) {
            ChatInterface chat = optionalChat.get();
            chat.addUser((JpaUser) user); // метод для добавления пользователя
            return chatRepository.save(chat); // Сохраняем обновленный чат в репозитории
        } else {
            LOGGER.error("ChatInterface with ID {} not found", chatId);
            return null; // Возвращаем null в случае, если чат не найден
        }
    }

    @Override
    public void deleteChat(long chatId) {
        chatRepository.deleteById(chatId); // Удаляем чат по его идентификатору
    }

    @Override
    public List<JpaUser> getUsersInChat(int chatId) {
        Optional<JpaChat> optionalChat = chatRepository.findById(chatId);
        if (optionalChat.isPresent()) {
            ChatInterface chat = optionalChat.get();
            return chat.getUsers(); // Получаем список пользователей чата из соответствующего метода в классе ChatInterface
        } else {
            LOGGER.error("ChatInterface with ID {} not found", chatId);
            return Collections.emptyList(); // Возвращаем пустой список, если чат не найден
        }
    }
}

