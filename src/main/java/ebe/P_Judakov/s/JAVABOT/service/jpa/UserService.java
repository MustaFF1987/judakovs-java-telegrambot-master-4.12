package ebe.P_Judakov.s.JAVABOT.service.jpa;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.UserRepository;
import ebe.P_Judakov.s.JAVABOT.service.interfaces.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ebe.P_Judakov.s.JAVABOT.domen.entity.role.Role;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private  UserRepository userRepository;

    private ChatRepository chatRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User createUser(ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User user) {
        JpaUser jpaUser = (JpaUser) user;
        // создания нового пользователя
        return userRepository.save(jpaUser);
    }

    @Override
    public ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User getUserById(int userId) {
        Optional<JpaUser> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> (ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User) user).orElse(null);
    }

    @Override
    public ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User getUserByUsername(String username) {
        // Ваша логика получения пользователя по имени
        return null;
    }

    @Override
    public ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User updateUser(int userId, ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User updatedUser) {
        Optional<JpaUser> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setRole(updatedUser.getRole());
            userRepository.save(user);
        });
        return userOptional.map(user -> (ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User) user).orElse(null);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<JpaUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User addUserToChat(int userId, ChatInterface chat) {
        Optional<JpaUser> userOptional = userRepository.findById(userId);
        Optional<JpaChat> chatOptional = chatRepository.findById(chat.getId());

        if (userOptional.isPresent() && chatOptional.isPresent()) {
            JpaUser user = userOptional.get();
            JpaChat updatedJpaChat = chatOptional.get();

            // Добавления пользователя к чату
            updatedJpaChat.addUser(user);
            chatRepository.save(updatedJpaChat);

            return user;
        }
        return null; // Обработка случая, если пользователь или чат не существуют
    }

    // метод для установки роли пользователю
    public void setUserRole(Long chatId, String role) {
        // Получаем пользователя по chatId
        JpaUser user = userRepository.findByChatId(chatId);

        // Установите роль пользователю
        Role userRole = new Role(role);
        user.setRole(userRole);

        // Сохраняем обновленного пользователя в базе данных
        userRepository.save(user);
    }
}
