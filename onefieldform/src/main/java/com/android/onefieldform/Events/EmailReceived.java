package com.android.onefieldform.Events;

/**
 * Used to propagate email received event
 * @author jithin
 * @version 1.0
 */

public class EmailReceived {
    private final String email;

    public EmailReceived(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
