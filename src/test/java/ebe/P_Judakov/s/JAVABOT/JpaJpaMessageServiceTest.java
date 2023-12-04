package ebe.P_Judakov.s.JAVABOT;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaChat;
import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaMessage;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository;
import ebe.P_Judakov.s.JAVABOT.repository.interfaces.MessageRepository;
import ebe.P_Judakov.s.JAVABOT.service.jpa.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.mockito.Mockito.*;

public class JpaJpaMessageServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService jpaMessageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveMessage() {
        String chatId = "1";
        String messageText = "Test message";

        JpaChat jpaChat = new JpaChat();
        jpaChat.setId(1);

        JpaMessage message = new JpaMessage();
        message.setText(messageText);
        message.setJpaChat(jpaChat);

        Optional<JpaChat> optionalChat = Optional.of(jpaChat);

        when(chatRepository.findById(anyInt())).thenReturn(optionalChat);
        when(messageRepository.save(any(JpaMessage.class))).thenReturn(message);

        jpaMessageService.saveMessage(chatId, messageText);

        verify(chatRepository, times(1)).findById(1);
        verify(messageRepository, times(1)).save(any(JpaMessage.class));
    }
}
