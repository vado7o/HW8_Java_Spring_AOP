package pudov.vadim.homeWork5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutorRepo extends JpaRepository<Executor, Long> {
}
