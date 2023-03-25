package group.g22.demostore.enums;

public enum TypeProductStatusOption {
    IN_USE(1, "Đang sử dụng"),
    CLOSE(0, "Không sử dụng");

    private final int value;

    private final String name;

    TypeProductStatusOption(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static TypeProductStatusOption valueOf(int value) {
        TypeProductStatusOption[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TypeProductStatusOption responseCode = var1[var3];
            if (responseCode.getValue() == value) {
                return responseCode; // code int
            }
        }
        return null;
    }
}
