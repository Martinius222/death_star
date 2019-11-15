package no.kristiania.DAO;

import jdk.dynalink.Operation;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class OperationTrooperDao extends AbstractDao<OperationTrooper> {

    public OperationTrooperDao(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    protected void insertMember(OperationTrooper member, PreparedStatement statement) throws SQLException {
        statement.setString(1, member.getName());
        statement.setString(2, member.getEmail());

    }

    @Override
    protected OperationTrooper readObject(ResultSet resultSet) throws SQLException {
        OperationTrooper member = new OperationTrooper();

        member.setId(resultSet.getInt(1));
        member.setName(resultSet.getString(2));
        member.setEmail(resultSet.getString(3));
        return member;
    }


    public long insert(OperationTrooper OperationTrooper) throws SQLException {
        long id = insert(OperationTrooper, "insert into OperationTroopers (name,email) values (?,?)");
        OperationTrooper.setId(id);
        return id;
    }

    public List<OperationTrooper> listAll() throws SQLException {
        return listAll("select * from OperationTroopers");
    }

    public OperationTrooper retrieve(long id) throws SQLException{
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from OperationTroopers where id = ?")) {
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
