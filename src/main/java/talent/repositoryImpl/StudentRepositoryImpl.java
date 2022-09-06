package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Company;
import talent.entity.Course;
import talent.entity.Instructor;
import talent.entity.Student;
import talent.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertStudent(Long companyId, Student student) {
        Company company = entityManager.find(Company.class, companyId);
        company.addStudent(student);
        student.setCompany(company);
        entityManager.persist(student);
    }

    @Override
    public List<Student> getAllStudents(Long studentId) {
        List<Student> students = entityManager
                .createQuery("SELECT s FROM Student s WHERE s.company.companyId = : studentId", Student.class)
                .setParameter("studentId", studentId).getResultList();
        Comparator<Student> comparator = ((o1, o2) -> (int) (o1.getStudentId() - o2.getStudentId()));
        students.sort(comparator);
        return students;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return entityManager.find(Student.class, studentId);
    }

    @Override
    public void editStudent(Long studentId, Student student) {
        Student student1 = entityManager.find(Student.class, studentId);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public void removeStudent(Long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        student.setCourse(null);
        entityManager.remove(student);
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.setCourse(course);
        course.addStudents(student);
        entityManager.merge(student);
        entityManager.merge(course);
    }

}
