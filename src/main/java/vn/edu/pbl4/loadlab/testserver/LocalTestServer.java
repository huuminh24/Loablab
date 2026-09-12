package vn.edu.pbl4.loadlab.testserver;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
public class LocalTestServer {
    private final HttpServer server;
    public LocalTestServer(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ok", ex -> reply(ex, 200, "OK"));
        server.createContext("/error", ex -> reply(ex, 500, "ERROR"));
        server.createContext("/slow", ex -> {
            try { Thread.sleep(4000); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            reply(ex, 200, "SLOW");
        });
    }
    private static void reply(com.sun.net.httpserver.HttpExchange ex,
                              int status, String text) throws IOException {
        byte[] body = text.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(status, body.length);
        ex.getResponseBody().write(body);
        ex.close();
    }
    public void start() { server.start(); }
    public void stop() { server.stop(0); }
    public static void main(String[] args) throws Exception {
        new LocalTestServer(8080).start();
        System.out.println("Server: http://localhost:8080/ok");
    }
}