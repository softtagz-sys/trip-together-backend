package kdg.be.ttbackend.controller.dto.in;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoomCreateDTO {

    private String title;
    private Date date;
}
