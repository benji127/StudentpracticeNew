package com.example.studentpracticenew.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.studentpracticenew.entities.Student;
import com.example.studentpracticenew.entities.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.studentpracticenew.repositories.UserRepository;

@Controller
public class UserController {
    @Autowired
    UserRepository urepo;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/signup")
    public String getSignup() {
        return "registration";

    }

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@RequestParam("email") String email, Model model, Users user) {
        ModelAndView mv = new ModelAndView("success");
        List<User> list = urepo.findByEMAIL(email);

        if (list.size() != 0) {
            mv.addObject("message", "Oops!  There is already a user registered with the email provided.");

        } else {
            urepo.save(user);
            mv.addObject("message", "User has been successfully registered.");
        }

        return mv;
    }

    @GetMapping("/dummy")
    public String dummy() {
        return "dummy";
    }

    @PostMapping("/login")
    public String login_user(@RequestParam("email") String email, @RequestParam("password") String password,
                             HttpSession session, ModelMap modelMap) {

        Users auser = (Users) urepo.findByEmailPassword(email, password);

        if (auser != null) {
            String uemail = auser.getEmail();
            String upass = auser.getPassword();

            if (email.equalsIgnoreCase(uemail) && password.equalsIgnoreCase(upass)) {
                session.setAttribute("email", email);
                return "dummy";
            } else {
                modelMap.put("error", "Invalid Account");
                return "login";
            }
        } else {
            modelMap.put("error", "Invalid Account");
            return "login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout_user(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/login";
    }
}
