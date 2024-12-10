import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62_CHARS.length();

    private long counter = 1; // 고유 ID 생성용
    private final Map<Long, String> idToUrl = new HashMap<>();
    private final Map<String, Long> urlToId = new HashMap<>();

    // URL을 짧은 형태로 변환
    public String shortenURL(String longURL) {
        if (urlToId.containsKey(longURL)) {
            return encodeBase62(urlToId.get(longURL));
        }
        urlToId.put(longURL, counter);
        idToUrl.put(counter, longURL);
        return encodeBase62(counter++);
    }

    // 짧은 URL을 원래 URL로 복원
    public String expandURL(String shortURL) {
        long id = decodeBase62(shortURL);
        return idToUrl.getOrDefault(id, null);
    }

    // Base62로 ID를 인코딩
    private String encodeBase62(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62_CHARS.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.reverse().toString();
    }

    // Base62를 ID로 디코딩
    private long decodeBase62(String shortURL) {
        long id = 0;
        for (char c : shortURL.toCharArray()) {
            id = id * BASE + BASE62_CHARS.indexOf(c);
        }
        return id;
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();

        String originalURL = "https://www.example.com/some/long/url";
        String shortURL = shortener.shortenURL(originalURL);
        System.out.println("Shortened URL: " + shortURL);

        String expandedURL = shortener.expandURL(shortURL);
        System.out.println("Expanded URL: " + expandedURL);
    }
}
