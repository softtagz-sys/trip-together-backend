package kdg.be.ttbackend.controller.api;

import kdg.be.ttbackend.controller.dto.in.ParticipantCreateDTO;
import kdg.be.ttbackend.controller.dto.out.ParticipantDTO;
import kdg.be.ttbackend.domain.Participant;
import kdg.be.ttbackend.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/add/{groupId}")
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantCreateDTO participantCreateDTO, @PathVariable Long groupId) {
        Participant participant = convertToParticipant(participantCreateDTO);
        return ResponseEntity.ok(participantService.addParticipant(participant, groupId));
    }

    @DeleteMapping("/remove/{participantId}/{groupId}")
    public ResponseEntity<Void> removeParticipant(@PathVariable Long participantId, @PathVariable Long groupId) {
        try {
            participantService.removeParticipant(participantId, groupId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private Participant convertToParticipant(ParticipantCreateDTO participantCreateDTO) {
        return new Participant(
                participantCreateDTO.getName(),
                participantCreateDTO.getDestination()
        );
    }
}
