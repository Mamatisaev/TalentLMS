package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Company;
import talent.entity.Course;
import talent.entity.Instructor;
import talent.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertCourse(Long companyId, Course course) {
        Company company = entityManager.find(Company.class, companyId);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourses(Long courseId) {
        List<Course> courses = entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();
        Comparator<Course> comparator = ((o1, o2) -> (int) (o1.getCourseId() - o2.getCourseId()));
        courses.sort(comparator);
        return courses;
    }

    @Override
    public Course getCourseById(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    public void updateCourse(Long courseId, Course course) {
        Course course1 = entityManager.find(Course.class, courseId);
        course1.setCourse_name(course.getCourse_name());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setDuration(course.getDuration());
        course1.setImage(course.getImage());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course1 = entityManager.find(Course.class, courseId);
        for (Instructor instructor : course1.getInstructors()) {
            instructor.setCourses(null);
        }
        course1.setCompany(null);
        entityManager.remove(course1);
    }
}
