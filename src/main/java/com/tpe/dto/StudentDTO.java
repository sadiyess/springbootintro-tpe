package com.tpe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpe.domain.Student;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {

    private Long id;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "first name can not be white space")
    @Size(min = 2, max = 25, message = "First name '${validatedValue}' must be between {min} and {max} chars") //'${} burda bi keyword kullanıyrouz
   // @Column(nullable = false, length = 25):gerek yok
    private String firstName; //isimleri değiştirebilirim,tahmin edilemeyecek kolon isimleri belirlenir

    //@Column(nullable = false, length = 25):gerek yok
    private String lastName;

   // @Column:gerek yok
    private Integer grade;

   // @Column(nullable = false, length = 50, unique = true):gerek yok
    @Email(message = "Provide valid email")
    private String email;

    private String phoneNumber;

   /* @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss" , timezone = "Turkey")
    @Setter(AccessLevel.NONE)*/
    private LocalDateTime createDate= LocalDateTime.now();

    //constructor ekleyeceğim
    public StudentDTO(Student student){
        this.id = student.getId();
        this.lastName= student.getLastName();
        this.grade = student.getGrade();
        this.email= student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
        this.createDate = student.getCreateDate();
    }
}
