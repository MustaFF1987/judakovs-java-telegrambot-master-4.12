package ebe.P_Judakov.s.JAVABOT.config;

import ebe.P_Judakov.s.JAVABOT.repository.mysql.ChatRepository;
import ebe.P_Judakov.s.JAVABOT.repository.mysql.MessageRepository;
import ebe.P_Judakov.s.JAVABOT.repository.mysql.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final EntityManager entityManager; //  передаем EntityManager

    public Config(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void initializeRepository() {
        ChatRepository chatRepository = new ChatRepository(entityManager);
    }

    @Bean
    public ebe.P_Judakov.s.JAVABOT.repository.interfaces.UserRepository customerRepository() {

        return new UserRepository();
    }

    @Bean
    public ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository productRepository() {

        return new ChatRepository(entityManager);
    }

    @Bean
    public ebe.P_Judakov.s.JAVABOT.repository.interfaces.MessageRepository messageRepository() {

        return new MessageRepository(entityManager);
    }

}
