package com.dipdeveloper.caching.service;

import com.dipdeveloper.caching.entity.Student;
import com.dipdeveloper.caching.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    private final String MY_CACHE_NAME = "student";

    @CachePut(value = MY_CACHE_NAME, key = "#result.id")
    public Student add(Student studentRequest) {
        log.info("Database call to save a student");
        return studentRepository.save(studentRequest);
    }

    @CachePut(value = MY_CACHE_NAME, key = "#result.id")
    public Student update(Student studentRequest) {
        log.info("Database call to update a student");
        Optional<Student> optionalStudent = studentRepository.findById(studentRequest.getId());
        if(!optionalStudent.isPresent()){
            throw new RuntimeException("Student Not Found.");
        }
        Student student = optionalStudent.get();
        student.setEmail(studentRequest.getEmail());
        return studentRepository.save(student);
    }

    @Cacheable(value = MY_CACHE_NAME, key = "#id")
    public Student findById(Integer id) {
        log.info("Database call to Get a student");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        throw new RuntimeException("Student Not Found.");
    }

    @CacheEvict(value = MY_CACHE_NAME, key = "#id")
    public void delete(Integer id) {
        log.info("Database call to delete a student");
        studentRepository.deleteById(id);
    }
}
