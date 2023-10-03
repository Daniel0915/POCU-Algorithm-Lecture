package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashSet;
import java.util.zip.CRC32;

public final class Cracker1 {
    private final User[] users;
    private final String email;
    private final String password;
    private int idx;

    public Cracker1(final User[] uss, final String eml, final String pw) {
        users = uss;
        email = eml;
        password = pw;

        HashSet<String> userSet = new HashSet<>();
        for (User user : users) {
            userSet.add(user.getPasswordHash());
        }

        CRC32 crc32 = new CRC32();
        crc32.update(password.getBytes(StandardCharsets.UTF_8));
        String pwToString = String.valueOf(crc32.getValue());
        if (userSet.contains(pwToString)) {
            idx = 0;
            return;
        }

        String[] instances = new String[]{"MD2", "MD5", "SHA-1", "SHA-256"};
        try {
            for (int i = 0; i < instances.length; ++i) {
                MessageDigest mDigest = MessageDigest.getInstance(instances[i]);
                byte[] hash = mDigest.digest(password.getBytes(StandardCharsets.UTF_8));
                String result = Base64.getEncoder().encodeToString(hash);

                if (userSet.contains(result)) {
                    idx = i + 1;
                    return;
                }
            }
        } catch (Exception e) {

        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        RainbowTable rt = rainbowTables[idx];
        String[] rs = new String[users.length];

        for (int i = 0; i < users.length; ++i) {
            User user = users[i];
            String hash = user.getPasswordHash();
            if (rt.contains(hash)) {
                rs[i] = rt.get(hash);
            }
        }

        return rs;
    }
}