package com.jimmiereggievanvliet.springSecurityfull.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.jimmiereggievanvliet.springSecurityfull.security.UserPermissions.*;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN( Sets.newHashSet(COURSE_READ, COURSE_WRITE, USER_READ, USER_WRITE)),
    ADMINTRAINEE( Sets.newHashSet(COURSE_READ, USER_READ));

    private final Set<UserPermissions> permissions;

    UserRole(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
       Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
               .collect(Collectors.toSet());
       permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
       return permissions;
    }
}
