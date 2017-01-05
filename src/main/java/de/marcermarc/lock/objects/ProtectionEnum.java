package de.marcermarc.lock.objects;

public enum ProtectionEnum {
    ALLALOWED(0),
    ONLYLOOK(1),
    NOACCESS(2);

    private int value;

    ProtectionEnum(int value) {
        this.value = value;
    }


}
