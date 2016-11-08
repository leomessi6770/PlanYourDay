package com.example.puyed.planyourday;

/**
 * Created by puyed on 04-Nov-16.
 */

public class CustomDate {
    private int mDayOfMonth; // min=1, max=31
    private int mMonth;      // min=0, max=11
    private int mYear;       // min=Integer.MIN_VALUE, max=Integer.MAX_VALUE

    public int getDayOfMonth() {
        return mDayOfMonth;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public void setDayOfMonth(int mDayOfMonth) {
        this.mDayOfMonth = mDayOfMonth;
    }

    public void setMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public void setYear(int mYear) {
        this.mYear = mYear;
    }

}
