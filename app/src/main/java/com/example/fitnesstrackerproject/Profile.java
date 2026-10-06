package com.example.fitnesstrackerproject;

import android.widget.AdapterView;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    public double height;
    public double weight;
    public int age;
    public String gender;
    public String activity_level;
    public double daily_calorie_intake;
    public Map<String, Boolean> updateProfile = new HashMap<>();


    public Profile(double height, double weight, int age, String gender, String activity_level, double daily_calorie_intake) {


        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
        this.activity_level = activity_level;
        this.daily_calorie_intake = daily_calorie_intake;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("height", height);
        result.put("weight", weight);
        result.put("age", age);
        result.put("gender", gender);
        result.put("activity_level", activity_level);
        result.put("daily_calorie_intake", daily_calorie_intake);
        return result;
    }


    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }

    public void setDaily_calorie_intake(double daily_calorie_intake) {
        this.daily_calorie_intake = daily_calorie_intake;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getActivity_level() {
        return activity_level;
    }

    public double getDaily_calorie_intake() {
        return daily_calorie_intake;
    }


}
