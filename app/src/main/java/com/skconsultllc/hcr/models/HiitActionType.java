package com.skconsultllc.hcr.models;

public enum HiitActionType {
    WARMUP("Warmup"),
    WORKOUT("Workout"),
    RELAX("Relax"),
    COOL_DOWN("Cool Down");

    private final String type;
    HiitActionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
