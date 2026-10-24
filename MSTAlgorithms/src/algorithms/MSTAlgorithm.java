package algorithms;

import metrics.OperationCounter;
import model.Graph;

public interface MSTAlgorithm {
    String getName();

    MSTResult run(Graph graph, OperationCounter operationCounter);
}
