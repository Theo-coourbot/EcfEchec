package com.echec.demo.service;

import com.echec.demo.entity.Part;
import com.echec.demo.entity.User;
import com.echec.demo.exeption.EmptyEmplacement;
import com.echec.demo.exeption.NotAdminException;
import com.echec.demo.exeption.NotSignInException;
import com.echec.demo.exeption.UserNotExistException;
import com.echec.demo.repository.PartRepository;
import com.echec.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.hibernate.IdentifierLoadAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartRepository _partRepository;

    @Autowired
    private LoginService _loginService;
    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private HttpSession _session;

    public boolean createPart(Part part) throws EmptyEmplacement, NotAdminException, NotSignInException {
        if(_loginService.isLogged()) {
            if(_loginService.isAdmin()) {
                if(part.getTitle() == null || part.getUsers().size() != 2) {

                    throw new EmptyEmplacement();
                }

                _partRepository.save(part);

                return true;
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }

    public boolean winPart(Part part) throws UserNotExistException {

        User user = _userRepository.findByPseudo(part.getWinner());
        if (user != null){
            user.setScore(user.getScore()+1);
            _userRepository.save(user);
            _partRepository.save(part);

        } else {
            throw new UserNotExistException();
        }
        return true;
    }

    public List<Part> findAllPart(){
        return (List<Part>) _partRepository.findAll();

    }
    public Part findById(Integer id){
        return _partRepository.findById(id).get();
    }






}
