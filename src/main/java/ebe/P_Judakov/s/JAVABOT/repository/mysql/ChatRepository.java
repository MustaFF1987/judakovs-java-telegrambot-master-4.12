package ebe.P_Judakov.s.JAVABOT.repository.mysql;

import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.MessageInterface;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.User;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.SubscribedChannel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public class ChatRepository implements ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository
 {

    private final EntityManager entityManager;

    public ChatRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   private ChatRepository chatRepository;

     // Этот метод вернет список чатов, в которых участвует указанный пользователь.
    @Override
    public List<ChatInterface> findByUsers(User user) {
        Query query = entityManager.createQuery("SELECT c FROM ChatInterface c WHERE :user MEMBER OF c.users");
        query.setParameter("user", user);
        return query.getResultList();
    }

     // Этот метод вернет список всех сообщений, относящихся к указанному чату.
    @Override
    public List<MessageInterface> findMessagesByChat(ChatInterface chat) {
         Query query = entityManager.createQuery("SELECT m FROM MessageInterface m WHERE m.chat = :chat");
         query.setParameter("chat", chat);
         return query.getResultList();
    }

    // Реализация метода для сохранения чата
    @Override
    public ChatInterface save(ChatInterface chat) {
        entityManager.getTransaction().begin();
        entityManager.persist(chat);
        entityManager.getTransaction().commit();
        return chat;
    }

    // Реализация метода для удаления чата
    @Override
    public void delete(ChatInterface chat) {
        entityManager.getTransaction().begin();
        entityManager.remove(chat);
        entityManager.getTransaction().commit();
    }

     // Этот метод вернет количество пользователей, участвующих в указанном чате.
    @Override
    public int countUsersByChat(ChatInterface chat) {
        if (chat instanceof JpaChat) {
            JpaChat jpaChat = (JpaChat) chat;
            if (jpaChat.getUsers() != null) {
                return jpaChat.getUsers().size();
            }
        }
        return 0;
    }

    // Реализация метода для поиска подписанных каналов по chatId
    @Override
    public List<SubscribedChannel> findSubscribedChannelsByChatIdAndChannelId(int chatId) {
        return entityManager.createQuery("SELECT sc FROM SubscribedChannel sc WHERE sc.chatId = :chatId", SubscribedChannel.class)
                .setParameter("chatId", chatId)
                .getResultList();
    }


     //  Возвращает идентификатор чата.
     //  Он используется для извлечения идентификатора чата из объекта, который реализует интерфейс ChatInterface.
     @Override
     public long getId(ChatInterface chat) {
         if (chat instanceof JpaChat) {
             return ((JpaChat) chat).getId();
         }
         return -1; // если чат не является JpaChat
     }

     @Override
     public void deleteById(long chatId) {
         chatRepository.deleteById(chatId); // Удаляем чат по его идентификатору с помощью метода из репозитория
     }

     @Override
     public ChatInterface findByChatId(Long chatId) {
         return entityManager.find(ChatInterface.class, chatId);
     }

     @Override
     public Optional<JpaChat> findById(long id) {
         return Optional.ofNullable(entityManager.find(JpaChat.class, id));
     }
}
