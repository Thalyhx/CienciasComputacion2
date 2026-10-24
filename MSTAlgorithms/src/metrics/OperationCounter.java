package metrics;

import java.util.Comparator;

// Conteo de las operaciones clave que realiza un algoritmo durante la ejecucion.
public class OperationCounter {
    private long comparisons;
    private long peakAuxiliaryElements;
    // Para prim:
    private long heapInsertions;
    private long heapExtractions;
    // Para Kruskal:
    private long finds;
    private long unions;

    public void countComparison() {
        comparisons++;
    }

    public void countHeapInsertion() {
        heapInsertions++;
    }

    public void countHeapExtraction() {
        heapExtractions++;
    }

    public void countFind() {
        finds++;
    }

    public void countUnion() {
        unions++;
    }

    public void trackAuxiliaryElements(long currentElements) {
        if (currentElements > peakAuxiliaryElements) {
            peakAuxiliaryElements = currentElements;
        }
    }

    // Definir que arista es menor:
    public <T extends Comparable<? super T>> Comparator<T> countingComparator() {
        return (a, b) -> {
            countComparison();
            return a.compareTo(b);
        };
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getHeapInsertions() {
        return heapInsertions;
    }

    public long getHeapExtractions() {
        return heapExtractions;
    }

    public long getFinds() {
        return finds;
    }

    public long getUnions() {
        return unions;
    }

    public long getPeakAuxiliaryElements() {
        return peakAuxiliaryElements;
    }

    public long totalOperations() {
        return comparisons + heapInsertions + heapExtractions + finds + unions;
    }
}