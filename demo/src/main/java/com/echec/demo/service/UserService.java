package com.echec.demo.service;

import com.echec.demo.entity.Part;
import com.echec.demo.entity.User;
import com.echec.demo.exeption.UserExistException;
import com.echec.demo.exeption.UserNotExistException;
import com.echec.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private LoginService loginService;

    public boolean signUp(User user){
        try {
            _userRepository.findByPseudo(user.getPseudo());
            throw new UserExistException();
        }catch (Exception e){
            User newUser = user;


            _userRepository.save(newUser);
            return loginService.login(newUser);

        }
    }
    public  void update(User user){
         _userRepository.save(user);

    }

    public boolean signIn(User user) throws UserNotExistException {
        try {
            User userSearch = _userRepository.findByPseudoAndPassword(user.getPseudo(), user.getPassword());
           // userSearch.setAdmin(true);
           //_userRepository.save(userSearch);
            return loginService.login(userSearch);
        } catch (Exception e){
            throw new UserNotExistException();

        }
    }

    public List<User> findAll() throws UserNotExistException {
        List<User> users = (List<User>) _userRepository.findAll();
        if (users.size()!= 0){

        return users;
        } else {
            throw new UserNotExistException();
        }

    }


}
