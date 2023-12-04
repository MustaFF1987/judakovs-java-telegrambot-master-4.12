package ebe.P_Judakov.s.JAVABOT.service.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.MessageInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaMessage;

import java.util.List;

public interface MessageServiceInterface {

        // Отправка сообщения в чат.
        MessageInterface sendMessage(long chatId, long userId, String text);

        // Получение всех сообщений в чате.
        List<JpaMessage> getMessagesInChat(long chatId);

        // Получение сообщения по его идентификатору.
        MessageInterface getMessageById(long messageId);

        // Получение последнего сообщения в чате.
        MessageInterface getLastMessageInChat(long chatId);

        // Сохранение сообщения в чате.
        void saveMessage(long chatId, String messageText);
}
