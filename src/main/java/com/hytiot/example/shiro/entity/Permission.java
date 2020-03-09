package com.hytiot.example.shiro.entity;

import java.io.Serializable;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-03-09 13:37
 **/
public class Permission implements Serializable {

    private String permission;

    private String permissionName;

    public Permission() {
    }

    public Permission(String permission, String permissionName) {
        this.permission = permission;
        this.permissionName = permissionName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
