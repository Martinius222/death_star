package no.kristiania.DAO;

import no.kristiania.HTTP.HttpController;
import no.kristiania.HTTP.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class TrooperHttpController implements HttpController {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(TrooperHttpController.class);
    private TrooperDao trooperDao;


    public TrooperHttpController(TrooperDao trooperDao) {
        this.trooperDao = trooperDao;
    }

    @Override
    public void handle(String requestAction, String requestPath, Map<String, String> requestParameters,
                       String requestBody, OutputStream outputStream) throws IOException {
        try {
            if (requestAction.equalsIgnoreCase("POST")) {
                requestParameters = HttpServer.parseRequestParameters(requestBody);
                Trooper trooper = new Trooper();
                trooper.setName(requestParameters.get("memberName"));
                trooper.setEmail(requestParameters.get("email"));
                trooperDao.insert(trooper);
                return;
            }

            String statusCode = requestParameters.getOrDefault("status", "200");
            String location = requestParameters.get("location");
            String body = requestParameters.getOrDefault("body", getBody());

            outputStream.write(("HTTP/1.0 " + statusCode + " OK\r\n" +

                    "Content-length: " + body.length() + "\r\n" +
                    (location != null ? "Location: " + location + "\r\n" : "") +
                    "\r\n" +
                    body).getBytes());
        } catch (SQLException e) {
            Logger.error("While handling request{}", requestPath, e);
            String message = e.toString();
            outputStream.write(("HTTP/1.0 500 Internal server error\r\n" +
                    "Content-length: " + message.length() + "\r\n" +
                    "\r\n" +
                    message).getBytes());
        }

    }

    public String getBody() throws SQLException {
        String body = trooperDao.listAll().stream()
                .map( p -> String.format( "<option value='%s'>%s</option>", p.getId(), p.getName() ) )
                .collect( Collectors.joining( "" ) );
        return body;
    }
}
