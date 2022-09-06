package talent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import talent.entity.Company;
import talent.entity.Course;
import talent.entity.Student;
import talent.service.CompanyService;
import talent.service.CourseService;
import talent.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, CompanyService companyService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping("/allStudents/{companyId}")
    private String getAllStudents(@PathVariable("companyId") Long companyId, Model model,
                                  @ModelAttribute("course") Course course) {
        model.addAttribute("allStudents", studentService.getAllStudents(companyId));
        model.addAttribute("companyId", companyId);
        Company company = companyService.getCompanyById(companyId);
        List<Course> courses = company.getCourses();
        model.addAttribute("courses", courseService.getAllCourses(companyId));
        return "student/studentPage";
    }

    @GetMapping("/{companyId}/addStudent")
    private String addStudent(@PathVariable("companyId") Long companyId, Model model) {
        model.addAttribute("addStudent", new Student());
        model.addAttribute("companyId", companyId);
        return "student/addStudent";
    }

    @PostMapping("{companyId}/insertStudent")
    private String insertStudent(@PathVariable("companyId") Long companyId,
                                 @ModelAttribute("addStudent") Student student) {
        studentService.insertStudent(companyId, student);
        return "redirect:/students/allStudents/ " + companyId;
    }

    @GetMapping("/getStudent/{studentId}")
    private String getStudentById(@PathVariable("studentId") Long studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        return "student/studentPage";
    }

    @GetMapping("/edit/{studentId}")
    private String editStudent(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        model.addAttribute("companyId", student.getCompany().getCompanyId());
        return "student/editStudent";
    }

    @PostMapping("/{companyId}/{studentId}/saveEditedStudent")
    public String saveEditedStudent(@PathVariable("companyId") Long companyId,
                                    @PathVariable("studentId") Long studentId,
                                    @ModelAttribute("student") Student student) {
        studentService.editStudent(studentId, student);
        return "redirect:/students/allStudents/ " + companyId;
    }

    @PostMapping("/{studentId}/{companyId}/remove")
    private String removeStudent(@PathVariable("studentId") Long studentId,
                                 @PathVariable("companyId") Long companyId) {
        studentService.removeStudent(studentId);
        return "redirect:/students/allStudents/ " + companyId;
    }

    @PostMapping("/{companyId}/{studentId}/assign")
    private String assign(@PathVariable("studentId") Long studentId,
                          @PathVariable("companyId") Long companyId,
                          @ModelAttribute("course") Course course) {
        studentService.assignStudentToCourse(studentId, course.getCourseId());
        return "redirect:/students/allStudents/ " + companyId;
    }

}