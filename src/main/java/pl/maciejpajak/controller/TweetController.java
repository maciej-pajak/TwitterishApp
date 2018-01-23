package pl.maciejpajak.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.maciejpajak.entity.Comment;
import pl.maciejpajak.entity.Tweet;
import pl.maciejpajak.entity.User;
import pl.maciejpajak.repository.CommentRepository;
import pl.maciejpajak.repository.TweetRepository;
import pl.maciejpajak.util.Consts;

@Controller
@RequestMapping("/tweet")
public class TweetController {
    
    @Autowired
    private TweetRepository tweetRepo;
    
    @Autowired
    private CommentRepository commentRepo;

    @RequestMapping("/{id}")
    public String showTweet(@PathVariable long id, Model model, 
                    @PageableDefault(size = 5, sort = "created", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        Tweet t = tweetRepo.findOne(id);
        if (t == null) {
            return "redirect:/error";
        }
        model.addAttribute("tweet", t);
        model.addAttribute("comments", commentRepo.findByTweetId(t.getId(), pageable));
        model.addAttribute("dateFormatter", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        return "tweet/tweet";
    }
    
    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable long id, @RequestParam String comment,
            @SessionAttribute(required = true, name = Consts.LOGGED_ID) Long userId) {
        if (tweetRepo.exists(id)) {
            Comment c = new Comment();
            c.setComment(comment);
            c.setCreated(LocalDateTime.now());
            c.setUser(new User().setId(userId));
            c.setTweet(tweetRepo.findOne(id));
            commentRepo.save(c);
        }
        return "redirect:/tweet/" + id;
    }
    
    @PostMapping("/new")
    public  String createNewTweet(@RequestParam(required = true) String tweet,
            @SessionAttribute(required = true, name = Consts.LOGGED_ID) Long id) {
        Tweet t = new Tweet();
        t.setCreated(LocalDateTime.now());
        t.setText(tweet);
        t.setUser(new User().setId(id));
        tweetRepo.save(t);
        return "redirect:/";
    }
}
