package com.example.projectstatistic.model;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private String userID, userName;
    private int median ,modus,  mean;
    private int year;
    private String resultID;
    private int firstScr, secondScr, thirdScr, frthScr, fifthScr;

    public Result() {

    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userID", userID);
        result.put("userName", userName);
        result.put("mean", mean);
        result.put("median", median);
        result.put("modus", modus);
        result.put("year", year);
        result.put("firstScr", firstScr);
        result.put("secondScr",  secondScr);
        result.put("thirdScr", thirdScr);
        result.put("frthScr", frthScr);
        result.put("fifthScr", fifthScr);

        return result;
    }

    public Result(String userID,String userName, int mean, int median, int modus, int year, int firstScr, int secondScr, int thirdScr, int frthScr, int fifthScr) {
        this.userID = userID;
        this.userName = userName;
        this.median = median;
        this.modus = modus;
        this.mean = mean;
        this.year = year;
        this.firstScr = firstScr;
        this.secondScr = secondScr;
        this.thirdScr = thirdScr;
        this.frthScr = frthScr;
        this.fifthScr = fifthScr;
    }

    public int getFirstScr() {
        return firstScr;
    }

    public void setFirstScr(int firstScr) {
        this.firstScr = firstScr;
    }

    public int getSecondScr() {
        return secondScr;
    }

    public void setSecondScr(int secondScr) {
        this.secondScr = secondScr;
    }

    public int getThirdScr() {
        return thirdScr;
    }

    public void setThirdScr(int thirdScr) {
        this.thirdScr = thirdScr;
    }

    public int getFrthScr() {
        return frthScr;
    }

    public void setFrthScr(int frthScr) {
        this.frthScr = frthScr;
    }

    public int getFifthScr() {
        return fifthScr;
    }

    public void setFifthScr(int fifthScr) {
        this.fifthScr = fifthScr;
    }

    public String getResultID() {
        return resultID;
    }

    public void setResultID(String resultID) {
        this.resultID = resultID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getMedian() {
        return median;
    }

    public void setMedian(int median) {
        this.median = median;
    }

    public int getModus() {
        return modus;
    }

    public void setModus(int modus) {
        this.modus = modus;
    }

    public int getMean() {
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }
}
