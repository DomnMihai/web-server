package jenkins.workshops.hash;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.NoSuchAlgorithmException;

public class HasherTest {

    @Test
    public void testHashMd5() {
        final String expectedHash = "a2fb2699f06d44384ab59160e170f3e7";
        final String hash;
        try {
            hash = Hasher.hash("moja kura", "MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expectedHash, hash, "Wrong MD5 checksum");
    }

    @Test
    public void testHashSha256() {
        final String expectedHash = "7e7aee8cdf4f16f611f49f5d303ec892f228cb55f5fd2d2eb068cb9704ca77b8";
        final String hash;
        try {
            hash = Hasher.hash("moja kura", "SHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expectedHash, hash, "Wrong SHA256 checksum");
    }

    @Test
    public void testHashNullText() {
        assertThrows(NoSuchAlgorithmException.class, () -> Hasher.hash(null, "SHA256"));
    }

    @Test
    public void testHashNullMethod() {
        assertThrows(NoSuchAlgorithmException.class, () -> Hasher.hash("uh ty", null));
    }

    @Test
    public void testHashWrongMethod() {
        assertThrows(NoSuchAlgorithmException.class, () -> Hasher.hash("uh ty", "not"));
    }
}
