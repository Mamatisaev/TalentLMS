package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Course;
import talent.entity.Instructor;
import talent.entity.Lesson;
import talent.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertLesson(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class, courseId);
        course.addLessons(lesson);
        lesson.setCourses(course);
        entityManager.persist(course);
    }

    @Override
    public List<Lesson> getAllLessons(Long lessonId) {
        List<Lesson> lessons = entityManager
                .createQuery("SELECT l FROM Lesson l", Lesson.class).getResultList();
        Comparator<Lesson> comparator = ((o1, o2) -> (int) (o1.getLessonId() - o2.getLessonId()));
        lessons.sort(comparator);
        return lessons;
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return entityManager.find(Lesson.class, lessonId);
    }

    @Override
    public void editLesson(Long lessonId, Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class,lessonId);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public void removeLesson(Long lessonId) {
        entityManager.remove(entityManager.find(Lesson.class, lessonId));
    }
}
