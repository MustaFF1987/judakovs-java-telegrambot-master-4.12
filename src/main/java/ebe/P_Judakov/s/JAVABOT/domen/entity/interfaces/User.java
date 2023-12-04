package ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces;

import ebe.P_Judakov.s.JAVABOT.domen.entity.role.Role;

public interface User {

    int getId();
    String getUsername();
    Role getRole();
    void setChatId(Long chatId);
}
