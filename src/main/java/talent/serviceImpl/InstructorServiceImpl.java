package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Instructor;
import talent.repository.InstructorRepository;
import talent.service.InstructorService;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void insertInstructor(Long companyId, Instructor instructor) {
        instructorRepository.insertInstructor(companyId, instructor);
    }

    @Override
    public List<Instructor> getAllInstructors(Long instructorId) {
        return instructorRepository.getAllInstructors(instructorId);
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public void editInstructor(Long instructorId, Instructor instructor) {
        instructorRepository.editInstructor(instructorId, instructor);
    }

    @Override
    public void removeInstructor(Long instructorId) {
        instructorRepository.removeInstructor(instructorId);
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        instructorRepository.assignInstructorToCourse(instructorId, courseId);
    }
}