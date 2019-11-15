package no.kristiania.deathStart.Dao;

import no.kristiania.DAO.OperationObjective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationObjectiveTest {

    @Test
    void shouldReturnObjectiveToString() {
        OperationObjective objective = new OperationObjective
                ("Kill John", "He's on the left", 2);
        assertEquals("OperationObjective{OperationObjective Name='Kill John'" +
                "OperationObjective Description='He's on the left'OperationObjective ID=2}", objective.toString());
    }

}
