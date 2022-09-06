package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Task;
import talent.repository.TaskRepository;
import talent.service.TaskService;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void insertTask(Long lessonId, Task task) {
    taskRepository.insertTask(lessonId, task);
    }

    @Override
    public List<Task> getAllTasks(Long taskId) {
        return taskRepository.getAllTasks(taskId);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.getTaskById(taskId);
    }

    @Override
    public void editTask(Long taskId, Task task) {
    taskRepository.editTask(taskId, task);
    }

    @Override
    public void removeTask(Long taskId) {
    taskRepository.removeTask(taskId);
    }
}