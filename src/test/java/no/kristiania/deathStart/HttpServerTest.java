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
    @Test
    void shouldExecuteHttprequest() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo");
        assertEquals(200, client.execute("GET").getStatusCode());
    }

    @Test
    void shouldReadStatusCode() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?status=401");
        assertEquals(401, client.execute("GET").getStatusCode());
    }

    @Test
    void shouldReadHeaders() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?content-type=text/plain");
        assertEquals("text/plain; charset=utf-8", client.execute("GET").getHeader("Content-type"));
    }

    @Test
    void shouldReadContentLength() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?body=hello+world!");
        assertEquals(12, client.execute("GET").getContentLenght());
    }

    @Test
    void shouldReadBody() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80, "/echo?body=hello+world!");
        assertEquals("hello world!", client.execute("GET").getBody());
    }

}