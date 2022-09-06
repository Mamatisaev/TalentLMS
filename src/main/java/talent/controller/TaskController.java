package talent.controller;

import talent.entity.Task;
import talent.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allTasks/{lessonId}")
    private String getAllTasks(@PathVariable("lessonId") Long lessonId, Model model) {
        model.addAttribute("allTasks", taskService.getAllTasks(lessonId));
        model.addAttribute("lessonId", lessonId);
        return "task/taskPage";
    }

    @GetMapping("/addTask/{lessonId}")
    private String addTask(@PathVariable("lessonId") Long lessonId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("lessonId", lessonId);
        return "task/addTask";
    }

    @PostMapping("/insertTask/{lessonId}")
    private String insertTask(@PathVariable("lessonId") Long lessonId,
                              @ModelAttribute("task") Task task) {
        taskService.insertTask(lessonId, task);
        return "redirect:/tasks/allTasks/ " + lessonId;
    }

    @GetMapping("/getTask/{taskId}")
    private String getTaskById(@PathVariable("taskId") Long taskId, Model model) {
        model.addAttribute("task", taskService.getTaskById(taskId));
        return "task/taskPage";
    }

    @GetMapping("/editTask/{taskId}")
    private String editTask(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("lessonId", task.getLessons().getLessonId());
        return "task/editTask";
    }

    @PostMapping("/{lessonId}/{taskId}/saveEditedTask")
    private String saveEditedTask(@PathVariable("lessonId") Long lessonId,
                                  @PathVariable("taskId") Long taskId,
                                  @ModelAttribute("task") Task task) {
        taskService.editTask(taskId, task);
        return "redirect:/tasks/allTasks/ " + lessonId;

    }

    @PostMapping("/{lessonId}/{taskId}/removeTask")
    private String removeTask(@PathVariable("lessonId") Long lessonId,
                              @PathVariable("taskId") Long taskId) {
        taskService.removeTask(taskId);
        return "redirect:/tasks/allTasks/ " + lessonId;
    }
}
