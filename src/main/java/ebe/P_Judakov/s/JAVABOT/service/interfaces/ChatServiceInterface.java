package ebe.P_Judakov.s.JAVABOT.service.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;

import java.util.List;

public interface ChatServiceInterface {

    // Создание нового чата.
    ChatInterface createChat(ChatInterface chat);

    // Поиск чата по идентификатору.
    ChatInterface getChatById(long chatId);

    // Добавление пользователя в чат.
    ChatInterface addUserToChat(long chatId, ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User user);

    // Удаление чата.
    void deleteChat(long chatId);

    // Получение списка пользователей в чате.
    List<JpaUser> getUsersInChat(int chatId);


}
