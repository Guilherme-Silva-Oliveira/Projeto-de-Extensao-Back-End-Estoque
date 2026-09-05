package school.sptech.sistema_estoque.enums;

public enum Role {
    ADMIN,
    ALMOXARIFE;
    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}