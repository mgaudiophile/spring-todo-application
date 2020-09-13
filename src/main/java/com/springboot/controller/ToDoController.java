package com.springboot.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.domain.ToDo;
import com.springboot.repository.ToDoRepository;

@Controller
@RequestMapping("/")
public class ToDoController {

	private ToDoRepository todoRepo;
	
	@Autowired
	public ToDoController(ToDoRepository todoRepo) {
		this.todoRepo = todoRepo;
	}
	
	@GetMapping
	public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
		
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@GetMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<ToDo>> getToDos(@RequestHeader HttpHeaders headers) {
		
		return new ResponseEntity<Iterable<ToDo>>(todoRepo.findAll(), headers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/initdb")
	public ModelAndView initdb(ModelAndView modelAndView, HttpServletRequest request) {
		
		ToDo t1 = new ToDo();
		t1.setDescription("Buy Milk");
		t1.setCreated(LocalDateTime.now());
		t1.setModified(LocalDateTime.now());
		t1.setCompleted(false);
		
		ToDo t2 = new ToDo();
		t2.setDescription("Read a Book");
		t2.setCreated(LocalDateTime.now());
		t2.setModified(LocalDateTime.now());
		t2.setCompleted(false);
		
		ToDo t3 = new ToDo();
		t3.setDescription("Go to Spring One 2020");
		t3.setCreated(LocalDateTime.now());
		t3.setModified(LocalDateTime.now());
		t3.setCompleted(false);
		
		todoRepo.save(t1);
		todoRepo.save(t2);
		todoRepo.save(t3);
		
		modelAndView.setViewName("index");
		return modelAndView;
	}
 }
