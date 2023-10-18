package ru.kata.spring.bootstrap.demo.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {


    public Role(String roleName){
        this.roleName = roleName;
    }

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString(){
        return roleName;
    }
}
