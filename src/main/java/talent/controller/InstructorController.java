package talent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import talent.entity.Instructor;
import talent.service.CourseService;
import talent.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/allInstructors/{companyId}")
    private String getAllInstructors(@PathVariable("companyId") Long companyId, Model model) {
        model.addAttribute("allInstructors", instructorService.getAllInstructors(companyId));
        model.addAttribute("companyId", companyId);
        model.addAttribute("instructors", courseService.getAllCourses(companyId));
        return "instructor/instructorPage";
    }


    @GetMapping("{companyId}/newInstructor")
    private String newInstructor(@PathVariable("companyId") Long companyId, Model model) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("companyId", companyId);
        return "instructor/addInstructor";
    }

    @PostMapping("{companyId}/insertInstructor")
    private String insertInstructor(@PathVariable("companyId") Long companyId,
                                  @ModelAttribute("newInstructor") Instructor instructor) {
        instructorService.insertInstructor(companyId, instructor);
        return "redirect:/instructors/allInstructors/ " + companyId;
    }

    @GetMapping("/getInstructor/{instructorId}")
    private String getInstructorById(@PathVariable("instructorId") Long instructorId, Model model) {
        model.addAttribute("instructorId", instructorService.getInstructorById(instructorId));
        return "instructor/instructorPage";
    }

    @GetMapping("/editInstructor/{instructorId}")
    private String editInstructor(@PathVariable("instructorId") Long instructorId, Model model) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        model.addAttribute("instructor", instructor);
        model.addAttribute("companyId", instructor.getCompany().getCompanyId());
        return "instructor/editInstructor";
    }

    @PostMapping("/{companyId}/{instructorId}/saveEditedInstructor")
    private String saveEditedInstructor(@PathVariable("companyId") Long companyId,
                              @PathVariable("instructorId") Long instructorId,
                              @ModelAttribute("instructor") Instructor instructor) {
        instructorService.editInstructor(instructorId, instructor);
        return "redirect:/instructors/allInstructors/ " + companyId;
    }

    @PostMapping("/{id}/{instructorId}/remove")
    private String removeInstructor(@PathVariable("id") Long id, @PathVariable("instructorId") Long instructorId) {
        instructorService.removeInstructor(instructorId);
        return "redirect:/instructors/allInstructors/" + id;
    }


}