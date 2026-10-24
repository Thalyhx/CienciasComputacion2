package metrics;

import algorithms.MSTResult;

public class ExecutionMetrics {

    private final String algorithmName;
    private final double averageTimeNanos;
    private final OperationCounter operations;
    private final MSTResult result;

    public ExecutionMetrics(String algorithmName, double averageTimeNanos,
                            OperationCounter operations, MSTResult result) {
        this.algorithmName = algorithmName;
        this.averageTimeNanos = averageTimeNanos;
        this.operations = operations;
        this.result = result;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public double getAverageTimeNanos() {
        return averageTimeNanos;
    }

    public double getAverageTimeMicros() {
        return averageTimeNanos / 1_000.0;
    }

    public OperationCounter getOperations() {
        return operations;
    }

    public MSTResult getResult() {
        return result;
    }
}