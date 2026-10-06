package com.example.fitnesstrackerproject;

import java.util.ArrayList;

public class Diaries {

    private String date;
    private int totalCalories;
    private ArrayList<String> breakfastFoodList;
    private ArrayList<String> lunchFoodList;
    private ArrayList<String> dinnerFoodList;
    private int water;

    public Diaries(String date, int totalCalories, ArrayList<String> breakfastFoodList, ArrayList<String> lunchFoodList, ArrayList<String> dinnerFoodList, int water) {
        this.date = date;
        this.totalCalories = totalCalories;
        this.breakfastFoodList = breakfastFoodList;
        this.lunchFoodList = lunchFoodList;
        this.dinnerFoodList = dinnerFoodList;
        this.water = water;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public ArrayList<String> getBreakfastFoodList() {
        return breakfastFoodList;
    }

    public void setBreakfastFoodList(ArrayList<String> breakfastFoodList) {
        this.breakfastFoodList = breakfastFoodList;
    }

    public ArrayList<String> getLunchFoodList() {
        return lunchFoodList;
    }

    public void setLunchFoodList(ArrayList<String> lunchFoodList) {
        this.lunchFoodList = lunchFoodList;
    }

    public ArrayList<String> getDinnerFoodList() {
        return dinnerFoodList;
    }

    public void setDinnerFoodList(ArrayList<String> dinnerFoodList) {
        this.dinnerFoodList = dinnerFoodList;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
