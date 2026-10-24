package metrics;

import java.util.List;
import java.util.Locale;

public class MetricsTable {

    public String format(List<ExecutionMetrics> metrics) {
        StringBuilder table = new StringBuilder();
        String rowFormat = "%-10s %14s %14s %11s %11s %9s %9s %16s%n";

        table.append(String.format(Locale.ROOT, rowFormat, "Algoritmo", "Tiempo (µs)", "Comparaciones",
                "Ins. heap", "Ext. heap", "Finds", "Unions", "Mem. aux (elem)"));

        for (ExecutionMetrics m : metrics) {
            OperationCounter ops = m.getOperations();
            table.append(String.format(Locale.ROOT, rowFormat,
                    m.getAlgorithmName(),
                    String.format(Locale.ROOT, "%.2f", m.getAverageTimeMicros()),
                    ops.getComparisons(),
                    ops.getHeapInsertions(),
                    ops.getHeapExtractions(),
                    ops.getFinds(),
                    ops.getUnions(),
                    ops.getPeakAuxiliaryElements()));
        }
        return table.toString();
    }
}