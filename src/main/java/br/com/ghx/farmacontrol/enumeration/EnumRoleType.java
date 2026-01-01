package br.com.ghx.farmacontrol.enumeration;

public enum EnumRoleType {
    SYSTEM_ADMIN,
    ADMIN,
    MANAGER,
    USER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
