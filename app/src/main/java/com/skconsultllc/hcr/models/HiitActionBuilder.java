package com.skconsultllc.hcr.models;

public class HiitActionBuilder {
    private HiitActionType hiitActionType;
    private int duration;

    public HiitActionBuilder setHiitActionType(HiitActionType hiitActionType) {
        this.hiitActionType = hiitActionType;
        return this;
    }

    public HiitActionBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public HiitAction build() {
        return new HiitAction(hiitActionType, duration);
    }
}