package talent.repository;

import talent.entity.Lesson;

import java.util.List;

public interface LessonRepository {

    void insertLesson(Long courseId, Lesson lesson);

    List<Lesson> getAllLessons(Long lessonId);

    Lesson getLessonById(Long lessonId);

    void editLesson(Long lessonId, Lesson lesson);

    void removeLesson(Long lessonId);

}