package com.nodir.spring_data_jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
//            Student nodir = new Student(
//                    "Nodir",
//                    "Xakimov",
//                    "nodirxakimov@gmail.com",
//                    28
//            );
//            Student adham = new Student(
//                    "Adham",
//                    "Xakimov",
//                    "adhamxakimov@gmail.com",
//                    25
//            );
//
//            studentRepository.saveAll(List.of(nodir, adham));

            System.out.println(studentRepository.findStudentByEmail("nodirsayfullayevich@gmail.com"));


            studentRepository
                    .findStudentByEmail("nodirsayfullayevich@gmail.com1")
                    .ifPresentOrElse(System.out::println, () -> {
                        System.out.println("No such student exists");
                    });

            studentRepository.findStudentByFirstNameEqualsAndAgeEquals(
                    "Nodir",
                    28
            ).forEach(System.out::println);
            studentRepository
                    .findStudentsByFirstNameEqualsOrAgeEquals("Adham", 28)
                    .forEach(System.out::println);

            System.out.println("Deleting");
            System.out.println(studentRepository.deleteStudentById(1L));
        };
    }

}
