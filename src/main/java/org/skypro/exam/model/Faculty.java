package org.skypro.exam.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_seq"
    )
    @SequenceGenerator(
            name = "faculty_seq",
            sequenceName = "faculty_sequence",
            allocationSize = 1
    )

    private Long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    ///Это не нужно, потому что теперь есть Ломбок, но оставлю, на случай, если Ломбок нельзя использовать///
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


}
