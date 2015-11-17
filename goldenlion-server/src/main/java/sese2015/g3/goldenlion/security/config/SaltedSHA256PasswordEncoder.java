package sese2015.g3.goldenlion.security.config;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaltedSHA256PasswordEncoder implements PasswordEncoder {
    private static final String DIGEST_INSTANCE = "SHA-256";

    private final String salt;
    private final MessageDigest digest;

    public SaltedSHA256PasswordEncoder(String salt) throws NoSuchAlgorithmException {
        this.salt = salt;
        this.digest = MessageDigest.getInstance(DIGEST_INSTANCE);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String saltedPassword = rawPassword + this.salt;
        return new String(Hex.encode(this.digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8))));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword).equals(encodedPassword.trim());
    }
}
