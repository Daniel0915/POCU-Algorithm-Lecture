package bruteForce;

import java.util.ArrayList;
import java.util.List;

// https://velog.io/@jh5253/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AA%A8%EC%9D%8C%EC%82%AC%EC%A0%84-Java%EC%9E%90%EB%B0%94
public class DictionaryDfs {
    static List<String> list = new ArrayList<>();
    static String [] words = {"A", "E", "I", "O", "U"};

    public int solution(String word) {
        int answer = 0;
        dfs("", 0);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    private void dfs(String str, int len) {
        list.add(str);
        if (len == 5) {return;}
        for (int i = 0; i < words.length; i++) {
            dfs(str + words[i], len + 1);
        }
    }
}
