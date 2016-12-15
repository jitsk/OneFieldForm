package com.android.onefieldform.Events;

/**
 * Created by jithin on 15/12/16.
 */

public class EmailReceived {
    private String email;

    public EmailReceived(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
