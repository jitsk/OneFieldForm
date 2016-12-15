package com.android.onefieldform.Events;

/**
 * Created by jithin on 15/12/16.
 */

public class PasswordReceived {
    private String password;

    public PasswordReceived(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
