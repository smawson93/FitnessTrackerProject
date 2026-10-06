package com.example.fitnesstrackerproject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void BMR_test1() {

        double height = 180;
        double weight = 70;
        int age = 25;
        String gender = "Male";
        double result = 1748.047;

        double BMR_result = update_profile_page.getBMR(height, weight, age, gender);

        assertEquals(BMR_result, result, 1);
    }
    @Test
    public void BMR_test2() {

        double height = 155;
        double weight = 54;
        int age = 21;
        String gender = "Female";
        double result = 1336.191;

        double BMR_result = update_profile_page.getBMR(height, weight, age, gender);

        assertEquals(BMR_result, result, 1);
    }
    @Test
    public void BMR_test3() {

        double height = 175;
        double weight = 85;
        int age = 34;
        String gender = "Female";
        double result = 1628.52;

        double BMR_result = update_profile_page.getBMR(height, weight, age, gender);

        assertEquals(BMR_result, result, 1);
    }

    @Test
    public void calorie_count_test1() {

        double BMR = 1748.047;
        String activity_level = "Sedentary";
        double result = 2097.66;

        double calorie_result = update_profile_page.getDailyCalorieIntake(BMR, activity_level);

        assertEquals(calorie_result, result, 1);
    }

    @Test
    public void calorie_count_test2() {

        double BMR = 1748.047;
        String activity_level = "Lightly active";
        double result = 2403.564625;

        double calorie_result = update_profile_page.getDailyCalorieIntake(BMR, activity_level);

        assertEquals(calorie_result, result, 1);
    }

    @Test
    public void calorie_count_test3() {

        double BMR = 1748.047;
        String activity_level = "Moderately active";
        double result = 2709.47285;

        double calorie_result = update_profile_page.getDailyCalorieIntake(BMR, activity_level);

        assertEquals(calorie_result, result, 1);
    }

    @Test
    public void calorie_count_test4() {

        double BMR = 1748.047;
        String activity_level = "Very active";
        double result = 3015.381075;

        double calorie_result = update_profile_page.getDailyCalorieIntake(BMR, activity_level);

        assertEquals(calorie_result, result, 1);
    }

    @Test
    public void calorie_count_test5() {

        double BMR = 1748.047;
        String activity_level = "Extra Active";
        double result = 3321.2893;

        double calorie_result = update_profile_page.getDailyCalorieIntake(BMR, activity_level);

        assertEquals(calorie_result, result, 1);
    }

    @Test
    public void weightLossTest1() {

        double preDAC = 2097.66;
        String weightLoss = "Maintain Weight";
        double result = 2097.66;

        double DAC = update_profile_page.setWeightLostDAC(preDAC, weightLoss);

        assertEquals(DAC, result, 1);
    }

    @Test
    public void weightLossTest2() {

        double preDAC = 2097.66;
            String weightLoss = "Lose half a kg per week";
        double result = 1597.66;

        double DAC = update_profile_page.setWeightLostDAC(preDAC, weightLoss);

        assertEquals(DAC, result, 1);
    }

    @Test
    public void weightLossTest3() {

        double preDAC = 2097.66;
        String weightLoss = "Lose one kg per week";
        double result = 1097.66;

        double DAC = update_profile_page.setWeightLostDAC(preDAC, weightLoss);

        assertEquals(DAC, result, 1);
    }

    @Test
    public void weightLossTest4() {

        double preDAC = 2097.66;
        String weightLoss = "Gain half a kg per week";
        double result = 2597.66;

        double DAC = update_profile_page.setWeightLostDAC(preDAC, weightLoss);

        assertEquals(DAC, result, 1);
    }

    @Test
    public void weightLossTest5() {

        double preDAC = 2097.66;
        String weightLoss = "Gain one kg per week";
        double result = 3097.66;

        double DAC = update_profile_page.setWeightLostDAC(preDAC, weightLoss);

        assertEquals(DAC, result, 1);
    }

    @Test
    public void setProteins(){

        double DAC = 2097;
        double result = 209.7;

        double proteins = (DAC * 0.4) / 4;

        assertEquals(proteins, result, .1);
    }

    @Test
    public void setCarbs(){

        double DAC = 2097;
        double result = 209.7;

        double carbs = (DAC * 0.4) / 4;

        assertEquals(carbs, result, 0.1);
    }

    @Test
    public void setFats(){

        double DAC = 2097;
        double result = 46.6;

        double fats = (DAC * 0.2) / 9;

        assertEquals(fats, result, .1);
    }

}