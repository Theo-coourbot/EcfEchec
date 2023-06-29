package com.todo.demo.repository;

import com.todo.demo.entity.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament,Integer> {

}
