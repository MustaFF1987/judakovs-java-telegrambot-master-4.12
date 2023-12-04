//package ebe.P_Judakov.s.JAVABOT;
//
//import ebe.P_Judakov.s.JAVABOT.service.jpa.MessageService;
//import ebe.P_Judakov.s.JAVABOT.service.jpa.TelegramBotService;
//import ebe.P_Judakov.s.JAVABOT.service.jpa.TelegramMessageHandler;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.*;
//
//class TelegramMessageHandlerTest {
//
//    @Mock
//    private MessageService messageService;
//
//    @Mock
//    private TelegramBotService botService;
//
//    @InjectMocks
//    private TelegramMessageHandler telegramMessageHandler;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        telegramMessageHandler = new TelegramMessageHandler(botService, messageService);
//    }
//
//    @Test
//    void saveMessageToDatabase_Test() {
//        String chatId = "123456";
//        String messageText = "Some message with Global Quote";
//
//        telegramMessageHandler.saveMessageToDatabase(chatId, messageText);
//
//        // Проверяем, что метод сохранения сообщения был вызван один раз с корректными параметрами
//        verify(messageService, times(1)).saveMessage(eq(chatId), eq(messageText));
//    }
//}