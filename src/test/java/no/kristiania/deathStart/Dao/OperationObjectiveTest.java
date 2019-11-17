package no.kristiania.deathStart.Dao;

import no.kristiania.DAO.Objective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationObjectiveTest {

    @Test
    void shouldReturnObjectiveToString() {
        Objective objective = new Objective
                ("Find Luke Skywalker", "He's on the run, find him now", 2, "Ongoing");
        assertEquals("Objective{Objective Name='Kill John'" +
                "Objective Description='He's on the left'Objective ID=2}", objective.toString());
    }

}
