package talent.repository;

import talent.entity.Task;

import java.util.List;

public interface TaskRepository {

    void insertTask(Long lessonId, Task task);

    List<Task> getAllTasks(Long taskId);

    Task getTaskById(Long taskId);

    void editTask(Long taskId, Task task);

    void removeTask(Long taskId);

}