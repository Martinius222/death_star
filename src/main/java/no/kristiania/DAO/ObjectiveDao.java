package no.kristiania.DAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ObjectiveDao extends AbstractDao<Objective> {
    public ObjectiveDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(Objective operationObjective, PreparedStatement statement) throws SQLException {
        statement.setString(1, operationObjective.getName());

    }

    @Override
    protected Objective readObject(ResultSet resultSet) throws SQLException {
        Objective operationObjective = new Objective();
        operationObjective.setName(resultSet.getString(2));
        operationObjective.setId(resultSet.getInt(1));
        return operationObjective;
    }



    public long insert(Objective operationObjective) throws SQLException{
        long id = insert(operationObjective, "insert into operations (name, description) values (?,?)");
        operationObjective.setId((int)id);
        return id;
    }

    public List<Objective> listAll() throws SQLException {
        return listAll("select * from operations");
    }
    public Objective retrieve(long id) throws SQLException{
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from objectives where id = ?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        return (readObject(resultSet));
                    } else {
                        return null;
                    }
                }
            }
        }
    }
}