package no.kristiania.deathStart;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkerDAO extends AbstractDAO<Worker> {

    public WorkerDAO(DataSource dataSource) {
        super(dataSource);
    }

    public long insert(Worker worker) throws SQLException {
        long id = insertRow(worker, "insert into workers (name, email) values (?, ?)");
        worker.setId(id);
        return id;
    }

    public List<Worker> listAll() throws SQLException {
        return listAllRows("select * from workers");
    }

    public Worker retrieve(long id) throws SQLException {
        return retrieveSingleRow(id, "select * from workers where id = ?");
    }

    @Override
    protected void writeToDb(Worker worker, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, worker.getName());
        stmt.setString(2, worker.getEmail());
    }

    @Override
    protected Worker readFromDb(ResultSet rs) throws SQLException {
        Worker worker = new Worker();
        worker.setName(rs.getString("name"));
        worker.setEmail(rs.getString("email"));
        return worker;
    }
}
