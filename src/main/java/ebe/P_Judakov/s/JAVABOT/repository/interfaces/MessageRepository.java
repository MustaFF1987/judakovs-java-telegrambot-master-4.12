package ebe.P_Judakov.s.JAVABOT.repository.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.MessageInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User;

import java.util.Date;
import java.util.List;

public interface MessageRepository {

    // Этот метод вернет список сообщений, отправленных указанным пользователем.
    List<MessageInterface> findByUser(User user);

    // Этот метод вернет список сообщений, относящихся к указанному чату.
    List<MessageInterface> findByChat(ChatInterface chat);

    // Этот метод вернет список сообщений, отправленных в указанный период времени.
    List<MessageInterface> findByDateBetween(Date startDate, Date endDate);

    // Этот метод вернет последнее сообщение в указанном чате, сортированное по дате в убывающем порядке.
    MessageInterface findTopByChatOrderByDateDesc(ChatInterface chat);

    // Получение количества сообщений в чате
    int countByChat(ChatInterface chat);

    // Метод сохранения сообщения
    MessageInterface save(MessageInterface message);

    // Метод получения сообщения по его идентификатору
    MessageInterface findById(Long id);

    MessageInterface findFirstByChatIdOrderByDateDesc(int chatId);

    // Метод удаления сообщения по Id
    void deleteById(int messageId);

    // Метод удаления сообщения
    void delete(MessageInterface message);
}
