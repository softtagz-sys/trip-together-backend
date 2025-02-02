package kdg.be.ttbackend.repository;

import kdg.be.ttbackend.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
