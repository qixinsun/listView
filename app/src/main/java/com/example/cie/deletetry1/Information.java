package com.example.cie.deletetry1;

public class Information {
    private String mGrade;
    private int mStudentId;
    private String mName;

    public Information(String grade, int studentId, String mName) {
        mGrade = grade;
        mStudentId = studentId;
        this.mName = mName;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public int getStudentId() {
        return mStudentId;
    }

    public void setStudentId(int studentId) {
        mStudentId = studentId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
