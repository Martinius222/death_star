package no.kristiania.deathStart;

import no.kristiania.HTTP.HttpClient;
import no.kristiania.HTTP.HttpClientResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTest {


    @Test
    void shouldReadStatusCode() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80,"/echo?body=Houston%20we%20have%20a%20connection");
        HttpClientResponse response = client.executeRequest();
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void shouldReadFailureStatusCode() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80,"/echo?status=401");
        HttpClientResponse response = client.executeRequest();
        assertEquals(401, response.getStatusCode());
    }

    @Test
    void shouldReturnResponseHeader() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80,"/echo?status=302&Location=http://www.example.com");
        HttpClientResponse response = client.executeRequest();
        assertEquals("http://www.example.com", response.getHeader("Location"));
    }

    @Test
    void shouldReturnContentLength() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80,"/echo?status=401");
        HttpClientResponse response = client.executeRequest();
        assertEquals("4", response.getHeader("Content-Length"));
    }

    @Test
    void shouldReturnContentBody() throws IOException {
        HttpClient client = new HttpClient("urlecho.appspot.com", 80,"/echo?body=Houston%20we%20have%20a%20connection");
        HttpClientResponse response = client.executeRequest();
        assertEquals("Houston we have a connection", response.getBody());
    }

}
