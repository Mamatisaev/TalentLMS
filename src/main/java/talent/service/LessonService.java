package talent.service;

import talent.entity.Lesson;

import java.util.List;

public interface LessonService {

    void insertLesson(Long courseId, Lesson lesson);

    List<Lesson> getAllLessons(Long lessonId);

    Lesson getLessonById(Long lessonId);

    void editLesson(Long lessonId, Lesson lesson);

    void removeLesson(Long lessonId);

}
