package talent.service;

import talent.entity.Student;

import java.util.List;

public interface StudentService {


    void insertStudent(Long companyId, Student student);

    List<Student> getAllStudents(Long studentId);

    Student getStudentById(Long studentId);

    void editStudent(Long studentId, Student student);

    void removeStudent(Long studentId);

    void assignStudentToCourse(Long studentId, Long courseId);

}
