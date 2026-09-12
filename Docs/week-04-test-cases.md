# Week 04 Test Cases

| ID | Test Case | Expected Result | Result |
|----|-----------|-----------------|--------|
| T1 | GET /ok, timeout 3000 ms | success=true, status=200 | Not Run |
| T2 | GET /error, timeout 3000 ms | success=false, status=500, HTTP_ERROR | Not Run |
| T3 | GET /slow, timeout 1000 ms | success=false, TIMEOUT | Not Run |
| T4 | Stop server then GET /ok | CONNECTION_ERROR | Not Run |
| T5 | Check latency | latencyNanos > 0 | Not Run |
| T6 | Check bytes /ok | responseBytes = 2 | Not Run |