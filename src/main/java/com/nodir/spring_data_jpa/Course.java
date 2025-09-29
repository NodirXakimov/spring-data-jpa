package com.nodir.spring_data_jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "department", nullable = false, columnDefinition = "TEXT")
    private String department;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "course")
    private List<Enrolment> enrolments = new ArrayList<>();

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void addEnrolment(Enrolment enrolment) {
        if(!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment) {
        enrolments.remove(enrolment);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
