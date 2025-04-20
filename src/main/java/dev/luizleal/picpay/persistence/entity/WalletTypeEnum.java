package dev.luizleal.picpay.persistence.entity;

public enum WalletTypeEnum {

    USER(1L, "user"),
    MERCHANT(2L, "merc");

    private Long id;
    private String description;

    WalletTypeEnum(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public WalletType get() {
        return new WalletType(id, description);
    }
}
