package com.echec.demo.controller;


import com.echec.demo.entity.Part;
import com.echec.demo.entity.Tournament;
import com.echec.demo.entity.User;
import com.echec.demo.exeption.EmptyEmplacement;
import com.echec.demo.exeption.NotAdminException;
import com.echec.demo.exeption.NotSignInException;
import com.echec.demo.exeption.UserNotExistException;
import com.echec.demo.repository.TournamentRepository;
import com.echec.demo.repository.UserRepository;
import com.echec.demo.service.PartService;
import com.echec.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("part")
public class PartController {
    @Autowired
    UserService _userService;
    @Autowired
    TournamentRepository _tournamentRepository;

    @Autowired
    PartService _partService;

    @GetMapping("form")
    public ModelAndView getForm() throws UserNotExistException {
        ModelAndView mv = new ModelAndView("createPartForm");
        List<User> users = _userService.findAll();
        mv.addObject("users", users );
        return mv;
    }

    @PostMapping("create-part")
    public String postPart(@RequestParam("title") String title, @RequestParam("user") List<User> users) throws EmptyEmplacement, NotSignInException, NotAdminException {

        Part part = Part.builder().title(title).users(users).build();
        _partService.createPart(part);


        return "redirect:/user/admin/profile";
    }

    @GetMapping("list")
    public ModelAndView getAllParts() {
        ModelAndView mv = new ModelAndView("partList");
        List<Part> parts= _partService.findAllPart();
        mv.addObject("parts",parts);

        return mv;
    }
    @GetMapping("endgame/form/{id}")
    public ModelAndView getFormEndGame(@PathVariable Integer id) throws UserNotExistException {
        Part part = _partService.findById(id);
        ModelAndView mv = new ModelAndView("endGameform");
        mv.addObject("part",part);

        return mv;
    }



    @PostMapping("post")
    public String postEndPart(@RequestParam("user") User user ,@RequestParam("id") Integer id) throws UserNotExistException {
        Part part = _partService.findById(id);

        part.setWinner(user.getPseudo());
        part.setFinish(true);
        if (_partService.winPart(part)){

        return "redirect:/part/list";
        } else {
            return "error";
        }

    }






}
