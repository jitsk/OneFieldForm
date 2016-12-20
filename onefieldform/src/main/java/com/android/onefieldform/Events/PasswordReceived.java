package com.android.onefieldform.Events;

/**
 * Used to propagate password received event
 * @author jithin
 * @version 1.0
 */

public class PasswordReceived {
    private final String password;

    public PasswordReceived(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
