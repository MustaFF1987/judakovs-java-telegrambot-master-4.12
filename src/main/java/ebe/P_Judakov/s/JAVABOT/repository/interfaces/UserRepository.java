package ebe.P_Judakov.s.JAVABOT.repository.interfaces;


import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<JpaUser, Long> {

    Optional<JpaUser> findById(int userId);

    void deleteById(int userId);

    JpaUser findByChatId(Long chatId);
}
