package com.shangzf.oauth.multi;

import java.util.Map;

public class MultiAuthentication {

    private String authType;
    private String username;
    private Map<String, String[]> authParameters;

    public String getAuthParameter(String parameter) {
        String[] values = this.authParameters.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return "";
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, String[]> getAuthParameters() {
        return authParameters;
    }

    public void setAuthParameters(Map<String, String[]> authParameters) {
        this.authParameters = authParameters;
    }
}
