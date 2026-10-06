package com.example.fitnesstrackerproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private Context context;

    private static final String TABLE_NAME_PROFILE = "profile_table";
    private static final String PROFILE_COL1 = "name";
    private static final String PROFILE_COL2 = "height";
    private static final String PROFILE_COL3 = "weight";
    private static final String PROFILE_COL4 = "gender";
    private static final String PROFILE_COL5 = "age";
    private static final String PROFILE_COL6 = "activity_level";
    private static final String PROFILE_COL7 = "BMR";
    private static final String PROFILE_COL8 = "DAC";
    private static final String PROFILE_COL9 = "weight_loss";

    private static final String TABLE_NAME_DIARY = "diary_table";
    private static final String DIARY_COL1 = "date";
    private static final String DIARY_COL2 = "totalCalories";
    private static final String DIARY_COL3 = "water";
    private static final String DIARY_COL4 = "syns";
    private static final String DIARY_COL5 = "weightWatcherPoints";
    private static final String DIARY_COL6 = "proteins";
    private static final String DIARY_COL7 = "fats";
    private static final String DIARY_COL8 = "carbs";

    private static final String TABLE_NAME_BREAKFAST_LIST = "breakfast_list_table";
    private static final String BREAKFAST_COL1 = "id";
    private static final String BREAKFAST_COL2 = "date";
    private static final String BREAKFAST_COL3 = "food";
    private static final String BREAKFAST_COL4 = "calories";

    private static final String TABLE_NAME_LUNCH_LIST = "lunch_list_table";
    private static final String LUNCH_COL1 = "id";
    private static final String LUNCH_COL2 = "date";
    private static final String LUNCH_COL3 = "food";
    private static final String LUNCH_COL4 = "calories";

    private static final String TABLE_NAME_DINNER_LIST = "dinner_list_table";
    private static final String DINNER_COL1 = "id";
    private static final String DINNER_COL2 = "date";
    private static final String DINNER_COL3 = "food";
    private static final String DINNER_COL4 = "calories";

    private static final String TABLE_NAME_FOOD_LIST = "food_list_table";
    private static final String FOOD_COL1 = "foodID";
    private static final String FOOD_COL2 = "name";
    private static final String FOOD_COL3 = "calories";

    private static final String TABLE_NAME_GOAL = "goal_list_table";
    private static final String GOAL_COL1 = "goalID";
    private static final String GOAL_COL2 = "goal_name";
    private static final String GOAL_COL3 = "goal_target";
    private static final String GOAL_COL4 = "goal_current";
    private static final String GOAL_COL5 = "goal_start_date";
    private static final String GOAL_COL6 = "goal_end_date";
    private static final String GOAL_COL7 = "goal_type";
    private static final String GOAL_COL8 = "goal_complete";

    private static final String TABLE_NAME_DIET = "diet_table";
    private static final String DIET_COL1 = "name";
    private static final String DIET_COL2 = "syns";
    private static final String DIET_COL3 = "weightWatcherPoints";
    private static final String DIET_COL4 = "proteins";
    private static final String DIET_COL5 = "fats";
    private static final String DIET_COL6 = "carbs";

    private static final String TABLE_NAME_EXERCISE = "exercise_table";
    private static final String EXERCISE_COL1 = "exerciseID";
    private static final String EXERCISE_COL2 = "name";
    private static final String EXERCISE_COL3 = "type";
    private static final String EXERCISE_COL4 = "date";
    private static final String EXERCISE_COL5 = "duration";
    private static final String EXERCISE_COL6 = "calories";
    private static final String EXERCISE_COL7 = "sets";
    private static final String EXERCISE_COL8 = "reps";
    private static final String EXERCISE_COL9 = "weight";

    public Database(@Nullable Context context, String tableName) {
        super(context, tableName, null, 1);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_Profile_Table = "CREATE TABLE " + TABLE_NAME_PROFILE + " (" + PROFILE_COL1 + " TEXT PRIMARY KEY, " + PROFILE_COL2 + " INTEGER, " + PROFILE_COL3 + " DOUBLE, " + PROFILE_COL4 + " TEXT, " + PROFILE_COL5 + " INTEGER, "
                + PROFILE_COL6 + " TEXT, " + PROFILE_COL7 + " DOUBLE, " + PROFILE_COL8 + " DOUBLE, " + PROFILE_COL9 + " TEXT)";
        String create_Diary_Table = "CREATE TABLE " + TABLE_NAME_DIARY + " (" + DIARY_COL1 + " TEXT PRIMARY KEY, " + DIARY_COL2 + " INTEGER, " + DIARY_COL3 + " INTEGER," + DIARY_COL4 + " INTEGER, " + DIARY_COL5 + " INTEGER, "
        + DIARY_COL6 + " DOUBLE, " + DIARY_COL7 + " DOUBLE, " + DIARY_COL8 + " DOUBLE)";
        String create_Breakfast_List_Table = "CREATE TABLE " + TABLE_NAME_BREAKFAST_LIST + " (" + BREAKFAST_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BREAKFAST_COL2 + " TEXT, " + BREAKFAST_COL3 + " TEXT, " + BREAKFAST_COL4 + " INTEGER)";
        String create_Lunch_List_Table = "CREATE TABLE " + TABLE_NAME_LUNCH_LIST + " (" + LUNCH_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LUNCH_COL2 + " TEXT, " + LUNCH_COL3 + " TEXT, " + LUNCH_COL4 + " INTEGER)";
        String create_Dinner_List_Table = "CREATE TABLE " + TABLE_NAME_DINNER_LIST + " (" + DINNER_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DINNER_COL2 + " TEXT, " + DINNER_COL3 + " TEXT, " + DINNER_COL4 + " INTEGER)";
        String create_Food_List_Table = "CREATE TABLE " + TABLE_NAME_FOOD_LIST + " (" + FOOD_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FOOD_COL2 + " TEXT, " + FOOD_COL3 + " INTEGER)";
        String create_weight_goal_Table = "CREATE TABLE " + TABLE_NAME_GOAL + " (" + GOAL_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GOAL_COL2 + " TEXT, " + GOAL_COL3 + " INTEGER, " + GOAL_COL4
                + " INTEGER, " + GOAL_COL5 + " TEXT, " + GOAL_COL6 + " TEXT, " + GOAL_COL7 + " TEXT, " + GOAL_COL8 + " TEXT)";
        String create_diet_table = "CREATE TABLE " + TABLE_NAME_DIET + " (" + DIET_COL1 + " TEXT, " + DIET_COL2 + " INTEGER, " + DIET_COL3 + " INTEGER, " + DIET_COL4 + " DOUBLE, " + DIET_COL5 + " DOUBLE, " + DIET_COL6 + " DOUBLE)";
        String create_exercise_table = "CREATE TABLE " + TABLE_NAME_EXERCISE + " (" + EXERCISE_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXERCISE_COL2 + " TEXT, " + EXERCISE_COL3 + " TEXT, " + EXERCISE_COL4 + " TEXT, "
        + EXERCISE_COL5 + " INTEGER, " + EXERCISE_COL6 + " INTEGER, " +  EXERCISE_COL7 + " INTEGER, " + EXERCISE_COL8 + " INTEGER, " + EXERCISE_COL9 + " DOUBLE)";
        db.execSQL(create_Profile_Table);
        db.execSQL(create_Diary_Table);
        db.execSQL(create_Breakfast_List_Table);
        db.execSQL(create_Lunch_List_Table);
        db.execSQL(create_Dinner_List_Table);
        db.execSQL(create_Food_List_Table);
        db.execSQL(create_weight_goal_Table);
        db.execSQL(create_diet_table);
        db.execSQL(create_exercise_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DIARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BREAKFAST_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LUNCH_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DINNER_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FOOD_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GOAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DIET);
        onCreate(db);
    }

    public boolean addProfile(String name, int height, double weight, String gender, int age, String activityLevel, double BMR, double DAC, String weightLoss) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COL1, name);
        contentValues.put(PROFILE_COL2, height);
        contentValues.put(PROFILE_COL3, weight);
        contentValues.put(PROFILE_COL4, gender);
        contentValues.put(PROFILE_COL5, age);
        contentValues.put(PROFILE_COL6, activityLevel);
        contentValues.put(PROFILE_COL7, BMR);
        contentValues.put(PROFILE_COL8, DAC);
        contentValues.put(PROFILE_COL9, weightLoss);

        long result = database.insert(TABLE_NAME_PROFILE, null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean updateProfile(String name, int height, double weight, String gender, int age, String activityLevel, double BMR, double DAC, String weightLoss) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COL2, height);
        contentValues.put(PROFILE_COL3, weight);
        contentValues.put(PROFILE_COL4, gender);
        contentValues.put(PROFILE_COL5, age);
        contentValues.put(PROFILE_COL6, activityLevel);
        contentValues.put(PROFILE_COL7, BMR);
        contentValues.put(PROFILE_COL8, DAC);
        contentValues.put(PROFILE_COL9, weightLoss);

        long result = database.update(TABLE_NAME_PROFILE, contentValues, PROFILE_COL1 + "= '" + name + "'", null);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean newDiaryDataInput(String date, int totalCalories, int water, int syns, int points, double proteins, double fat, double carbs) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DIARY_COL1, date);
        contentValues.put(DIARY_COL2, totalCalories);
        contentValues.put(DIARY_COL3, water);
        contentValues.put(DIARY_COL4, syns);
        contentValues.put(DIARY_COL5, points);
        contentValues.put(DIARY_COL6, proteins);
        contentValues.put(DIARY_COL7, fat);
        contentValues.put(DIARY_COL8, carbs);

        long result = database.insert(TABLE_NAME_DIARY, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean updateWater(int water, String date) {
        String query = "UPDATE " + TABLE_NAME_DIARY + " SET " + DIARY_COL3 + " = " + DIARY_COL3 + " + " + water + " WHERE date = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean newGoalInput(String name, int target, int current, String startDate, String goalType, String isComplete) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GOAL_COL2, name);
        contentValues.put(GOAL_COL3, target);
        contentValues.put(GOAL_COL4, current);
        contentValues.put(GOAL_COL5, startDate);
        contentValues.put(GOAL_COL7, goalType);
        contentValues.put(GOAL_COL8, isComplete);

        long result = database.insert(TABLE_NAME_GOAL, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean updateDiaryData(String date, int totalCalories) {

        String query = "UPDATE " + TABLE_NAME_DIARY + " SET " + DIARY_COL2 + " = " + DIARY_COL2 + " + " + totalCalories + " WHERE date = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean updateDiaryDataSyns(String date, int totalCalories, int syns) {

        String query = "UPDATE " + TABLE_NAME_DIARY + " SET " + DIARY_COL2 + " = " + DIARY_COL2 + " + " + totalCalories + ", " + DIARY_COL4 + " = " + DIARY_COL4 + " + " + syns + " WHERE date = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean updateDiaryDataPoints(String date, int totalCalories, int points) {

        String query = "UPDATE " + TABLE_NAME_DIARY + " SET " + DIARY_COL2 + " = " + DIARY_COL2 + " + " + totalCalories + " , " + DIARY_COL5 + " = " + DIARY_COL5 + " + " + points + " WHERE date = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean updateDiaryDataMacros(String date, int totalCalories, double proteins, double fats, double carbs) {

        String query = "UPDATE " + TABLE_NAME_DIARY + " SET " + DIARY_COL2 + " = " + DIARY_COL2 + " + " + totalCalories + " , " + DIARY_COL6 + " = "+ DIARY_COL6 + " + " + proteins + " , " + DIARY_COL7 + " = " + DIARY_COL7 + " + " + fats + " , " + DIARY_COL8 + " = " + DIARY_COL8 + " + " + carbs + " WHERE date = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean updateGoalData(String name, int current, String endDate, String isComplete, int goalID) {
        String query = "UPDATE " + TABLE_NAME_GOAL + " SET " + GOAL_COL2 + " = '" + name + "', " + GOAL_COL4 + " = " + current + ", " + GOAL_COL6 + " = " + endDate + ", " + GOAL_COL8 + " = '" + isComplete + "' WHERE " + GOAL_COL1 + " = " + goalID;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean deleteGoalData(int goalID){
        String query = "DELETE FROM " + TABLE_NAME_GOAL + " WHERE " + GOAL_COL1 + " = " + goalID;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean newBreakfastDataInput(String date, String name, int calories) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BREAKFAST_COL2, date);
        contentValues.put(BREAKFAST_COL3, name);
        contentValues.put(BREAKFAST_COL4, calories);

        long result = database.insert(TABLE_NAME_BREAKFAST_LIST, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean newLunchDataInput(String date, String food, int calories) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LUNCH_COL2, date);
        contentValues.put(LUNCH_COL3, food);
        contentValues.put(LUNCH_COL4, calories);

        long result = database.insert(TABLE_NAME_LUNCH_LIST, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean newDinnerDataInput(String date, String food, int calories) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DINNER_COL2, date);
        contentValues.put(DINNER_COL3, food);
        contentValues.put(DINNER_COL4, calories);

        long result = database.insert(TABLE_NAME_DINNER_LIST, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean newFoodDataInput(String name, int calories) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_COL2, name);
        contentValues.put(FOOD_COL3, calories);

        long result = database.insert(TABLE_NAME_FOOD_LIST, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteDiaryEntry(String date){
        String query = "DELETE FROM " + TABLE_NAME_DIARY + " WHERE " + DIARY_COL1 + " = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean deleteBreakfastEntries(String date){
        String query = "DELETE FROM " + TABLE_NAME_BREAKFAST_LIST + " WHERE " + BREAKFAST_COL2 + " = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean deleteLunchEntries(String date){
        String query = "DELETE FROM " + TABLE_NAME_LUNCH_LIST + " WHERE " + LUNCH_COL2 + " = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean deleteDinnerEntries(String date){
        String query = "DELETE FROM " + TABLE_NAME_DINNER_LIST + " WHERE " + DINNER_COL2 + " = '" + date + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean deleteProfile(String name){
        String query = "DELETE FROM " + TABLE_NAME_PROFILE + " WHERE " + PROFILE_COL1 + " = '" + name + "'";
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean addDietSlimmingWorld(String name, int syns) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DIET_COL1, name);
        contentValues.put(DIET_COL2, syns);

        long result = database.insert(TABLE_NAME_DIET, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean addDietWeightWatchers(String name, int points){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DIET_COL1, name);
        contentValues.put(DIET_COL3, points);

        long result = database.insert(TABLE_NAME_DIET, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean addDietMacro(String name, double proteins, double fats, double carbs){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DIET_COL1, name);
        contentValues.put(DIET_COL4, proteins);
        contentValues.put(DIET_COL5, fats);
        contentValues.put(DIET_COL6, carbs);

        long result = database.insert(TABLE_NAME_DIET, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean addStrengthTrainingExercise(String name, String type, String date, int sets, int reps, double weight) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COL2, name);
        contentValues.put(EXERCISE_COL3, type);
        contentValues.put(EXERCISE_COL4, date);
        contentValues.put(EXERCISE_COL7, sets);
        contentValues.put(EXERCISE_COL8, reps);
        contentValues.put(EXERCISE_COL9, weight);

        long result = database.insert(TABLE_NAME_EXERCISE, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean addCardioExercise(String name, String type, String date, int duration, int calories) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCISE_COL2, name);
        contentValues.put(EXERCISE_COL3, type);
        contentValues.put(EXERCISE_COL4, date);
        contentValues.put(EXERCISE_COL5, duration);
        contentValues.put(EXERCISE_COL6, calories);

        long result = database.insert(TABLE_NAME_EXERCISE, null, contentValues);

        if (result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean updateStrengthExercise(int id, String name, int sets, int reps, double weight) {
        String query = "UPDATE " + TABLE_NAME_EXERCISE + " SET " + EXERCISE_COL2 + " = '" + name + "', " + EXERCISE_COL7 + " = " + sets + ", " + EXERCISE_COL8 + " = " + reps + ", " + EXERCISE_COL9 + " = " + weight + " WHERE " + EXERCISE_COL1 + " = " + id;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    public boolean updateCardioExercise(int id, String name,  int duration, int calories) {
        String query = "UPDATE " + TABLE_NAME_EXERCISE + " SET " + EXERCISE_COL2 + " = '" + name + "', " + EXERCISE_COL5 + " = " + duration + ", " + EXERCISE_COL6 + " = " + calories + " WHERE " + EXERCISE_COL1 + " = " + id;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }


    public void deleteDiet(){
        String query = "DELETE FROM " + TABLE_NAME_DIET;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
    }

    public boolean deleteExercise(int id){
        String query = "DELETE FROM " + TABLE_NAME_EXERCISE + " WHERE " + EXERCISE_COL1 + " = " + id;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(query);
        return true;
    }

    Cursor listProfile() {
        String query = "SELECT * FROM " + TABLE_NAME_PROFILE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor listAllDiaryData() {
        String query = "SELECT * FROM " + TABLE_NAME_DIARY;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
   }

    Cursor listGoalData() {
        String query = "SELECT * FROM " + TABLE_NAME_GOAL;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor singleDiaryDataEntry(String date) {

        String query = "SELECT * FROM " + TABLE_NAME_DIARY + " WHERE " + DIARY_COL1 + " = '" + date + "'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
   }

    Cursor singleGoalData(int goalID){
       String query = "SELECT * FROM " + TABLE_NAME_GOAL + " WHERE " + GOAL_COL1 + " = " + goalID;
       SQLiteDatabase database = this.getReadableDatabase();

       Cursor cursor = null;
       if (database != null) {
           cursor = database.rawQuery(query, null);
       }
       return cursor;
   }

    Cursor listBreakfastFoods(String date) {

        String query = "SELECT * FROM " + TABLE_NAME_BREAKFAST_LIST + " WHERE " + BREAKFAST_COL2 + " = '" + date + "'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor listLunchFoods(String date) {

        String query = "SELECT * FROM " + TABLE_NAME_LUNCH_LIST + " WHERE " + LUNCH_COL2 + " = '" + date + "'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor listDinnerFoods(String date) {

        String query = "SELECT * FROM " + TABLE_NAME_DINNER_LIST + " WHERE " + DINNER_COL2 + " = '" + date + "'";
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor listAllFoods() {

        String query = "SELECT * FROM " + TABLE_NAME_FOOD_LIST;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getDietChoice() {
        String query = "SELECT * FROM " + TABLE_NAME_DIET;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor listAllExercise() {
        String query = "SELECT * FROM " + TABLE_NAME_EXERCISE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getSingleExerciseData(int id) {

        String query = "SELECT * FROM " + TABLE_NAME_EXERCISE + " WHERE " + EXERCISE_COL1 + " = " + id;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
}
