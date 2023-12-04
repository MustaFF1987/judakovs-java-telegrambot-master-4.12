package ebe.P_Judakov.s.JAVABOT.repository.mysql;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.MessageInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class MessageRepository implements ebe.P_Judakov.s.JAVABOT.repository.interfaces.MessageRepository {

    private final EntityManager entityManager;

    public MessageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Этот метод вернет список сообщений, отправленных указанным пользователем.
    @Override
    public List<MessageInterface> findByUser(User user) {
        TypedQuery<MessageInterface> query = entityManager.createQuery(
                "SELECT m FROM MessageInterface m WHERE m.user = :user", MessageInterface.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    // Этот метод вернет список сообщений, относящихся к указанному чату.
    @Override
    public List<MessageInterface> findByChat(ChatInterface chat) {
        TypedQuery<MessageInterface> query = entityManager.createQuery(
                "SELECT m FROM MessageInterface m WHERE m.chat = :chat", MessageInterface.class);
        query.setParameter("chat", chat);
        return query.getResultList();
    }

    // Этот метод вернет список сообщений, отправленных в указанный период времени.
    @Override
    public List<MessageInterface> findByDateBetween(Date startDate, Date endDate) {
        TypedQuery<MessageInterface> query = entityManager.createQuery(
                "SELECT m FROM MessageInterface m WHERE m.date BETWEEN :startDate AND :endDate", MessageInterface.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    // Этот метод вернет последнее сообщение в указанном чате, сортированное по дате в убывающем порядке.
    @Override
    public MessageInterface findTopByChatOrderByDateDesc(ChatInterface chat) {
        TypedQuery<MessageInterface> query = entityManager.createQuery(
                "SELECT m FROM MessageInterface m WHERE m.chat = :chat ORDER BY m.date DESC", MessageInterface.class);
        query.setParameter("chat", chat);
        query.setMaxResults(1);
        List<MessageInterface> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Получение количества сообщений в чате
    @Override
    public int countByChat(ChatInterface chat) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(m) FROM MessageInterface m WHERE m.chat = :chat", Long.class);
        query.setParameter("chat", chat);
        return Math.toIntExact(query.getSingleResult());
    }

    // Метод сохранения сообщения
    @Override
    public MessageInterface save(MessageInterface message) {
        entityManager.getTransaction().begin();
        entityManager.persist(message);
        entityManager.getTransaction().commit();
        return message;
    }

    // Метод получения сообщения по его идентификатору
    @Override
    public MessageInterface findById(Long id) {
        return entityManager.find(MessageInterface.class, id);
    }

    @Override
    public MessageInterface findFirstByChatIdOrderByDateDesc(int chatId) {
        return null;
    }

    @Override
    public void deleteById(int messageId) {
    }

    // Метод удаления сообщения
    @Override
    public void delete(MessageInterface message) {
        entityManager.getTransaction().begin();
        entityManager.remove(message);
        entityManager.getTransaction().commit();
    }
}
