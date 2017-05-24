package com.teamdating.datingapp.Models;

/**
 * Created by BT on 5/24/2017.
 */

public class User {

    public class Role {
        String name;
        String description;
        Role parentRole;
    }

    public class Permission {
        String name;
        String description;
    }

    public class RolePermission {
        Role role;
        Permission permission;
    }

    private Role[] roles;

    private Permission[] permissions;

    private RolePermission[] rolePermission;

    private Role activeRole;

    public Role[] getRoles ()
    {
        return roles;
    }

    public void setRoles (Role[] roles)
    {
        this.roles = roles;
    }

    public Permission[] getPermissions ()
    {
        return permissions;
    }

    public void setPermissions (Permission[] permissions)
    {
        this.permissions = permissions;
    }

    public RolePermission[] getRolePermission ()
    {
        return rolePermission;
    }

    public void setRolePermission (RolePermission[] rolePermission)
    {
        this.rolePermission = rolePermission;
    }

    public Role getActiveRole ()
    {
        return activeRole;
    }

    public void setActiveRole (Role activeRole)
    {
        this.activeRole = activeRole;
    }
}
