package vn.edu.pbl4.loadlab.config;
public final class ConfigParser {
private ConfigParser() { }
public static TestConfig parse(String[] args) {
String url = "http://localhost:8080/ok";
int concurrency = 8;
int duration = 10;
int timeout = 3000;
int warmup = 2;
for (int i = 0; i < args.length - 1; i++) {
switch (args[i]) {
case "--url" -> url = args[++i];
case "--concurrency" -> concurrency = Integer.parseInt(args[++i]);
case "--duration" -> duration = Integer.parseInt(args[++i]);
case "--timeout" -> timeout = Integer.parseInt(args[++i]);
case "--warmup" -> warmup = Integer.parseInt(args[++i]);
default -> { }
}
} return new TestConfig(url, concurrency, duration, timeout, warmup);
}
}