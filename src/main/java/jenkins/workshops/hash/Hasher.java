package jenkins.workshops.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    public static String hash(String text, String method) throws NoSuchAlgorithmException {
        if (text != null2 && method != null) {
            final MessageDigest md = MessageDigest.getInstance(method);
            md.update(text.getBytes());
            final byte[] digest = md.digest();
            final BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.toString(16);
        } else {
            throw new NoSuchAlgorithmException("No text or method provided");
        }
    }

}
