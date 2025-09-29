package com.nodir.spring_data_jpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress();
            Integer age = faker.number().numberBetween(1, 60);
            Student student = new Student(firstName, lastName, email, age);

            StudentIdCard studentIdCard = new StudentIdCard("123456798", student);
            student.setStudentIdCard(studentIdCard);

            student.addBook(new Book("Clean Code", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("Think and Grow Rich", LocalDateTime.now().minusDays(10)));
            student.addBook(new Book("Spring Data JPA", LocalDateTime.now()));
            student.addBook(new Book("Hamsa", LocalDateTime.now().minusYears(1)));

            student.addEnrolment(new Enrolment(
                new EnrolmentId(1L, 1L),
                student,
                new Course("Computer Science", "IT"),
                LocalDateTime.now()
            ));

            student.addEnrolment(new Enrolment(
                new EnrolmentId(1L, 2L),
                student,
                new Course("Digital Economy", "Economy"),
                LocalDateTime.now().minusDays(12)
            ));

//            student.enrolToCourse(new Course("Computer Science", "IT"));
//            student.enrolToCourse(new Course("Economy", "Economy"));

            studentRepository.save(student);

            studentRepository.findById(1L)
                    .ifPresent(s -> {
                        System.out.println("Fetching book lazy...");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> {
                            System.out.println(s.getFirstName() + " borrowed " + book.getBookName());
                        });
                    });




//            studentIdCardRepository.findById(1L)
//                    .ifPresent(System.out::println);
//            studentIdCardRepository.deleteById(1L);

        };
    }

//    private static void generateRandomStudents(StudentRepository studentRepository) {
//        Faker faker = new Faker();
//        for(int i = 0; i < 20; i++){
//            String firstName = faker.name().firstName();
//            String lastName = faker.name().lastName();
//            String email = faker.internet().emailAddress();
//            Integer age = faker.number().numberBetween(1, 60);
//            Student student = new Student(firstName, lastName, email, age);
//            studentRepository.save(student);
//        }
//    }

}
