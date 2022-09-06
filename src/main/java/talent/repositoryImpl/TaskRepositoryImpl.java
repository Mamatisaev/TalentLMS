package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Instructor;
import talent.entity.Lesson;
import talent.entity.Task;
import talent.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertTask(Long lessonId, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.addTasks(task);
        task.setLessons(lesson);
        entityManager.persist(task);
    }

    @Override
    public List<Task> getAllTasks(Long taskId) {
        List<Task> tasks = entityManager
                .createQuery("SELECT t FROM Task t WHERE t.lessons.lessonId = : taskId", Task.class)
                .setParameter("taskId", taskId).getResultList();
        Comparator<Task> comparator = ((o1, o2) -> (int) (o1.getTaskId() - o2.getTaskId()));
        tasks.sort(comparator);
        return tasks;
    }

    @Override
    public Task getTaskById(Long taskId) {
        return entityManager.find(Task.class, taskId);
    }

    @Override
    public void editTask(Long taskId, Task task) {
        Task task1 = entityManager.find(Task.class, taskId);
        task1.setTaskName(task.getTaskName());
        task1.setDeadline(task.getDeadline());
        task1.setTaskText(task.getTaskText());
        entityManager.merge(task1);
    }

    @Override
    public void removeTask(Long taskId) {
        Task task = entityManager.find(Task.class, taskId);
        task.setLessons(null);
        entityManager.remove(task);
    }
}
