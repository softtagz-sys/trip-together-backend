package kdg.be.ttbackend.service;

import kdg.be.ttbackend.domain.Group;
import kdg.be.ttbackend.domain.Participant;
import kdg.be.ttbackend.repository.GroupRepository;
import kdg.be.ttbackend.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GroupRepository groupRepository;

    public ParticipantService(ParticipantRepository participantRepository, GroupRepository groupRepository) {
        this.participantRepository = participantRepository;
        this.groupRepository = groupRepository;
    }

    public Participant addParticipant(Participant participant, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        Integer maxParticipants = group.getMaxParticipants();
        if (maxParticipants == null || group.getParticipants().size() < maxParticipants) {
            participant.setGroup(group);
            return participantRepository.save(participant);
        } else {
            throw new IllegalStateException("Group is full");
        }
    }

    public void removeParticipant(Long participantId, Long groupId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        if (participant.getGroup().equals(group)) {
            participantRepository.delete(participant);
        } else {
            throw new IllegalArgumentException("Participant does not belong to the group");
        }
    }
}