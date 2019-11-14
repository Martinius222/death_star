package no.kristiania.deathStart;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;



public class WorkerDAOTest {

    private JdbcDataSource dataSource;
    private WorkerDAO dao;

    @BeforeEach
    void testDataSource() {
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(dataSource).load().migrate();
        dao = new WorkerDAO(dataSource);
    }

    @Test
    void shouldListInsertedWorkers() throws SQLException {
        Worker worker = sampleWorker();
        dao.insert(worker);
        assertThat(dao.listAll())
                .contains(worker);
    }

    @Test
    void shouldRetrieveWorkerById() throws SQLException {
        Worker worker = sampleWorker();

        long id = dao.insert(worker);
        assertThat(worker.getId()).isEqualTo(id);

        assertThat(dao.retrieve(id))
                .isEqualTo(worker);
    }

    private Worker sampleWorker() {
        Worker worker = new Worker();
        worker.setName(sampleWorkerName());
        worker.setEmail(sampleEmail());
        return worker;
    }

    private String sampleEmail() {
        return "test@tester.com";
    }

    private String sampleWorkerName() {
        String[] alternatives = {
                "BROhannes", "Tired Java Developer", "NeiHannes"
        };
        return alternatives[new Random().nextInt(alternatives.length)];
    }



}