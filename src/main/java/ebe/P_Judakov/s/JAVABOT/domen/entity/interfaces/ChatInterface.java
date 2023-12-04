package ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.jpa.JpaUser;

import java.util.List;

public interface ChatInterface {
    long getId();
    long getChatId();
    List<JpaUser> getUsers();
    void addUser(JpaUser user);
}

