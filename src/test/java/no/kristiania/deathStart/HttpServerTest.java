package no.kristiania.deathStart;


import no.kristiania.HTTP.HttpClient;
import no.kristiania.HTTP.HttpClientResponse;
import no.kristiania.HTTP.HttpServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpServerTest {
    private HttpServer server;

    @BeforeEach
    void setUp() throws IOException {
        server = new HttpServer(0);
        server.startServer();
    }

    @Test
    void shouldGet200StatusCode() throws IOException {
        HttpClient client = new HttpClient("localhost", server.getPort(), "/echo");
        HttpClientResponse response = client.executeRequest();
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void shouldRequestStatusCode() throws IOException {
        HttpClient client = new HttpClient("localhost", server.getPort(), "/echo?status=401");
        HttpClientResponse response = client.executeRequest();
        assertEquals(401, response.getStatusCode());
    }

    @Test
    void shouldReturnResponseHeader() throws IOException {
        HttpClient client = new HttpClient("localhost", server.getPort(), "/echo?status=302&Location=http://www.example.com");
        HttpClientResponse response = client.executeRequest();
        assertEquals(302, response.getStatusCode());
        assertEquals("http://www.example.com", response.getHeader("Location"));
    }

    @Test
    void shouldReturnContent() throws IOException {
        HttpClient client = new HttpClient("localhost", server.getPort(), "/echo?body=ALOHA!");
        HttpClientResponse response = client.executeRequest();
        assertEquals("6", response.getHeader("Content-Length"));
        assertEquals("ALOHA!", response.getBody());
    }
    @Test
    void shouldReturnFileFromDisk() throws IOException {
        Files.writeString(Paths.get("target/testText.txt"), "This is the actual content of the awesome test text file!");
        server.setFileLocation("target");
        HttpClient httpClient = new HttpClient("localhost", server.getPort(), "/website/testText.txt");
        HttpClientResponse response = httpClient.executeRequest();
        assertEquals("This is the actual content of the awesome test text file!", response.getBody());

    }

}
