package no.kristiania.deathStart.Dao;

import no.kristiania.DAO.Objective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationObjectiveTest {

    @Test
    void shouldReturnObjectiveToString() {
        Objective objective = new Objective
                ("Kill John", "He's on the left", 2);
        assertEquals("Objective{Objective Name='Kill John'" +
                "Objective Description='He's on the left'Objective ID=2}", objective.toString());
    }

}
