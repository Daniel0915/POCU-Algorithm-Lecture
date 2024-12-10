package dfsBfs;

import java.util.*;

public class DfsTravel {
    boolean[] visited;
    ArrayList<String> allRoute;

//    모든 경우의 수를 구한 다음 Collections.sort()를 통해 정렬하기 위해 ArrayList allRoute; 선언
//    처음 출발은 항상 "ICN"에서 해야하기 때문에 DFS를 호출할 때 start 변수에 "ICN"을 가져가고 route의 시작도 "ICN"으로 시작하기 때문에 "ICN"을 넣고 호출한다.
//    DFS함수에서 모든 티켓을 다 썼을 때, allRoute에 구현 경로를 add()한다.
//    연결되어 있는 공항으로 ?꼬리물기?를 하며 티켓의 시작공항이 start와 같고 방문하지 않은 경우 dfs()의 start 자리에 tickets[i][1]을 넣고 재탐색한다.
//    이때 visited[i] = false로 다시 바꿔주는 이유는 모든 경로를 탐색하기 위함이다.
//    모든 경우의 수를 allRoute에 넣은 후 Collections.sort()로 정렬하고 첫번째 문자열을 가져오게 되면 알파벳 순으로 가장 빠른 경로를 구할 수 있다.
//    이때 , 문제에서 요구하는 출력 형태는 String[]임으로 split()을 이용해 문자열을 리스트로 쪼갠다.
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        int cnt = 0;
        visited = new boolean[tickets.length];
        allRoute = new ArrayList<>();

        dfs("ICN", "ICN", tickets, cnt);
        Collections.sort(allRoute);
        answer = allRoute.get(0).split(" ");

        return answer;
    }

    private void dfs(String start, String route, String[][] tickets, int cnt) {
        if (cnt == tickets.length) {
            allRoute.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            // 첫시작인 ICN && 방문하지 않을 경우
            if (start.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route+" "+tickets[i][1], tickets, cnt+1);
                // 다시 바꿔주는 이유는 모든 경로를 탐색하기 위함
                visited[i] = false;
            }
        }
    }
}
