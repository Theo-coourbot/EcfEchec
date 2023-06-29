package com.echec.demo.repository;

import com.echec.demo.entity.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends CrudRepository<Part,Integer> {



}
