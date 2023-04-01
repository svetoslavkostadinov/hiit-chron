package com.skconsultllc.hcr.models;

import java.util.Calendar;

public class HiitAction {
    private HiitActionType hiitActionType;
    private int duration;

    public HiitAction(HiitActionType hiitActionType, int duration) {
        this.hiitActionType = hiitActionType;
        this.duration = duration;
    }

    public HiitActionType getHiitActionType() {
        return hiitActionType;
    }

    public void setHiitActionType(HiitActionType hiitActionType) {
        this.hiitActionType = hiitActionType;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationStringValue() {
        return String.valueOf(duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
