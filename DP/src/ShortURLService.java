import java.util.HashMap;
import java.util.Map;

public class ShortURLService {
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62_CHARS.length();
    private static final int MAX_HASH_LENGTH = 10;

    private long counter = 1; // 고유 번호
    private final Map<Long, String> idToUrl = new HashMap<>();
    private final Map<String, Long> urlToId = new HashMap<>();

    /**
     * 원본 URL을 단축 URL로 변환
     */
    public String generateShortURL(String originalURL) {
        if (urlToId.containsKey(originalURL)) {
            return encodeBase62(urlToId.get(originalURL));
        }
        urlToId.put(originalURL, counter);
        idToUrl.put(counter, originalURL);
        String shortHash = encodeBase62(counter++);
        return shortHash.substring(0, Math.min(shortHash.length(), MAX_HASH_LENGTH));
    }

    /**
     * 단축 URL의 값(인코딩)을 사용하여 원본 URL 조회
     */
    public String getOriginalURL(String shortURL) {
        long id = decodeBase62(shortURL);
        return idToUrl.getOrDefault(id, null);
    }

    /**
     * Base62로 숫자를 인코딩
     */
    private String encodeBase62(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62_CHARS.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.reverse().toString();
    }

    /**
     * Base62 문자열을 숫자로 디코딩
     */
    private long decodeBase62(String shortURL) {
        long id = 0;
        for (char c : shortURL.toCharArray()) {
            System.out.println("c = " + c);
            id = id * BASE + BASE62_CHARS.indexOf(c);
            System.out.println("id = " + id);
        }
        return id;
    }

    public static void main(String[] args) {
        ShortURLService service = new ShortURLService();

        for (int index = 0; index < 1; index++) {
            // 원본 URL 예제
            String originalURL = "https://www.example.com/some/long/path?query=123" + "&index=" + index;

            // 1. Short URL 생성
            String shortURL = service.generateShortURL(originalURL);

            System.out.println("service.idToUrl = " + service.idToUrl);
            System.out.println("service.urlToId = " + service.urlToId);

            System.out.println("Original URL: " + originalURL);
            System.out.println("Short URL: " + "http://short.url/" + shortURL);

            // 2. Short URL 클릭 시 원본 URL로 리다이렉트
            String resolvedURL = service.getOriginalURL(shortURL);
            if (resolvedURL != null) {
                System.out.println("Redirecting to: " + resolvedURL);
            } else {
                System.out.println("Original URL not found!");
            }
        }
    }
}
