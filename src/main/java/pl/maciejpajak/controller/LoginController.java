package pl.maciejpajak.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.maciejpajak.entity.User;
import pl.maciejpajak.form.LoginForm;
import pl.maciejpajak.repository.UserRepository;
import pl.maciejpajak.util.Consts;

@Controller
public class LoginController {
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    
    @PostMapping("/login")
    public String processLoginRequest(@Valid LoginForm loginForm, BindingResult result, HttpSession session) {
        User u = userRepo.findFirstByUsernameIgnoreCase(loginForm.getUsername());
        if (u != null) {
            if (u.checkPassword(loginForm.getPassword())) {
                session.setAttribute(Consts.LOGGED_ID, u.getId());
                return "redirect:/";
            } 
            result.reject("login.error", "invalid credentials");
            result.getModel().put("password", "");
        }
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Consts.LOGGED_ID);
        return "redirect:/";
    }
    
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String processRegistrationRequest(@Valid User u, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        } else if (userRepo.existsByUsernameIgnoreCase(u.getUsername())) {
            result.rejectValue("username", "user.registration.username", "An account already exists for that username");
            return "register";
        } else if (userRepo.existsByEmail(u.getEmail())) {
            result.rejectValue("email", "user.registration.email", "An account already exists for that email");
            return "register";
        }
        userRepo.save(u);
        return "redirect:/";
    }


}
