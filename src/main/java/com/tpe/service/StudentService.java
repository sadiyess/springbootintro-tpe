package com.tpe.service;


import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
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
}
