package pl.maciejpajak.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.maciejpajak.entity.Message;
import pl.maciejpajak.entity.User;
import pl.maciejpajak.form.MessageForm;
import pl.maciejpajak.repository.MessageRepository;
import pl.maciejpajak.repository.TweetRepository;
import pl.maciejpajak.repository.UserRepository;
import pl.maciejpajak.util.Consts;

@Controller
@RequestMapping("/message")
public class MessageController {
    
    @Autowired
    private TweetRepository tweetRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private MessageRepository messageRepo;
    
    @GetMapping("/{id}")
    public String showMessage(@PathVariable(required = true) long id, HttpSession sess, Model model) {
        Message m = messageRepo.findOne(id);
        Long userId = (Long) sess.getAttribute(Consts.LOGGED_ID);
        if (m != null && (m.getRecipient().getId() == userId || m.getSender().getId() == userId)) {
            m.setRead(true);
            messageRepo.save(m);
            model.addAttribute("message", m);
            model.addAttribute("userId", userId);
            model.addAttribute("dateFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            return "message/show";
        }
        return "redirect:/user/messages";
    }
    
    @GetMapping("/new")
    public String newMessage(@RequestParam(name = "to", required = false) Long id, Model model) {
        MessageForm mf = new MessageForm();
        if (id != null && userRepo.exists(id)) {
            mf.setRecipientUsername(userRepo.findOne(id).getUsername());
        }
        model.addAttribute("messageForm", mf);
        return "message/new";
    }
    
    @PostMapping("/new")
    public String processCreateMessageRequest(@Valid MessageForm mf, BindingResult result, HttpSession sess) {
        if (result.hasErrors()) {
            return "message/new";
        } 
        User recipient = userRepo.findFirstByUsernameIgnoreCase(mf.getRecipientUsername());
        Long senderId = (Long) sess.getAttribute(Consts.LOGGED_ID);
        if (recipient == null ) {
            result.rejectValue("recipientUsername", "message.create.recipientNotFound", "recipient not found");
            return "message/new";
        } else if (recipient.getId() == senderId) {
            result.rejectValue("recipientUsername", "message.create.recipientInvalid", "you cannot send message to yourself");
            return "message/new";
        }
        User sender = new User().setId(senderId);
        Message m = new Message(mf.getMessage(), false, LocalDateTime.now(), sender, recipient);
        messageRepo.save(m);
        return "redirect:/user/messages";
    }

}
