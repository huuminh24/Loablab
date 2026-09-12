package vn.edu.pbl4.loadlab;

import vn.edu.pbl4.loadlab.http.HttpRequestSender;
import vn.edu.pbl4.loadlab.metrics.RequestResult;
import vn.edu.pbl4.loadlab.testserver.LocalTestServer;

public class Main {
    public static void main(String[] args) throws Exception {
        LocalTestServer server = new LocalTestServer(8080);
        server.start();

        RequestResult result = new HttpRequestSender()
                .sendGet("http://localhost:8080/ok", 3000);
        System.out.println(result);

        server.stop();
    }
}
