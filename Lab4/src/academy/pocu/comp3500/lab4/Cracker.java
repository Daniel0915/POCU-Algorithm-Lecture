package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.zip.CRC32;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    public String getMyPasswordHash() {
        for (User user : userTable) {
            if (Objects.equals(user.getEmail(), email)) {
                return user.getPasswordHash();
            }
        }
        return null;
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        int rainbowTableIndex = 0;

        for (Algorithm algorithm : Algorithm.values()) {
            if (isWhatHash(algorithm, getMyPasswordHash())) {
                rainbowTableIndex = algorithm.getIndex();
                break;
            }
        }

        String[] passwords = new String[userTable.length];

        for (int j = 0; j < userTable.length; j++) {
            if (rainbowTables[rainbowTableIndex].contains(userTable[j].getPasswordHash())) {
                passwords[j] = rainbowTables[rainbowTableIndex].get(userTable[j].getPasswordHash());
            }
        }

        return passwords;
    }

    private boolean crc32(String myPasswordHash) {
        byte[] password = this.password.getBytes();
        CRC32 crc32 = new CRC32();
        crc32.update(password);

        String passwordString = String.valueOf(crc32.getValue());

        if (Objects.equals(myPasswordHash, passwordString)) {
            return true;
        }

        return false;
    }

    private boolean isWhatHash(Algorithm algorithm, String myPasswordHash) {
        if (algorithm == Algorithm.CRC32) {
            return crc32(myPasswordHash);
        }

        try {
            byte[] buffer = this.password.getBytes();
            MessageDigest md = MessageDigest.getInstance(algorithm.getAlgorithmString());
            md.update(buffer);
            byte[] digest = md.digest();

            String passwordString = Base64.getEncoder().encodeToString(digest);
            if (Objects.equals(passwordString, myPasswordHash)) { return true; }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}