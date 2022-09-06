package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Company;
import talent.entity.Course;
import talent.entity.Instructor;
import talent.entity.Student;
import talent.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertInstructor(Long companyId, Instructor instructor) {
        Company company = entityManager.find(Company.class, companyId);
        company.addInstructor(instructor);
        instructor.setCompany(company);
        entityManager.persist(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors(Long instructorId) {
        List<Instructor> instructors = entityManager
                .createQuery("SELECT i FROM Instructor i WHERE i.company.companyId = : instructorId",
                        Instructor.class).setParameter("instructorId", instructorId).getResultList();
        Comparator<Instructor> comparator = ((o1, o2) -> (int) (o1.getInstructorId() - o2.getInstructorId()));
        instructors.sort(comparator);
        return instructors;
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        return entityManager.find(Instructor.class, instructorId);
    }

    @Override
    public void editInstructor(Long instructorId, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, instructorId);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setSpecialization(instructor.getSpecialization());
        entityManager.merge(instructor1);
    }

    @Override
    public void removeInstructor(Long instructorId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        for (Course course : instructor.getCourses()) {
            course.setInstructors(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course = entityManager.find(Course.class, courseId);
        instructor.addCourse(course);
        course.addInstructors(instructor);
        entityManager.merge(instructor);
        entityManager.merge(course);
    }
}