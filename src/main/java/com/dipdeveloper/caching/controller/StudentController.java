package com.dipdeveloper.caching.controller;

import com.dipdeveloper.caching.entity.Student;
import com.dipdeveloper.caching.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> add(@RequestBody Student studentRequest){
        log.info("Save a student");
        Student studentResponse = studentService.add(studentRequest);
        return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> update(@RequestBody Student studentRequest){
        log.info("Update a student");
        Student studentResponse = studentService.update(studentRequest);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> get(@PathVariable("id") Integer id){
        log.info("Get a student");
        Student studentResponse = studentService.findById(id);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        log.info("Delete a student");
        studentService.delete(id);
        return new ResponseEntity<>("Student deleted successfully.", HttpStatus.OK);
    }
}
