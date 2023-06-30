package com.echec.demo.controller;

import com.echec.demo.entity.User;
import com.echec.demo.exeption.UserExistException;
import com.echec.demo.exeption.UserNotExistException;
import com.echec.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService _userService;
    @Autowired
    private HttpSession _session;



    @GetMapping("update")
    public ModelAndView updateForm(){
        ModelAndView mv = new ModelAndView("update");
        mv.addObject("user",_session.getAttribute("user"));
        return mv;
    }
    @PostMapping("/update")
    public  String update(@RequestParam String pseudo,@RequestParam String firstName, @RequestParam String lastName,@RequestParam int age, @RequestParam String email, @RequestParam String password) {

        User user = (User) _session.getAttribute("user");
        user.setEmail(email);
        user.setAge(age);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPseudo(pseudo);
        _userService.update(user);
        return "redirect:/user/profile";
    }

    @GetMapping("ranking")
    public ModelAndView getRanking(){
        //  User user =
        ModelAndView mv = new ModelAndView("ranking");

        return mv;
    }
    @PostMapping("update/post")
    public ModelAndView postUpdate(){
        ModelAndView mv = new ModelAndView("update");
        return mv;
    }
    @GetMapping("signin")
    public ModelAndView signIn(){
        ModelAndView mv = new ModelAndView("signin");
        return mv;
    }
    @PostMapping("signin")
    public String postsignIn(@RequestParam String pseudo, @RequestParam String password) throws UserNotExistException {
        User user = User.builder().pseudo(pseudo).password(password).build();
        if(_userService.signIn(user)) {

            user = (User) _session.getAttribute("user");
            if (user.isAdmin()){
                return "redirect:/user/admin/profile";
            } else {

            return "redirect:/user/profile";
            }
        }
        return null;
    }


    @GetMapping("signup")
    public ModelAndView SignUp() {
        ModelAndView mv = new ModelAndView("signup");
        return mv;
    }

    @PostMapping("signup")
    public String postSignUp(@RequestParam String pseudo,@RequestParam String firstName, @RequestParam String lastName,@RequestParam int age, @RequestParam String email, @RequestParam String password) {
        User user = User.builder().password(password).pseudo(pseudo).firstName(firstName).lastName(lastName).age(age).email(email).build();
        if(_userService.signUp(user)) {
            return "redirect:/user/profile";
        }


        return null;
    }
    @GetMapping("admin/profile")
    public ModelAndView adminProfile(){
        ModelAndView mv = new ModelAndView("adminPage");
        User user = (User) _session.getAttribute("user");
        mv.addObject("user",user);
        mv.addObject("part",user.getParts());
        return mv;
    }


    @GetMapping("profile")
    public ModelAndView profile(){
        ModelAndView mv = new ModelAndView("profile");
        User user = (User) _session.getAttribute("user");
        mv.addObject("user",user);
        mv.addObject("part",user.getParts());
        return mv;
    }

    @ExceptionHandler(UserExistException.class)
    public ModelAndView handleUserExist(UserExistException ex) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

}
