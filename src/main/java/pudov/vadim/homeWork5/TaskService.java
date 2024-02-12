package pudov.vadim.homeWork5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ExecutorService executorService;

    public Task createTask(Task task) {
        task.setDate(LocalDate.now());
        return taskRepo.save(task);
    }
    @TrackUserAction
    public List<Task> getAllTasks() { return taskRepo.findAll(); }

    public Task findById(Long id) {
        return taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Задача не найдена!"));
    }

    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Такая задача не найдена :("));
    }
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepo.deleteById(id);
    }
    public Task updateTask(Long id, String status) {
        Task existingTask = getTaskById(id);
        existingTask.setStatus(Task.Status.valueOf(status));
        return taskRepo.save(existingTask);
    }
}
