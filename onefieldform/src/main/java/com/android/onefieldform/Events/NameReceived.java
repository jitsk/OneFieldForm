package com.android.onefieldform.Events;

/**
 * Created by jithin on 15/12/16.
 */

public class NameReceived {
    private String name;

    public NameReceived(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
