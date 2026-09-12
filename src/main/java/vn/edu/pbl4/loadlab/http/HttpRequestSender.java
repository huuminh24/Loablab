package vn.edu.pbl4.loadlab.http;

import vn.edu.pbl4.loadlab.metrics.RequestResult;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class HttpRequestSender {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(3))
            .build();

    public RequestResult sendGet(String url, int timeoutMs) {
        long start = System.nanoTime();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofMillis(timeoutMs))
                    .GET()
                    .build();

            HttpResponse<byte[]> response = client.send(
                    request, HttpResponse.BodyHandlers.ofByteArray());
            long latency = System.nanoTime() - start;

            return RequestResult.success(
                    response.statusCode(), latency, response.body().length);
        } catch (HttpTimeoutException e) {
            return RequestResult.failure("TIMEOUT", System.nanoTime() - start);
        } catch (ConnectException e) {
            return RequestResult.failure("CONNECTION_ERROR", System.nanoTime() - start);
        } catch (IOException e) {
            return RequestResult.failure("IO_ERROR", System.nanoTime() - start);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return RequestResult.failure("INTERRUPTED", System.nanoTime() - start);
        }
    }
}