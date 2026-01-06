package org.skypro.exam.repository;

import org.skypro.exam.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> getStudentsByAgeBetween(int min, int max);


    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Long getStudentsCount();


    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Double getAverageAge();


    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

}

