package pl.maciejpajak.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.maciejpajak.entity.User;
import pl.maciejpajak.repository.MessageRepository;
import pl.maciejpajak.repository.UserRepository;
import pl.maciejpajak.util.Consts;

@ControllerAdvice
public class GlobalControllerAdvice {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private MessageRepository messageRepo;

    @ModelAttribute("user")
    public User user(HttpSession sess) {
        Long id = (Long) sess.getAttribute(Consts.LOGGED_ID);
        if (id == null) {
            return null;
        }
        return userRepo.findOne(id);
    }
    
    @ModelAttribute("unreadCount")
    public Long messagesCount(HttpSession sess) {
        Long id = (Long) sess.getAttribute(Consts.LOGGED_ID);
        if (id == null) {
            return null;
        }
        return messageRepo.countByRecipientIdAndRead(id, false);
    }
}
