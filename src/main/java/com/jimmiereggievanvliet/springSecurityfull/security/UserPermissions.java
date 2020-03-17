package com.jimmiereggievanvliet.springSecurityfull.security;

public enum UserPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    MAIN_READ("main:read"),
    MAIN_WRITE("main:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
