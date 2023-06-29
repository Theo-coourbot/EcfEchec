package com.todo.demo.repository;

import com.todo.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository <User,Integer> {

    public List<User> findByOOrderByScoreDesc();
}
