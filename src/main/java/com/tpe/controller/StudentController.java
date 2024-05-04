package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students") //bu classa gelecek isteklerin pathi http://localhost:8081/students +get + post+ put+delete
public class StudentController {

    @Autowired
    private StudentService studentService;



    //Not:getAll()*************************************************************
    @GetMapping //http://localhost:8081/students +  GET
    // 1 --> Student[] olur mu ? Olmaz List<> ile calismamam gerekiyor
// 2 --> Response icinde Status codunu rahat setlemek icin ResponseEntity ..
    public ResponseEntity<List<Student>> getAll(){

        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students); //200 status kodu ile Student nesnelerini client tarafına yonlendirdi
        //return new  ResponseEntity<>(map, HttpStatus.OK); UZUN HALİ
        //jsonla tür dönüşümü olur, students nesnlerim jsona cevrilir
    }

    //Not: CREATE Student ***************************
    @PostMapping //http://localhost:8081/students +  POST + JSON gelmeli
    public ResponseEntity<Map<String,String>> createStudent(@Valid @RequestBody Student student){
        //java jsondan anlamaz gelen nesneyi Student nesnesine cevirmeliyim
        studentService.createStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created succesfully");
        map.put("status", "true");
       return new  ResponseEntity<>(map, HttpStatus.CREATED);
        // return ResponseEntity.ok(map);//bu method 200, 201(CREATED) olması gerekiyor dbye yeni kaydedildi

        // @Valid : parametreler valid mi kontrol eder, bu örenekte Student
        //objesi oluşturmak için  gönderilen fieldlar yani
        //name gibi özellikler düzgün set edilmiş mi ona bakar.
        // @RequestBody = gelen  requestin bodysindeki bilgiyi ,
        //Student objesine map edilmesini sağlıyor.

    }


}
/*
        ***** SORU-1 :  @Controller yerine , @Component kullanirsam ne olur ??
        **    CEVAP-1 : Dispatcher , @Controller ile annote edilmis sınıfları tarar ve
        bunların içindeki @RequestMapping annotationlari algilamaya calisir. Dikkat :
        @Component ile annote edilen siniflar taranmayacaktir..
        Ayrica  @RequestMapping'i yalnızca sınıfları @Controller ile annote edilmis olan
        methodlar üzerinde/içinde kullanabiliriz ve @Component, @Service, @Repository vb.
        ile ÇALIŞMAZ…
        ***** SORU-2 : @RestController ile @Controller arasindaki fark nedir ??
        **   CEVAP-2 : @Controller, Spring MVC framework'ünün bir parçasıdır.genellikle HTML
        sayfalarının görüntülenmesi veya yönlendirilmesi gibi işlevleri gerçekleştirmek
        üzere kullanılır.
                       @RestController annotation'ı, @Controller'dan türetilmiştir ve RESTful
         web servisleri sağlamak için kullanılır.Bir sınıfın üzerine konulduğunda, tüm
         metodlarının HTTP taleplerine JSON gibi formatlarda cevap vermesini sağlar.
         ***** SORU-3 : Controller'dan direk Repo ya gecebilir miyim
         **   CEVAP-3: HAYIR, BusinessLogic ( kontrol ) katmani olan Service'i atlamam gerekir.
 */