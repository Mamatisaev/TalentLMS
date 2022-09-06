package talent.controller;

import talent.entity.Lesson;
import talent.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/allLessons/{courseId}")
    private String getAllLessons(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("allLessons", lessonService.getAllLessons(courseId));
        model.addAttribute("courseId", courseId);
        return "lesson/lessonPage";
    }

    @GetMapping("{courseId}/addLesson")
    private String addLesson(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("addLesson", new Lesson());
        model.addAttribute("courseId", courseId);
        return "lesson/addLesson";
    }

    @PostMapping("{courseId}/insertLesson")
    private String insertLesson(@PathVariable("courseId") Long courseId, @ModelAttribute("addLesson") Lesson lesson) {
        lessonService.insertLesson(courseId, lesson);
        return "redirect:/lessons/allLessons/ " + courseId;
    }

    @GetMapping("/getLesson/{lessonId}")
    private String getLessonById(@PathVariable("lessonId") Long id, Model model) {
        model.addAttribute("lesson", lessonService.getLessonById(id));
        return "lesson/lessonPage";
    }

    @GetMapping("/editLesson/{lessonId}")
    private String editLesson(@PathVariable("lessonId") Long lessonId, Model model) {
        Lesson lesson = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", lesson.getCourses().getCourseId());
        return "lesson/editLesson";
    }

    @PostMapping("/{courseId}/{lessonId}/saveEditedLesson")
    private String saveEditedLesson(@PathVariable("courseId") Long courseId,
                                    @PathVariable("lessonId") Long lessonId,
                                    @ModelAttribute("lesson") Lesson lesson) {
        lessonService.editLesson(lessonId, lesson);
        return "redirect:/lessons/allLessons/ " + courseId;
    }

    @PostMapping("/{courseId}/{lessonId}/removeLesson")
    private String removeLesson(@PathVariable("courseId") Long courseId, @PathVariable("lessonId") Long lessonId) {
        lessonService.removeLesson(lessonId);
        return "redirect:/lessons/allLessons/ " + courseId;
    }

}