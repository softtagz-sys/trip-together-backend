package kdg.be.ttbackend.controller.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCreateDTO {

    private String destination;
    private String transportType;
    private Integer maxParticipants;
}
