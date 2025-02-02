package kdg.be.ttbackend.controller.dto.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDTO {

    private Long id;
    private String name;
    private String destination;

    public ParticipantDTO() {
    }

    public ParticipantDTO(Long id, String name, String destination) {
        this.id = id;
        this.name = name;
        this.destination = destination;
    }
}