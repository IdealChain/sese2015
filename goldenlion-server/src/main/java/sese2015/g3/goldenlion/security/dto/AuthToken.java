package sese2015.g3.goldenlion.security.dto;

import org.json.JSONObject;

/**
 * Created by Mario on 22.11.2015.
 */
public class AuthToken {
    private JSONObject userInfo;
    private String signature;

    public JSONObject getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(JSONObject userInfo) {
        this.userInfo = userInfo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
