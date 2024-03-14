package academy.pocu.comp3500.lab9_pra.data;

public final class Task {
    private final int difficulty;
    private final int profit;

    public Task(int difficulty, int profit) {
        this.difficulty = difficulty;
        this.profit = profit;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "Task{" +
                "difficulty=" + difficulty +
                ", profit=" + profit +
                '}';
    }
}
