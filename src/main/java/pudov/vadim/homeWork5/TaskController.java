package pudov.vadim.homeWork5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public-data/tasks")
public class TaskController {
    private final TaskService service;
    @Autowired
    public TaskController(TaskService service) { this.service = service; }
    @GetMapping
    public List<Task> getAllTasks() { return service.getAllTasks(); }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) { return service.getTaskById(id); }

    @GetMapping("/notStarted")
    public List<Task> notStarted() {
        return service.getAllTasks().stream().filter(task -> task.getStatus().equals(Task.Status.notStarted)).collect(Collectors.toList());
    }
    @GetMapping("/done")
    public List<Task> done() {
        return service.getAllTasks().stream().filter(task -> task.getStatus().equals(Task.Status.done)).collect(Collectors.toList());
    }
    @GetMapping("/inProcess")
    public List<Task> inProcess() {
        return service.getAllTasks().stream().filter(task -> task.getStatus().equals(Task.Status.inProcess)).collect(Collectors.toList());
    }
    @PostMapping
    public Task createTask(@RequestBody Task note) { return service.createTask(note); }
    @PutMapping("/{id}")
    public Task changeStatus(@PathVariable Long id, @RequestBody String status) {
        return service.updateTask(id, status); }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) { service.deleteTask(id); }
}
