package academy.pocu.comp3500.lab6_pra.leagueofpocu;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public List<String> elements;

    public Test() {
        elements = new ArrayList<>();
    }


    public void leave(int index) {
        String element = elements.get(index);
        element = null;
    }
}
