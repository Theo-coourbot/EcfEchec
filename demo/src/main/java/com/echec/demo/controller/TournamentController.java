package com.echec.demo.controller;

import com.echec.demo.entity.Tournament;
import com.echec.demo.exeption.TournamentNotExistException;
import com.echec.demo.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TournamentController {


    @Autowired
    TournamentService _tournamentService;

    @GetMapping("/")
    public ModelAndView getHome() throws TournamentNotExistException {
      //  _tournamentService.createTournament();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject( "tournament",_tournamentService.serchById(1));
        return mv;
    }
}
