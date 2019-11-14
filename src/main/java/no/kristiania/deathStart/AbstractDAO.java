package no.kristiania.deathStart;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> {
    protected DataSource dataSource;

    public AbstractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected List<T> listAllRows(String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    List<T> workers = new ArrayList<>();
                    while (rs.next()) {
                        workers.add(readFromDb(rs));
                    }
                    return workers;
                }
            }
        }
    }

    protected long insertRow(T worker, String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                writeToDb(worker, stmt);
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getLong("id");
            }
        }
    }

    protected abstract void writeToDb(T worker, PreparedStatement stmt) throws SQLException;

    protected abstract T readFromDb(ResultSet rs) throws SQLException;

    protected T retrieveSingleRow(long id, String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return readFromDb(rs);
                    }
                    return null;
                }
            }
        }
    }
}
