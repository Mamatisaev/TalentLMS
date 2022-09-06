package talent.controller;

import talent.entity.Course;
import talent.entity.Instructor;
import talent.service.InstructorService;
import talent.serviceImpl.CompanyServiceImpl;
import talent.serviceImpl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import talent.serviceImpl.InstructorServiceImpl;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseServiceImpl courseService;
    private final CompanyServiceImpl companyService;
    private final InstructorService instructorService;


    @Autowired
    public CourseController(CourseServiceImpl courseService,
                            CompanyServiceImpl companyService,
                            InstructorServiceImpl instructorService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
    }

    @GetMapping("/allCourses/{companyId}")
    private String getAllCourses(@PathVariable("companyId") Long companyId, Model model,
                                 @ModelAttribute("instruct") Instructor instructor) {
        model.addAttribute("allCourses", courseService.getAllCourses(companyId));
        model.addAttribute("companyId", companyId);
        companyService.getCompanyById(companyId);
        model.addAttribute("instructors", instructorService.getAllInstructors(companyId));
        return "/course/coursePage";
    }

    @GetMapping("/{companyId}/newCourse")
    private String newCourse(@PathVariable("companyId") Long companyId, Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", companyId);
        return "/course/addCourse";
    }

    @PostMapping("{companyId}/saveCourse")
    private String saveCourse(@PathVariable("companyId") Long companyId,
                              @ModelAttribute("newCourse") Course course) {
        courseService.insertCourse(companyId, course);
        return "redirect:/courses/allCourses/ " + companyId;
    }

    @GetMapping("/getCourse/{courseId}")
    private String getCourseById(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "/course/coursePage";
    }

    @GetMapping("/update/{courseId}")
    private String updateCourse(@PathVariable("courseId") Long courseId, Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        model.addAttribute("compId", course.getCompany().getCompanyId());
        return "course/updateCourse";
    }

    @PostMapping("/{companyId}/{courseId}/saveEditedCourse")
    private String saveEditedCourse(@PathVariable("companyId") Long companyId,
                                    @PathVariable("courseId") Long courseId,
                                    @ModelAttribute("course") Course course) {
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/allCourses/ " + companyId;
    }

    @PostMapping("{companyId}/{courseId}/delete")
    private String deleteCourse(@PathVariable("companyId") Long companyId,
                                @PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(companyId);
        return "redirect:/courses/allCourses/ " + courseId;
    }

    @PostMapping("/saveAssignInstructorToCourse/{courseId}/{companyId}")
    private String saveAssign(@PathVariable("courseId") Long courseId,
                              @ModelAttribute("instructor") Instructor instructor,
                              @PathVariable("companyId") Long companyId) {
        instructorService.assignInstructorToCourse(instructor.getInstructorId(), courseId);
        return "redirect:/courses/allCourses/ " + companyId;
    }

}