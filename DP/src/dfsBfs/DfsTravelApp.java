package dfsBfs;

public class DfsTravelApp {
    public static void main(String[] args) {
        DfsTravel_2 dfsTravel2 = new DfsTravel_2();
        String[][] tickets = new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        dfsTravel2.solution(tickets);
    }
}
