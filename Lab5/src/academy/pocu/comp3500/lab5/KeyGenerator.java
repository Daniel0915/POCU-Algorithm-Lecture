package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {

    public static boolean isPrime(final BigInteger number) {

        if (number.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }

        if (number.compareTo(new BigInteger("3")) <= 0) {
            return true;
        }

        if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        BigInteger[] smallPrimes = new BigInteger[0];

        if (number.compareTo(new BigInteger("1373653")) == -1) {
            smallPrimes = new BigInteger[]{new BigInteger("2"), new BigInteger("3")};
        } else if (number.compareTo(new BigInteger("9080191")) == -1) {
            smallPrimes = new BigInteger[]{new BigInteger("31"), new BigInteger("73")};
        } else if (number.compareTo(new BigInteger("4759123141")) == -1) {
            smallPrimes = new BigInteger[]{new BigInteger("2"), new BigInteger("7"), new BigInteger("61")};
        } else if (number.compareTo(new BigInteger("2152302898747")) == -1) {
            smallPrimes = new BigInteger[]{new BigInteger("2"), new BigInteger("3"), new BigInteger("5"), new BigInteger("7"), new BigInteger("11")};
        } else if (number.compareTo(new BigInteger("3474749660383")) == -1) {
            smallPrimes = new BigInteger[]{new BigInteger("2"), new BigInteger("3"), new BigInteger("5"), new BigInteger("7"), new BigInteger("11"), new BigInteger("13")};
        } else if (number.compareTo(new BigInteger("341550071728321")) == -1) {
            smallPrimes = new BigInteger[]{new BigInteger("2"), new BigInteger("3"), new BigInteger("5"), new BigInteger("7"), new BigInteger("11"), new BigInteger("13"), new BigInteger("17")};
        } else {
            BigInteger sqrt = number.sqrt();

            for (BigInteger i = new BigInteger("3"); i.compareTo(sqrt) <= 0; i = i.add(BigInteger.TWO)) {
                if (number.mod(i).equals(BigInteger.ZERO)) {
                    return false;
                }
            }
            return true;
        }

        for (BigInteger a : smallPrimes) {
            if (!millerRabinTest(number, a)) {
                return false; // 소수가 아님
            }
        }

        return true;
    }

    // 밀러-라빈 테스트 수행
    // 밀러-라빈 테스트 수행
    public static boolean millerRabinTest(BigInteger number, BigInteger a) {
        BigInteger numberMinusOne = number.subtract(BigInteger.ONE);
        BigInteger d = numberMinusOne;
        while (d.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            d = d.divide(new BigInteger("2"));
        }

        BigInteger x = a.modPow(d, number);
        if (x.equals(BigInteger.ONE) || x.equals(numberMinusOne)) {
            return true; // 테스트 통과
        }

        while (!d.equals(numberMinusOne)) {
            x = x.modPow(new BigInteger("2"), number);
            d = d.multiply(new BigInteger("2"));

            if (x.equals(BigInteger.ONE)) {
                return false; // 소수가 아님
            }
            if (x.equals(numberMinusOne)) {
                return true; // 테스트 통과
            }
        }

        return false; // 소수가 아님
    }

}