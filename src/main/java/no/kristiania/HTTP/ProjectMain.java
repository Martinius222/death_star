package no.kristiania.HTTP;

import no.kristiania.DAO.OperationTrooper;
import no.kristiania.DAO.OperationTrooperDao;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class ProjectMain {
    public static void main(String[] args) throws IOException, SQLException {

        Properties properties = new Properties();
        properties.load(new FileReader("deathstar.properties"));
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl((properties.getProperty("dataSource.url")));
        dataSource.setUser((properties.getProperty("dataSource.username")));
        dataSource.setPassword(properties.getProperty("dataSource.password"));
        Flyway.configure().dataSource(dataSource).load().migrate();
        OperationTrooperDao operationTrooperDao = new OperationTrooperDao(dataSource);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        OperationTrooper operationTrooper = new OperationTrooper();

        System.out.println("What would you like to do?");
        System.out.println("A: List all operationTroopers \nB: Add new operationTrooper");
        String choose = scan.next();
        if(choose.equals("A") || choose.equals("a")) {
            System.out.println("Current operationTroopers " + operationTrooperDao.listAll());
        } else if (choose.equals("B") || choose.equals("b")) {
            System.out.println("Please type the name of the new operationTrooper");
            operationTrooper.setName(console.readLine());
            System.out.println("Please type the e-mail for " + operationTrooper.getName());
            operationTrooper.setEmail(console.readLine());

            operationTrooperDao.insert(operationTrooper);

            System.out.println("OperationTrooper added");
        } else {
            System.out.println("That's not an option");
        }

    }

}
