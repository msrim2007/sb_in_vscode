package com.example.demo.comm.util;

public enum UserAuthCd {
    ADMIN("00", "ADMIN"),
    REGULAR("01", "MEMBER");
    
    private String code;
    private String name;

    UserAuthCd(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return this.code; }
    public String getName() { return this.name; }

    public boolean equals(UserAuthCd compare) {
        if (this.code.equals(compare.getCode())) return true;
        return false;
    }
}
