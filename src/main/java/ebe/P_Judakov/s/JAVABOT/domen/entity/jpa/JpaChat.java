package ebe.P_Judakov.s.JAVABOT.domen.entity.jpa;
import ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces.ChatInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class JpaChat implements ChatInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "type")
    private String type;

    @ManyToMany
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<JpaUser> users;

    @OneToMany(mappedBy = "chat") // Указываем правильное имя поля в JpaMessage
    private List<JpaMessage> messages;

    public JpaChat(int id, int chatId, String type, List<JpaUser> users, List<JpaMessage> messages) {
        this.id = id;
        this.chatId = chatId;
        this.type = type;
        this.users = users;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "JpaChat{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", type='" + type + '\'' +
                ", users=" + users +
                ", messages=" + messages +
                '}';
    }

    @Override
    public void addUser(JpaUser user) {
    }


}
