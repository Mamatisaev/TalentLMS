package talent.service;

import talent.entity.Course;

import java.util.List;

public interface CourseService {

    void insertCourse(Long companyId, Course course);

    List<Course> getAllCourses(Long courseId);

    Course getCourseById(Long courseId);

    void updateCourse(Long courseId, Course course);

    void deleteCourse(Long courseId);

}
