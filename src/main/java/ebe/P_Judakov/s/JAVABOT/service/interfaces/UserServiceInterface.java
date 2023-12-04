package ebe.P_Judakov.s.JAVABOT.service.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserServiceInterface {

    // Создание нового пользователя:
    ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User createUser(ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User user);

    // Поиск пользователя по идентификатору:
    ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User getUserById(int userId);

    // Поиск пользователя по имени пользователя:
    ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User getUserByUsername(String username);

    // Обновление информации о пользователе:
    ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User updateUser(int userId, ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User updatedUser);

    // Удаление пользователя:
    @Transactional
    void deleteUser(int userId);

    // Получение списка всех пользователей:
    List<JpaUser> getAllUsers();

    // Добавление пользователя в чат:
    ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User addUserToChat(int userId, ChatInterface chat);


    void setUserRole(Long chatId, String role);
}
