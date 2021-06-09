package by.kostopravov.usersapp.model;

public enum Permission {
    CAN_READ("canRead"),
    CAN_WRITE("canWrite");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
