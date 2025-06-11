https://www.youtube.com/watch?v=JtabtxE8q2o

Logstream format: YYYY/MM/DD<function version><execution version GUID>

- each instance has a dedicated log stream
- As each instance scales up, each concurrent execution has its own log stream
- You can embed custom metrics with your log event

# The report line has following:

- RequestId: Invocation unique request id
- Duration: Time that function handler method spent processing the event.
- Billed duration: Time billed for the invocation
- Memory size: Memory allocated to the function
- Max Memory used: Memory used by the function
- Init duration: Time to load the function and run code  outside the handler method.
- XRAY Information: TraceId, SegmentId, Sampled

