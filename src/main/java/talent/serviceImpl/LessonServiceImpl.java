package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Lesson;
import talent.repository.LessonRepository;
import talent.service.LessonService;

import java.util.List;
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void insertLesson(Long courseId, Lesson lesson) {
        lessonRepository.insertLesson(courseId, lesson);
    }

    @Override
    public List<Lesson> getAllLessons(Long lessonId) {
        return lessonRepository.getAllLessons(lessonId);
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.getLessonById(lessonId);
    }

    @Override
    public void editLesson(Long lessonId, Lesson lesson) {
        lessonRepository.editLesson(lessonId, lesson);
    }

    @Override
    public void removeLesson(Long lessonId) {
        lessonRepository.removeLesson(lessonId);
    }
}