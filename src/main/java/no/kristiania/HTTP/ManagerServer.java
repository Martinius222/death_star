package no.kristiania.HTTP;

import no.kristiania.DAO.OperationTrooperDao;
import no.kristiania.DAO.OperationTrooperHttpController;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ManagerServer {

    public static void main(String[] args) throws IOException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        Properties properties = new Properties();
        try(FileReader reader = new FileReader("task-manager.properties")){
            properties.load(reader);
        }
        dataSource.setUrl(properties.getProperty("dataSource.url"));
        dataSource.setUser(properties.getProperty("dataSource.username"));
        dataSource.setPassword(properties.getProperty("dataSource.password"));

        Flyway.configure().dataSource(dataSource).load().migrate();


        HttpServer server = new HttpServer(8080);
        server.setFileLocation("src/main/resources/website");
        server.addController("/api/operationTroopers", new OperationTrooperHttpController(new OperationTrooperDao(dataSource)));

        server.start();
    }
}
