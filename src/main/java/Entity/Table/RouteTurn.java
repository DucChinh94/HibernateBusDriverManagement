package Entity.Table;

import Entity.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteTurn {
    private Route route;
    private int turnNumber;
}
