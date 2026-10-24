package analysis;

// Veredicto: qué algoritmo fue más rápido, cuál hizo menos operaciones y qué decía la teoría.
public class Recommendation {

    private final GraphProfile profile;
    private final String theoreticalChoice;
    private final String timeWinner;
    private final String operationsWinner;

    public Recommendation(GraphProfile profile, String theoreticalChoice,
                          String timeWinner, String operationsWinner) {
        this.profile = profile;
        this.theoreticalChoice = theoreticalChoice;
        this.timeWinner = timeWinner;
        this.operationsWinner = operationsWinner;
    }

    public String getTheoreticalChoice() {
        return theoreticalChoice;
    }

    public String getTimeWinner() {
        return timeWinner;
    }

    public String getOperationsWinner() {
        return operationsWinner;
    }

    public boolean timeMatchesTheory() {
        return timeWinner.equals(theoreticalChoice);
    }

    public String describe() {
        StringBuilder text = new StringBuilder();
        text.append("  Según la teoría (grafo ").append(profile.getTypeLabel()).append("): ").append(theoreticalChoice).append(System.lineSeparator());
        text.append("  Más rápido en la práctica: ").append(timeWinner).append(System.lineSeparator());
        text.append("  Menos operaciones clave: ").append(operationsWinner).append(System.lineSeparator());

        if (timeMatchesTheory()) {
            text.append(" *** Conclusión: el resultado medido coincide con la teoría.");
        } else {
            text.append(" *** Conclusión: el resultado medido no coincide con la teoría. Con PriorityQueue " + "(heap binario) ambos algoritmos son O(E log V), por eso la diferencia es pequeña.");
        }
        return text.toString();
    }
}