package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Course;
import talent.repository.CourseRepository;
import talent.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void insertCourse(Long companyId, Course course) {
        courseRepository.insertCourse(companyId, course);
    }

    @Override
    public List<Course> getAllCourses(Long courseId) {
        return courseRepository.getAllCourses(courseId);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public void updateCourse(Long courseId, Course course) {
        courseRepository.updateCourse(courseId, course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteCourse(courseId);
    }
}