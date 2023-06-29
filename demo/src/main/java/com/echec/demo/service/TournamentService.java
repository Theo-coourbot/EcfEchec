package com.echec.demo.service;

import com.echec.demo.entity.Tournament;
import com.echec.demo.exeption.TournamentNotExistException;
import com.echec.demo.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository _tournamentRepository;


    public void createTournament(){
        Tournament tournament = Tournament.builder().name("crazy chess tournaments").build();
        _tournamentRepository.save(tournament);
    }
    public Tournament serchById(Integer id) throws TournamentNotExistException {
       try {
           return _tournamentRepository.findById(id).get();
       } catch (Exception ex){
           throw new TournamentNotExistException();
       }
    }

}




