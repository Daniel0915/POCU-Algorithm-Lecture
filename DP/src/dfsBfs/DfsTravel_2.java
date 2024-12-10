package dfsBfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DfsTravel_2 {
    boolean[] visited;
    ArrayList<String> allRoute;

    public String[] solution(String[][] tickets) {
        String[] answer = {};
        int cnt = 0;
        visited = new boolean[tickets.length];
        allRoute = new ArrayList<>();

        dfs("ICN", "ICN", tickets, cnt);
        for (String route : allRoute) {
            System.out.println(route);
        }

        Collections.sort(allRoute);
        answer = allRoute.get(0).split(" ");
        return answer;
    }

    private void dfs(String start, String route, String[][] tickets, int cnt) {
//        System.out.println("dfs method stack: start = " + start + ", route = " + route + ", cnt = " + cnt);
        if (cnt == tickets.length) {
            allRoute.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            // 첫 시작이 ICN && 방문하지 않을 경우
            if (start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                // 다시 바꿔주는 이뉴는 모든 경로를 탐색하기 위함
                visited[i] = false;
            }
        }

    }
}
