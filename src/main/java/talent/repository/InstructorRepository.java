package talent.repository;

import talent.entity.Instructor;

import java.util.List;

public interface InstructorRepository {

    void insertInstructor(Long companyId, Instructor instructor);

    List<Instructor> getAllInstructors(Long instructorId);

    Instructor getInstructorById(Long instructorId);

    void editInstructor(Long instructorId, Instructor instructor);

    void removeInstructor(Long instructorId);

    void assignInstructorToCourse(Long instructorId, Long courseId);

}