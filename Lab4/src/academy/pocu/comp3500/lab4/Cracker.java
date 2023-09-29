package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] passwords = new String[userTable.length];
        for (int i = 0; i < rainbowTables.length; i++) {
            for (int j = 0; j < userTable.length; j++) {
                if (rainbowTables[i].contains(userTable[j].getPasswordHash())) {
                    passwords[j] = rainbowTables[i].get(userTable[j].getPasswordHash());
                }
            }
            if (areAllElementsNull(passwords)) {
                return passwords;
            }
        }
        return passwords;
    }

    private static boolean areAllElementsNull(String[] passwords) {
        if (passwords == null) {
            return false; // 배열 자체가 null이면 false 반환
        }

        for (String password : passwords) {
            if (password != null) {
                return true; // 하나라도 null이 아닌 요소가 있으면 true 반환
            }
        }

        return false; // 배열의 모든 요소가 null일 때 false 반환
    }


}