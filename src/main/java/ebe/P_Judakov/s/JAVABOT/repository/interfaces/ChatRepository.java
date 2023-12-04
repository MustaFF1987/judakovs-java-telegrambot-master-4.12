package ebe.P_Judakov.s.JAVABOT.repository.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.MessageInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.SubscribedChannel;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    // Поиск чатов, в которых участвует определенный пользователь.
    // Этот метод вернет список чатов, в которых участвует указанный пользователь.
    List<ChatInterface> findByUsers(User user);


    // Получение списка всех сообщений в чате.
    // Этот метод вернет список всех сообщений, относящихся к указанному чату.
    List<MessageInterface> findMessagesByChat(ChatInterface chat);

    // Создание нового чата.
    // Этот метод позволяет создать новый чат, добавляя его в базу данных.
    ChatInterface save(ChatInterface chat);

    // Удаление чата.
    // Этот метод позволяет удалить чат
    void delete(ChatInterface chat);

    // Получение количества участников в чате:
    // Этот метод вернет количество пользователей, участвующих в указанном чате.
    int countUsersByChat(ChatInterface chat);

    // Реализация метода для поиска подписанных каналов по chatId
    List<SubscribedChannel> findSubscribedChannelsByChatIdAndChannelId(int chatId);

    // Реализация метода для поиска чата по его идентификатору
    ChatInterface findByChatId(Long chatId);



    // Реализация метода для поиска чата по его идентификатору (JPA)
    Optional<JpaChat> findById(long id);


    long getId(ChatInterface chat);

    void deleteById(long chatId);
}
