package com.android.onefieldform.Events;

/**
 * Used to propagate name received
 * @author jithin
 * @version 1.0
 */

public class NameReceived {
    private final String name;

    public NameReceived(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
