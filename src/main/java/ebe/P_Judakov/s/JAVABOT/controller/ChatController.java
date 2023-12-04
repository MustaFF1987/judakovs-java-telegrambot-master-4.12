package ebe.P_Judakov.s.JAVABOT.controller;

import ebe.P_Judakov.s.JAVABOT.repository.interfaces.ChatRepository;
import ebe.P_Judakov.s.JAVABOT.service.interfaces.ChatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    /**
     * Сервис Чата.
     * Содержит бизнес-логику, относящуюся к чату.
     */
    @Autowired
    private ChatServiceInterface chatService;


    private ChatRepository chatRepository;
}
