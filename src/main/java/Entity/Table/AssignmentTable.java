package Entity.Table;

import Entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentTable {
    Driver driver;
    private List<RouteTurn> routeTurn;
}
