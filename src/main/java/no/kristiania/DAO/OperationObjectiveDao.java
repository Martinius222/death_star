package no.kristiania.DAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperationObjectiveDao extends AbstractDao<OperationObjective> {
    public OperationObjectiveDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void insertMember(OperationObjective operationObjective, PreparedStatement statement) throws SQLException {
        statement.setString(1, operationObjective.getName());

    }

    @Override
    protected OperationObjective readObject(ResultSet resultSet) throws SQLException {
        OperationObjective operationObjective = new OperationObjective();
        operationObjective.setName(resultSet.getString(2));
        operationObjective.setId(resultSet.getInt(1));
        return operationObjective;
    }



    public long insert(OperationObjective operationObjective) throws SQLException{
        long id = insert(operationObjective, "insert into operations (operation_name) values (?)");
        operationObjective.setId((int)id);
        return id;
    }

    public List<OperationObjective> listAll() throws SQLException {
        return listAll("select * from operations");
    }
    public OperationObjective retrieve(long id) throws SQLException{
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from operations where id = ?")) {
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
