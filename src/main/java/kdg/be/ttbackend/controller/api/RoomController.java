package kdg.be.ttbackend.controller.api;

import kdg.be.ttbackend.controller.dto.in.RoomCreateDTO;
import kdg.be.ttbackend.controller.dto.out.GroupDTO;
import kdg.be.ttbackend.controller.dto.out.ParticipantDTO;
import kdg.be.ttbackend.controller.dto.out.RoomDTO;
import kdg.be.ttbackend.domain.Group;
import kdg.be.ttbackend.domain.Participant;
import kdg.be.ttbackend.domain.Room;
import kdg.be.ttbackend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody RoomCreateDTO roomCreateDTO) {
        Room room = convertToRoom(roomCreateDTO);
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping("/{code}")
    public ResponseEntity<RoomDTO> getRoomByCode(@PathVariable String code) {
        return roomService.findByCode(code)
                .map(room -> ResponseEntity.ok(convertToRoomDTO(room)))
                .orElse(ResponseEntity.notFound().build());
    }

    private RoomDTO convertToRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setTitle(room.getTitle());
        roomDTO.setDate(room.getDate());
        roomDTO.setGroups(room.getGroups().stream()
                .map(this::convertToGroupDTO)
                .collect(Collectors.toSet()));
        roomDTO.setCode(room.getCode());
        return roomDTO;
    }

    private GroupDTO convertToGroupDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setDestination(group.getDestination());
        groupDTO.setTransportType(group.getTransportType().name());
        groupDTO.setMaxParticipants(group.getMaxParticipants());
        groupDTO.setParticipants(group.getParticipants().stream()
                .map(this::convertToParticipantDTO)
                .collect(Collectors.toSet()));
        return groupDTO;
    }

    private ParticipantDTO convertToParticipantDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setName(participant.getName());
        participantDTO.setDestination(participant.getDestination());
        return participantDTO;
    }

    private Room convertToRoom(RoomCreateDTO roomCreateDTO) {
        return new Room(
                roomCreateDTO.getTitle(),
                roomCreateDTO.getDate()
        );
    }
}