package kdg.be.ttbackend.service;

import kdg.be.ttbackend.domain.Room;
import kdg.be.ttbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class RoomService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private final RoomRepository roomRepository;
    private final int codeLength;

    public RoomService(RoomRepository roomRepository, @Value("${code.length}") int codeLength) {
        this.roomRepository = roomRepository;
        this.codeLength = codeLength;
    }

    public Room createRoom(Room room) {
        room.setCode(generateUniqueCode());
        return roomRepository.save(room);
    }

    public Optional<Room> findByCode(String code) {
        return roomRepository.findByCode(code);
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (roomRepository.findByCode(code).isPresent());
        return code;
    }

    private String generateRandomCode() {
        StringBuilder code = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}