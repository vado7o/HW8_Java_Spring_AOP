package pudov.vadim.homeWork5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private-data/executors")
public class ExecutorController {
    private final ExecutorService executorService;

    @Autowired
    public ExecutorController(ExecutorService executorService) { this.executorService = executorService; }

    @GetMapping
    public List<Executor> getAllExecutors() { return executorService.findAll(); }

    @GetMapping("/{id}")
    public Executor getExecutorById(@PathVariable Long id) { return executorService.findById(id); }

    @PostMapping
    public Executor createExecutor(@RequestBody Executor executor) { return executorService.save(executor); }

    @PutMapping("/{id}")
    public Executor changeName(@PathVariable Long id, @RequestBody String name) {
        return executorService.updateExecutor(id, name);
    }
    @DeleteMapping("/{id}")
    public void deleteExecutor(@PathVariable Long id) { executorService.delete(id); }

    @PutMapping("/{id}/task/{taskId}")
    public Executor assignTask(@PathVariable Long id, @PathVariable Long taskId) {
        return executorService.assignTask(id, taskId);
    }
}
