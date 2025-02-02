package kdg.be.ttbackend.controller.api;

import kdg.be.ttbackend.controller.dto.in.GroupCreateDTO;
import kdg.be.ttbackend.controller.dto.out.GroupDTO;
import kdg.be.ttbackend.domain.Group;
import kdg.be.ttbackend.domain.TransportType;
import kdg.be.ttbackend.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create/{roomId}")
    public ResponseEntity<Group> createGroup(@PathVariable Long roomId, @RequestBody GroupCreateDTO groupCreateDTO) {
        try {
            Group group = groupService.createGroup(roomId, convertToGroup(groupCreateDTO));
            return ResponseEntity.ok(group);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid transport type: " + groupCreateDTO.getTransportType(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Error creating group", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        try {
            groupService.deleteGroup(groupId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.error("Group not found", e);
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            logger.error("Cannot delete group with participants", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            logger.error("Error deleting group", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Group convertToGroup(GroupCreateDTO groupCreateDTO) {
        String transportTypeStr = groupCreateDTO.getTransportType().toUpperCase().replace(" ", "_");
        TransportType transportType = TransportType.valueOf(transportTypeStr);
        return new Group(
                groupCreateDTO.getDestination(),
                transportType,
                groupCreateDTO.getMaxParticipants()
        );
    }
}