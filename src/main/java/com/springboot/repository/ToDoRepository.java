package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {

}
