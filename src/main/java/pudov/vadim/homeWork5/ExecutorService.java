package pudov.vadim.homeWork5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutorService {
    @Autowired
    private ExecutorRepo executorRepo;

    @Autowired
    @Lazy
    private TaskService taskService;

    public Executor updateExecutor(Long id, String name) {
        Executor existingExecutor = findById(id);
        existingExecutor.setName(name);
        return executorRepo.save(existingExecutor);
    }

    @TrackUserAction
    public List<Executor> findAll() { return executorRepo.findAll(); }

    public Executor findById(Long id) {
        return executorRepo.findById(id).orElseThrow(() -> new RuntimeException("Executor not found"));
    }

    public Executor save(Executor executor) { return executorRepo.save(executor); }

    public void delete(Long id) { executorRepo.deleteById(id);}

    public Executor assignTask(Long id, Long taskId) {
        Executor existingExecutor = findById(id);
        Task task = taskService.findById(taskId);
        existingExecutor.setTask(task);
        return executorRepo.save(existingExecutor);
    }
}


