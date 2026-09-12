package vn.edu.pbl4.loadlab.metrics;
public record RequestResult(
        boolean success,
        int statusCode,
        long latencyNanos,
        int responseBytes,
        String errorType
) {
    public static RequestResult success(
            int statusCode, long latencyNanos, int responseBytes) {
        boolean ok = statusCode >= 200 && statusCode < 400;
        return new RequestResult(ok, statusCode, latencyNanos,
                responseBytes, ok ? "NONE" : "HTTP_ERROR");
    }
    public static RequestResult failure(String errorType, long latencyNanos) {
        return new RequestResult(false, -1, latencyNanos, 0, errorType);
    }
}