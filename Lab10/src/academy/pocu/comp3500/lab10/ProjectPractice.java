package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectPractice {
    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {
        List<String> schedule = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();

        for (Task task : tasks) {
            if (!visited.contains(task.getTitle())) {
                scheduleTask(task, tasks, visited, schedule);
            }
        }

        if (includeMaintenance) {
            addMaintenanceCycle(schedule, tasks, visited);
        }

        return schedule;
    }


    private static void scheduleTask(Task task, Task[] tasks, Set<String> visited, List<String> schedule) {
        visited.add(task.getTitle());

        if (task.getPredecessors() != null) {
            for (Task predecessor : task.getPredecessors()) {
                if (!visited.contains(predecessor.getTitle())) {
                    scheduleTask(predecessor, tasks, visited, schedule);
                }
            }
        }

        schedule.add(task.getTitle());
    }

    private static void addMaintenanceCycle(List<String> schedule, Task[] tasks, Set<String> visited) {
        for (Task task : tasks) {
            if (!visited.contains(task.getTitle())) {
                scheduleTask(task, tasks, visited, schedule);
            }
        }
    }
}
