package pl.maciejpajak.controller;



import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.maciejpajak.entity.Tweet;
import pl.maciejpajak.repository.CommentRepository;
import pl.maciejpajak.repository.TweetRepository;
import pl.maciejpajak.util.Consts;

@Controller
public class HomeController {
    
    @Autowired
    private TweetRepository tweetRepo;
    
    @Autowired
    private CommentRepository commentRepo;
    
    @ModelAttribute("tweets")
    public Page<Tweet> tweets(@PageableDefault(size = 5, sort = "created", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Tweet> tweets = tweetRepo.findAll(pageable);
        tweets.forEach(t -> t.setCommentsCount(commentRepo.countByTweetId(t.getId())));
        return tweets;
    }
    
    @ModelAttribute("dateFormatter")
    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    }

    @RequestMapping("/")
    public String homePage(@SessionAttribute(name = Consts.LOGGED_ID, required = false) Long id, Model model) {
        model.addAttribute("logged", id != null);
        return "index";
    }
    
}
