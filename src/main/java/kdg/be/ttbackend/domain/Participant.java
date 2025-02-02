package kdg.be.ttbackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String destination;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    public Participant() {
    }

    public Participant(String name, String destination) {
        this.name = name;
        this.destination = destination;
    }
}