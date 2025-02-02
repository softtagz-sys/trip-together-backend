package kdg.be.ttbackend.repository;

import kdg.be.ttbackend.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
