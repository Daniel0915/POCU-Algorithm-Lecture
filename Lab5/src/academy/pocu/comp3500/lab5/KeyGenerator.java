package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {

    public static boolean isPrime(final BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }

        if (number.compareTo(BigInteger.TWO) == 0) {
            return true;
        }

        if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        BigInteger sqrt = number.sqrt();
        for (BigInteger i = new BigInteger("3"); i.compareTo(sqrt) <= 0; i = i.add(BigInteger.TWO)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
}