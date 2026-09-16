package vn.edu.pbl4.loadlab.config;
public record TestConfig(
String url,
int concurrency,
int durationSeconds,
int timeoutMs,
int warmupSeconds
) {
public static TestConfig defaults() {
return new TestConfig("http://localhost:8080/ok", 8, 10, 3000, 2);
} public TestConfig {
if (url == null || url.isBlank()) {
throw new IllegalArgumentException("url khong duoc rong");
}if (concurrency < 1) {
throw new IllegalArgumentException("concurrency phai >= 1");
}if (durationSeconds < 1) {
throw new IllegalArgumentException("durationSeconds phai >= 1");
}if (timeoutMs < 1) {
throw new IllegalArgumentException("timeoutMs phai >= 1");
}if (warmupSeconds < 0) {
throw new IllegalArgumentException("warmupSeconds khong duoc am");
        }
    }
}