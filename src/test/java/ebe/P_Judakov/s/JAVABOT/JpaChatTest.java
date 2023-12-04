package ebe.P_Judakov.s.JAVABOT;

import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class JpaChatTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testSaveChatToDatabase() {
        // Подготовка тестовых данных
        int chatId = 123;
        String type = "group";

        JpaChat jpaChat = new JpaChat();
        jpaChat.setChatId(chatId);
        jpaChat.setType(type);

        // Сохранение чата в базе данных
        entityManager.persist(jpaChat);

        // Проверка, что чат успешно сохранен в базе данных
        JpaChat savedJpaChat = entityManager.find(JpaChat.class, jpaChat.getId());
        assertNotNull(savedJpaChat); // Проверяем, что чат не null

        // Проверка данных сохраненного чата
        assertEquals(chatId, savedJpaChat.getChatId());
        assertEquals(type, savedJpaChat.getType());
    }

    @Test
    public void testAddUserToChat() {
        // Подготовка тестовых данных
        int chatId = 123;
        String type = "group";

        JpaChat jpaChat = new JpaChat();
        jpaChat.setChatId(chatId);
        jpaChat.setType(type);

        List<JpaUser> users = new ArrayList<>();
        JpaUser user1 = new JpaUser(/* Заполните необходимые данные для пользователя */);
        users.add(user1);

        jpaChat.setUsers(users);

        // Проверка добавления пользователя к чату
        jpaChat.addUser(user1);

        // Проверка, что пользователь был добавлен к чату
        assertEquals(1, jpaChat.getUsers().size());
        assertEquals(user1, jpaChat.getUsers().get(0));
    }
}

