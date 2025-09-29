package com.nodir.spring_data_jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {

    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "enrolment_student_id_fk"))
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "enrolment_course_id_fk"))
    private Course course;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    public Enrolment(Student student, Course course, LocalDateTime createdAt) {
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment(EnrolmentId id, Student student, Course course, LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

}
