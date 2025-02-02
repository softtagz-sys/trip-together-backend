package kdg.be.ttbackend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private LocalDateTime departureTime;
    private String description;
    private TransportType transportType;
    private Integer maxParticipants;


    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference
    private Room room;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Participant> participants;

    public Group() {
    }

    public Group(String destination, TransportType transportType) {
        this.destination = destination;
        this.transportType = transportType;
    }

    public Group(String destination, TransportType transportType, Integer maxParticipants) {
        this.destination = destination;
        this.transportType = transportType;
        this.maxParticipants = maxParticipants;
    }
}
