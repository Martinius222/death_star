package no.kristiania.HTTP;

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
        WorkerDAO workerDao = new WorkerDAO(dataSource);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        Worker worker = new Worker();

        System.out.println("What would you like to do?");
        System.out.println("A: List all workers \nB: Add new worker");
        String choose = scan.next();
        if(choose.equals("A") || choose.equals("a")) {
            System.out.println("Current workers " + workerDao.listAll());
        } else if (choose.equals("B") || choose.equals("b")) {
            System.out.println("Please type the name of the new worker");
            worker.setName(console.readLine());
            System.out.println("Please type the e-mail for " + worker.getName());
            worker.setEmail(console.readLine());

            workerDao.insert(worker);

            System.out.println("Worker added");
        } else {
            System.out.println("That's not an option");
        }

    }

}
