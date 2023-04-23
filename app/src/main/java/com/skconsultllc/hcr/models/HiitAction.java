package com.skconsultllc.hcr.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class HiitAction implements Parcelable {
    private HiitActionType hiitActionType;
    private int duration;

    public HiitAction(HiitActionType hiitActionType, int duration) {
        this.hiitActionType = hiitActionType;
        this.duration = duration;
    }

    protected HiitAction(Parcel in) {
        this.duration = in.readInt();
        this.hiitActionType = HiitActionType.valueOf(in.readString());
    }

    public static final Creator<HiitAction> CREATOR = new Creator<HiitAction>() {
        @Override
        public HiitAction createFromParcel(Parcel in) {
            return new HiitAction(in);
        }

        @Override
        public HiitAction[] newArray(int size) {
            return new HiitAction[size];
        }
    };

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
        return String.valueOf(duration) + " secs";
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.getDuration());
        dest.writeString(this.getHiitActionType().name());
    }
}
