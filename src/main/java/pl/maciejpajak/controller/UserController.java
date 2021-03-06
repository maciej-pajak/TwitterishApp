package pl.maciejpajak.controller;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.maciejpajak.entity.Message;
import pl.maciejpajak.entity.Tweet;
import pl.maciejpajak.entity.User;
import pl.maciejpajak.form.EmailForm;
import pl.maciejpajak.form.SingleStringForm;
import pl.maciejpajak.repository.CommentRepository;
import pl.maciejpajak.repository.MessageRepository;
import pl.maciejpajak.repository.TweetRepository;
import pl.maciejpajak.repository.UserRepository;
import pl.maciejpajak.util.Consts;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private TweetRepository tweetRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private MessageRepository messageRepo;
    
    @Autowired
    private CommentRepository commentRepo;
    
    @GetMapping("/{id:[0-9]+}")
    public String showProfile(@PathVariable long id, Model model, @PageableDefault(size = 10, sort = "created", direction = Sort.Direction.DESC) Pageable pageable) {
        User u = userRepo.findOne(id);
        if (u == null) {
            return "redirect:/error";
        }
        model.addAttribute("user", u);
        Page<Tweet> tweets = tweetRepo.findByUserId(id, pageable);
        tweets.forEach(t -> t.setCommentsCount(commentRepo.countByTweetId(t.getId())));
        model.addAttribute("tweets", tweets);
        model.addAttribute("dateFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        return "user/profile";
    }

    @GetMapping("/{box:inbox|outbox}")
    public String showMessages(Model model, 
            @PathVariable String box,
            @PageableDefault(size = 20, sort = "created", direction = Sort.Direction.DESC) Pageable pageable,
            HttpSession session) {
        
        Long id = (Long) session.getAttribute(Consts.LOGGED_ID);
        Page<Message> messages;
        
        if (box.equals("inbox")) {
            messages = messageRepo.findByRecipientId(id, pageable);
        } else {
            messages = messageRepo.findBySenderId(id, pageable);
        }
        
        messages.forEach(m -> {
            if (m.getMessage().length() > 27) {
                m.setMessage(m.getMessage().substring(0, 27).concat("..."));
            }
        });
        
        model.addAttribute("messages", messages);
        model.addAttribute("dateFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        model.addAttribute("boxType", box);
        return "user/messageBox";
    }
    
    @GetMapping("/edit")
    public String editProfile(Model model) {
        model.addAttribute("emailForm", new EmailForm());
        model.addAttribute("singleStringForm", new SingleStringForm());
        return "user/edit";
    }
    
    @PostMapping("/edit")
    public String updateProfile(@Valid EmailForm emailForm, BindingResult result,
            @SessionAttribute(name = Consts.LOGGED_ID, required = true) Long id) {
        if (!result.hasErrors()) {
            if (userRepo.existsByEmail(emailForm.getEmail())) {
                result.rejectValue("email", "emailform.email.taken", "this email is already taken");
            } else {
                userRepo.save(userRepo.findOne(id).setEmail(emailForm.getEmail()));
                return "redirect:/user/edit";
            }
        }
        return "user/edit";
    }
    
    @PostMapping("/delete")
    public String deleteProfile(@Valid SingleStringForm passwordForm,
            BindingResult result,
            @SessionAttribute(name = Consts.LOGGED_ID, required = true) Long id,
            HttpSession session,
            Model model) {
        model.addAttribute("emailForm", new EmailForm());
        User u = userRepo.findOne(id);
        if (result.hasErrors()) {
            return "user/edit";
        }
        if (!u.checkPassword(passwordForm.getString())) {
            result.rejectValue("string", "passwordForm.invalid", "invalid password");
            return "user/edit";
        }
        session.setAttribute(Consts.LOGGED_ID, null);
        userRepo.delete(u);
        return "redirect:/";
    }
    
}
