package sese2015.g3.goldenlion.security.utils;

import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


public class TokenUtils {
    private static final String MAGIC_KEY = "Obfuscate";
    private static final int SECONDS_PER_MINUTE = 60;

    public static String createToken(UserDetails userDetails, Map<String, Boolean> roles) {
        long expires = System.currentTimeMillis() + 1000L * SECONDS_PER_MINUTE * SECONDS_PER_MINUTE;

        JSONObject token = new JSONObject();
        token.put("username", userDetails.getUsername());
        token.put("roles", roles);
        token.put("expires", expires);
        String encodedToken = new String(Base64.encode(token.toString().getBytes()));
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(encodedToken);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));
        return tokenBuilder.toString();
    }


    public static String computeSignature(UserDetails userDetails, long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(userDetails.getPassword());
        signatureBuilder.append(":");
        signatureBuilder.append(TokenUtils.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!", e);
        }
        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes(Charset.forName("UTF-8")))));
    }


    public static String getUserNameFromToken(String authToken) {
        if (authToken == null) {
            return null;
        }
        String[] parts = authToken.split(":");
        JSONObject userInfos = new JSONObject(parts[0]);
        return userInfos.getString("username");
    }

    public static boolean validateToken(String authToken, UserDetails userDetails) {
        String[] parts = authToken.split(":");
        JSONObject userInfos = new JSONObject(parts[0]);
        long expires = userInfos.getLong("expires");
        String signature = parts[1];

        if (expires < System.currentTimeMillis()) {
            return false;
        }
        return signature.equals(TokenUtils.computeSignature(userDetails, expires));
    }
}
