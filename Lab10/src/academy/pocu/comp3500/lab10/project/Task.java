package academy.pocu.comp3500.lab10.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Task {
    private final String title;
    private final ArrayList<Task> predecessors = new ArrayList<>(64);
    private int estimate;

    public Task(final String title, final int estimate) {
        this.title = title;
        this.estimate = estimate;
    }

    public String getTitle() {
        return this.title;
    }

    public void addPredecessor(final Task task) {
        this.predecessors.add(task);
    }

    public void addPredecessor(final Task... tasks) {
        for (Task task : tasks) {
            addPredecessor(task);
        }
    }

    public List<Task> getPredecessors() {
        return Collections.unmodifiableList(this.predecessors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        Task task = (Task) o;
        return Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, predecessors, estimate);
    }

    public int getEstimate() {
        return this.estimate;
    }
}
