package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {

    public static boolean isPrime(final BigInteger number) {
        if (number.equals(BigInteger.ONE)) {
            return false;
        }

        BigInteger[] minPrimeNums = {BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(7)};

        for (BigInteger minPrimeNum : minPrimeNums) {
            if (minPrimeNum == number) {
                return true;
            }

            BigInteger remainder = number.mod(minPrimeNum);
            if (remainder.equals(BigInteger.ZERO)) {
                return false; // 나누어 떨어지면 소수가 아님
            }
        }

        return true; // 나누어 떨어지지 않으면 소수일 가능성이 있음
    }
}