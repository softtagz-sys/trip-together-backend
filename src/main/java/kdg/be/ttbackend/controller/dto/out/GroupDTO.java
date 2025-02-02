package kdg.be.ttbackend.controller.dto.out;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GroupDTO {

    private Long id;
    private String destination;
    private String transportType;
    private Integer maxParticipants;
    private Set<ParticipantDTO> participants;

    public GroupDTO() {
    }

    public GroupDTO(Long id, String destination, String transportType, Integer maxParticipants, Set<ParticipantDTO> participants) {
        this.id = id;
        this.destination = destination;
        this.transportType = transportType;
        this.maxParticipants = maxParticipants;
        this.participants = participants;
    }
}