package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Student;
import talent.repository.StudentRepository;
import talent.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void insertStudent(Long companyId, Student student) {
        studentRepository.insertStudent(companyId, student);
    }

    @Override
    public List<Student> getAllStudents(Long studentId) {
        return studentRepository.getAllStudents(studentId);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public void editStudent(Long studentId, Student student) {
        studentRepository.editStudent(studentId, student);
    }

    @Override
    public void removeStudent(Long studentId) {
        studentRepository.removeStudent(studentId);
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        studentRepository.assignStudentToCourse(studentId, courseId);
    }
}