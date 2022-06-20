package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment implements Serializable {
    @Id
    @ManyToOne(targetEntity = Driver.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver drivers;

    @Id
    @ManyToOne(targetEntity = Route.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id", nullable = false)
    private Route route;

    @Column(name = "turn_number")
    private int turnNumber;
}
