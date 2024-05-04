package com.tpe.service;


import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service //özelleştirilmiş component anatationdur, uygulamanın herhangi biryerinde bu classa injektion yapabilirim
public class StudentService {

    //newwleme yapmayacagız enjeksiyon yapacağız

    @Autowired
    private StudentRepository studentRepository;


    //Not:getAll()*************************************************************
    public List<Student> getAll() {
        //handle etmemiz gereken bir durum var mı? kontrol etmemiz gereken YOK
        return studentRepository.findAll(); //SELECT * FROM student
    }

    //Not: CREATE Student ***************************
    public void createStudent(Student student) {
        //handle etmemiz gereken bir durum var mı? KONTROL ETMEMİZ GEREKEN. Email unique mi kontrolü yapılmalı
        if (studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email is already exist");
        }
        studentRepository.save(student);

    }

    //NOT:getStudentById with RequestParam********************************
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student not found with id:" + id)); //null olma durumunda devreye girer
    }

    //NOT:DELETE Student*******************************
    public void deleteStudent(Long id) {
        Student student= findStudent(id);
        studentRepository.deleteById(id);
        //studentRepository.delete(student);
    }

    //NOT:UPDATE Student*******************************
    public void updateStudent(Long id, StudentDTO studentDTO) {
        //id'li öğrenci var mi kontrolü
        Student student= findStudent(id);
    }
}
