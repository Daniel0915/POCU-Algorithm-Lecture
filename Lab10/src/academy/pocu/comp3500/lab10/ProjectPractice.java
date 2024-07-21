package academy.pocu.comp3500.lab10;

import academy.pocu.comp3500.lab10.project.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class ProjectPractice {

    public static List<String> findSchedule(final Task[] tasks, final boolean includeMaintenance) {

        HashMap<String, Task> transposeGraphTaskHashMap = new HashMap<>();
        HashMap<String, Task> taskHashMap = new HashMap<>();
        ArrayList<Task> startTasks = new ArrayList<>();

        // O(n) -- [1] 전치 그래프, 원본 그래프, 시작 task 들 구하기
        // [1-1] 원본 그래프의 해시맵 채우기 ( 바로 뒤에서 hashmap.contains, hashmap.get 로직 피하기 위해서 )
        // taskHashMap 에는 Predecessors 추가 안함
        for (Task task : tasks) {
            taskHashMap.put(task.getTitle(), new Task(task.getTitle(), task.getEstimate()));
        }

        for (Task task : tasks) {
            // [1-2] 전치 그래프 만들기
            transposeGraphTaskHashMap.put(task.getTitle(), task);

            // [1-3] 원본 그래프 만들기
            Task gTtask = taskHashMap.get(task.getTitle());
            for (Task predecessor : task.getPredecessors()) {
                Task gTpredecessor = taskHashMap.get(predecessor.getTitle());
                if (gTpredecessor.getPredecessors().contains(gTtask) == false) {
                    gTpredecessor.addPredecessor(gTtask);
                }
            }

            // [1-4] 시작 task 구하기
            if (task.getPredecessors().isEmpty()) {
                startTasks.add(gTtask);
            }
        }

        // O(n) -- [2] 후위순회 역순 리스트 생성
        LinkedList<Task> reversedPostTraverseList = new LinkedList<>();
        HashSet<Task> discovered = new HashSet<>();
        for (Task startTask : startTasks) {
            postTraverseDfsRecursive(startTask, reversedPostTraverseList, discovered);
        }

        // O(n) -- [3] 후위순회 역순 리스트 순서대로 전치 그래프를 DFS
        HashSet<String> cycleTasks = new HashSet<>();
        discovered.clear(); // reuse HashSet
        Stack<Task> stackForCycleTrace = new Stack<>();

        for (Task t : reversedPostTraverseList) {
            stackForCycleTrace.clear();
            Task task = transposeGraphTaskHashMap.get(t.getTitle());
            if (discovered.contains(task)) {
                continue;
            }

//            boolean isCycleExist = get
        }

        return null;
    }

    private static void postTraverseDfsRecursive(final Task task,
            final LinkedList<Task> reversedPostTraverseList, final HashSet<Task> discovered) {
        if (discovered.contains(task)) {
            return;
        }

        discovered.add(task);

        for (Task predecessor : task.getPredecessors()) {
            postTraverseDfsRecursive(predecessor, reversedPostTraverseList, discovered);
        }

        reversedPostTraverseList.addFirst(task);
    }


    private static void scheduleTask(Task task, Task[] tasks, Set<String> visited,
            List<String> schedule) {
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

    private static void addMaintenanceCycle(List<String> schedule, Task[] tasks,
            Set<String> visited) {
        for (Task task : tasks) {
            if (!visited.contains(task.getTitle())) {
                scheduleTask(task, tasks, visited, schedule);
            }
        }
    }

    public static List<String> findSchedule2(final Task[] tasks, final boolean includeMaintenance) {
        // 1. G를 DFS 후위 순회(역순) 한다.
        HashMap<String, Task> taskHashMap = new HashMap<>();

        for (Task task : tasks) {
            taskHashMap.put(task.getTitle(), task);
        }

        List<Task> list = new ArrayList<>();

        for (Task task : tasks) {
            Stack<Task> taskStack = new Stack<>();
            addToGraph2(task, list, taskHashMap, taskStack);
        }

        Collections.reverse(list);

        // 2. G^T 를 계산한다.
        HashMap<String, Task> gtGraph = new HashMap<>();

        for (Task task : tasks) {
            gtGraph.put(task.getTitle(), new Task(task.getTitle(), task.getEstimate()));
        }

        for (Task task : tasks) {
            for (String key : taskHashMap.keySet()) {
                if (taskHashMap.get(key).getPredecessors().contains(task)) {
                    gtGraph.get(task.getTitle()).addPredecessor(taskHashMap.get(key));
                }
            }
        }

        // 3. G^T의 각 노드에서 DFS 를 실행한다.
        // 순서대로 방문하며 DFS

        // ssc 변수
        HashMap<Integer, List<Task>> mapSscList = new HashMap<>();

        for (Task task : list) {
            Stack<Task> taskStack = new Stack<>();
            dfs(task, gtGraph, mapSscList, taskStack, mapSscList.size());
        }

        List<String> resultList = new ArrayList<>();

        for (int key : mapSscList.keySet()) {
            System.out.println("key : " + key);
            List<Task> mapValueList = mapSscList.get(key);
            for (Task task : mapValueList) {
                System.out.println(task.getTitle());
                resultList.add(task.getTitle());
            }
            System.out.println();
        }

        return resultList;
    }

    private static void addToGraph(final Task task, List<Task> list, HashMap<String, Task> hashMap, Stack<Task> taskStack) {
        if (list.contains(task)) {
            return;
        }

        // 마지막에 해당되는 거
        // 0 이면 마지막 노드
        int isLast = 0;
        for (String key : hashMap.keySet()) {
            boolean isInclude = false;
            for (Task task1 : list) {
                if (Objects.equals(task1.getTitle(), key)) {
                    isInclude = true;
                    break;
                }
            }

            if (isInclude) {
                continue;
            }

            if (hashMap.get(key).getPredecessors().contains(task)) {
                isLast++;
                if (isLast > 0) {
                    break;
                }
            }
        }

        if (isLast == 0) {
            list.add(task);
            return;
        }

        // 발견
        if (!taskStack.contains(task)) {
            taskStack.push(task);
        } else {
            list.add(taskStack.pop());
            return;
        }

        List<Task> predecessors = task.getPredecessors();

        if (predecessors.isEmpty()) {
            for (String key : hashMap.keySet()) {
                if (hashMap.get(key).getPredecessors().contains(task)) {
                    addToGraph(hashMap.get(key), list, hashMap, taskStack);
                    if (!taskStack.isEmpty()) {
                        list.add(taskStack.pop());
                    }
                    return;
                }
            }
        }

        for (String key : hashMap.keySet()) {
            if (task.getTitle() == hashMap.get(key).getTitle()) {
                continue;
            }

            if (list.contains(hashMap.get(key))) {
                continue;
            }

            if (!hashMap.get(key).getPredecessors().contains(task)) {
                continue;
            }

            addToGraph(hashMap.get(key), list, hashMap, taskStack);
            if (!taskStack.isEmpty()) {
                list.add(taskStack.pop());
            }
            return;
        }
    }

    public static void dfs(Task task, HashMap<String, Task> hashMap, HashMap<Integer, List<Task>> mapList, Stack<Task> stack, int mapKey) {
        if (isIncludeMapList(mapList, task)) {
            return;
        }
        if (stack.contains(task)) {
            addMapList(mapList, stack.pop(), mapKey);
            return;
        } else {
            stack.push(task);
        }

        for (String key : hashMap.keySet()) {
            if (isIncludeMapList(mapList, hashMap.get(key))) {
                continue;
            }

            List<Task> preTasks = hashMap.get(key).getPredecessors();

            if (preTasks.contains(task)) {
                Task nextTask = hashMap.get(key);
                dfs(nextTask, hashMap, mapList, stack, mapKey);
                if (!stack.isEmpty()) {
                    addMapList(mapList,stack.pop(), mapKey);
                }
                return;
            }
        }

        // 다음 노드 대상이 아예 없는 경우
        if (!stack.isEmpty()) {
            addMapList(mapList,stack.pop(), mapKey);
            return;
        }
        addMapList(mapList, task, mapKey);
        return;
    }

    private static void addMapList(HashMap<Integer, List<Task>> mapList, Task task, int mapKey) {
        if (mapList.get(mapKey) == null) {
            mapList.put(mapKey, new ArrayList<>(Arrays.asList(task)));
        } else {
            mapList.get(mapKey).add(task);
        }
    }

    private static boolean isIncludeMapList(HashMap<Integer, List<Task>> mapList, Task task) {
        for (int key : mapList.keySet()) {
            if (mapList.get(key).contains(task)) {
                return true;
            }
        }

        return false;
    }

    private static void addToGraph2(final Task task, List<Task> list, HashMap<String, Task> hashMap, Stack<Task> taskStack) {
        if (list.contains(task)) {
            return;
        }

        if (taskStack.contains(task)) {
            list.add(taskStack.pop());
            return;
        }

        taskStack.push(task);


        for (String key : hashMap.keySet()) {
            if (list.contains(hashMap.get(key))) {
                continue;
            }

            List<Task> preTasks = hashMap.get(key).getPredecessors();

            if (preTasks.contains(task)) {
                Task nextTask = hashMap.get(key);
                addToGraph2(nextTask, list, hashMap, taskStack);
                if (!taskStack.isEmpty()) {





                    list.add(taskStack.pop());
                }
                return;
            }
        }

        if (!taskStack.isEmpty()) {
            list.add(taskStack.pop());
            return;
        }
        list.add(task);
        return;
    }

    public static void searchDepthFirst(HashMap<String, Task> hashMap) {
        Stack<Task> stack = new Stack<>();

//        for ()
//
//
//        stack.push()




    }


}
