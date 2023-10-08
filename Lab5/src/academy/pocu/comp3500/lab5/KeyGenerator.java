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

        // 작은 수에 대한 판별(링크 : https://ko.wikipedia.org/wiki/%EB%B0%80%EB%9F%AC-%EB%9D%BC%EB%B9%88_%EC%86%8C%EC%88%98%ED%8C%90%EB%B3%84%EB%B2%95)
        if (number.compareTo(new BigInteger("1373653")) == -1) {
            if (number.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("3")).equals(BigInteger.ZERO)) {
                return false;
            }
            return true;
        }

        if (number.compareTo(new BigInteger("9080191")) == -1) {
            if (number.mod(new BigInteger("31")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("73")).equals(BigInteger.ZERO)) {
                return false;
            }
            return true;
        }

        if (number.compareTo(new BigInteger("4759123141")) == -1) {
            if (number.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("7")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("61")).equals(BigInteger.ZERO)) {
                return false;
            }
            return true;
        }

        if (number.compareTo(new BigInteger("2152302898747")) == -1) {
            if (number.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("3")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("5")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("7")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("11")).equals(BigInteger.ZERO)) {
                return false;
            }
            return true;
        }

        if (number.compareTo(new BigInteger("3474749660383")) == -1) {
            if (number.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("3")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("5")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("7")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("11")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("13")).equals(BigInteger.ZERO)) {
                return false;
            }

            return true;
        }

        // 341,550,071,728,321

        if (number.compareTo(new BigInteger("341550071728321")) == -1) {
            if (number.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("3")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("5")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("7")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("11")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("13")).equals(BigInteger.ZERO)) {
                return false;
            }

            if (number.mod(new BigInteger("17")).equals(BigInteger.ZERO)) {
                return false;
            }

            return true;
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