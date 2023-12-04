package ebe.P_Judakov.s.JAVABOT.service.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramMessageHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TelegramMessageHandler.class);

    private Long chatId;
    private String message;

    public TelegramMessageHandler() {
        // Инициализация подключения к базе данных
        this.connection = establishDatabaseConnection();
    }
    public TelegramMessageHandler(Long chatId, String message, MessageService messageService) {
        this.chatId = chatId;
        this.message = message;
        this.messageService = messageService;
    }
    private TelegramMessageSender messageSender;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/telegram_bot_10-170123-e-be";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123Unikorpa12";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; // Драйвер MySQL JDBC

    public TelegramMessageHandler(
            TelegramMessageSender messageSender,
            MessageService messageService,
            Connection connection
    ) {
        this.messageSender = messageSender;
        this.messageService = messageService;
        this.connection = connection;
    }

    private Connection connection;
    private Logger logger;

    public TelegramMessageHandler(TelegramMessageSender messageSender, TelegramBotService botService, MessageService messageService) {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }



    private Connection establishDatabaseConnection() {
        try {
            Class.forName(DB_DRIVER);
            LOGGER.info("JDBC driver loaded successfully: {}", DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Failed to load JDBC driver: {}", DB_DRIVER, e);
            throw new IllegalStateException("Failed to load JDBC driver: " + DB_DRIVER, e);
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LOGGER.info("Database connection established successfully");
        } catch (SQLException e) {
            LOGGER.error("Failed to establish database connection", e);
            e.printStackTrace();
        }

        return connection;
    }

    private TelegramBotService botService;
    public TelegramMessageHandler(TelegramBotService botService) {
        this.botService = botService;
    }
    private MessageService messageService;

    public void saveMessageToDatabase(String chatId, String messageText) {
        if (messageText.contains("Global Quote")) {
            messageService.saveMessage(Long.parseLong(chatId), messageText);
            System.out.println("MessageInterface saved to the database for chatId: " + chatId);
            LOGGER.info("MessageInterface saved to the database for chatId: {}", chatId);
        }
    }

    public void handleMessage(Long chatId, String messageText) {
        if (messageText.contains("Global Quote")) {
            String stockInfo = botService.fetchStockQuoteInfo(messageText);

            if (stockInfo != null && !stockInfo.isEmpty()) {
                saveMessageToDatabase(chatId.toString(), stockInfo);
                messageSender.sendMessage(chatId, stockInfo);
                LOGGER.info("Stock information sent and saved for chatId: {}", chatId);
            }
        }
    }
}

