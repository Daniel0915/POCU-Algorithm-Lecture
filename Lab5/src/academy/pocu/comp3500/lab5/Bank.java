package academy.pocu.comp3500.lab5;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import javax.crypto.Cipher;

public class Bank {
    private byte[][] pubKeys;
    private final long[] amounts;

    public Bank(byte[][] pubKeys, final long[] amounts) {
        this.pubKeys = pubKeys;
        this.amounts = amounts;
    }

    public long getBalance(final byte[] pubKey) {
        // 1. pubKey 를 가진 대상을 배열에서 찾고
        for (int i = 0; i < pubKeys.length; i++) {
            // 2. 만약 있다면, 잔액을 반환
            if (pubKeys[i] == pubKey) {
                return amounts[i];
            }
        }
        // 3. 아니면, 0 으로 잔액 반환
        return 0;
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        PublicKey publicKey = getPublicKey(from);

        byte[] sha256Hash = getSha256Hash(from, to, amount);

        byte[] decryptPlainByte = decrypt(signature, publicKey);

        if (decryptPlainByte != null) {
            if(Objects.equals(encodeToHexString(sha256Hash), encodeToHexString(decryptPlainByte))) {
                for (int i = 0; i < pubKeys.length; i++) {
                    if (pubKeys[i] == from) {
                        amounts[i] -= amount;
                    }

                    if (pubKeys[i] == to) {
                        amounts[i] += amount;
                    }
                }
                return true;
            }
        }

        return false;
    }

    private static byte[] getSha256Hash(final byte[] from, byte[] to, final long amount) {
        ByteBuffer buffer = ByteBuffer.allocate(from.length + to.length + 8);
        buffer.put(from);
        buffer.put(to);
        buffer.putLong(amount);
        byte[] combinedParams = buffer.array();

        byte[] digest = null;
        try {
            // 데이터를 합치기 위한 바이트 배열 생성

            // SHA-256 해시 알고리즘을 사용하는 MessageDigest 객체 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(combinedParams);
            // 데이터를 해시
            digest = md.digest();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digest;
    }

    private static byte[] encrypt(final byte[] sha256Hash, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            byte[] ciphertext = cipher.doFinal(sha256Hash);

            return ciphertext;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static byte[] decrypt(byte[] cipherByte, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            byte[] plainByte = cipher.doFinal(cipherByte);
            return plainByte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PublicKey getPublicKey(byte[] publicKeyByte) {
        PublicKey publicKey = null;
        try {
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(publicKeySpec);

            return keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    private static PrivateKey getPrivateKey(byte[] privateKeyByte) {
        PrivateKey privateKey = null;
        try {
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(privateKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return privateKey;
    }


    private String generateRSASignature(final byte[] from, byte[] to, final long amount, PrivateKey privateKey) {
        try {
            byte[] sha256Hash = getSha256Hash(from, to, amount);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(sha256Hash);
            return encodeToHexString(signature.sign());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    private static String encodeToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte oneByte : bytes) {
            result.append(String.format("%02x", oneByte));
        }
        return result.toString();
    }
}