package com.svlada.securty.model;

/**
 * Created by ramazancesur on 16/06/2017.
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
