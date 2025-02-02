package kdg.be.ttbackend.controller.dto.out;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class RoomDTO {

    private Long id;
    private String title;
    private Date date;
    private Set<GroupDTO> groups;
    private String code;
}

