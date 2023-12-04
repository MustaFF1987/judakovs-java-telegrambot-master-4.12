package ebe.P_Judakov.s.JAVABOT;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class DatabaseIntegrationTest {

    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        connection = DriverManager.getConnection(mySQLContainer.getJdbcUrl(), mySQLContainer.getUsername(), mySQLContainer.getPassword());

        // Добавьте создание таблицы или другую инициализацию перед каждым тестом
        createTable();
    }

    @Test
    public void testSaveAndRetrieveMessage() throws SQLException {
        String chatId = "123";
        String messageText = "{\"Global Quote\": {\"01. symbol\": \"TSLA\", \"02. open\": \"245.1400\", \"03. high\": \"245.2200\"}}";

        // Создание объекта TelegramMessageHandler и установка соединения с базой данных
        TelegramMessageHandler handler = new TelegramMessageHandler();
        handler.setConnection(connection);

        // Вызов метода сохранения сообщения
        handler.saveMessageToDatabase(chatId, messageText);

        // Запрос к базе данных для получения сохраненного сообщения
        String savedMessage = retrieveSavedMessageFromDatabase(chatId);

        // Проверка, что сообщение сохранилось корректно
        assertEquals(messageText, savedMessage);
    }

    private void createTable() throws SQLException {
        // Создание таблицы или другие операции инициализации
        String sql = "CREATE TABLE IF NOT EXISTS messages (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "chat_id VARCHAR(255) NOT NULL," +
                "text TEXT" +
                ")";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }

    private String retrieveSavedMessageFromDatabase(String chatId) throws SQLException {
        String savedMessage = null;

        try {
            String sql = "SELECT text FROM messages WHERE chat_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, chatId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    savedMessage = resultSet.getString("text");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedMessage;
    }
}
*/
