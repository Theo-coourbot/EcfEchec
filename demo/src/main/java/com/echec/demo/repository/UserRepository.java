package com.echec.demo.repository;

import com.echec.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository <User,Integer> {

//    public List<User> findByOOrderByScoreDesc();
    public User findByPseudo(String pseudo);

    public User findByPseudoAndPassword(String psrudo,String password);
}
