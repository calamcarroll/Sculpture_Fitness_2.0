package ie.app.sculpturefitness20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Handler extends SQLiteOpenHelper {


    public static final String DatabaseName = "SculptureFitnessVersion2.db";

    public DB_Handler(Context context) {
        super(context, DatabaseName,null, 1);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Workout(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ExerciseName TEXT, " +
                "SetNumber INTEGER, " +
                "RepNumber INTEGER, " +
                "RestTime TEXT, " +
                "WorkoutName TEXT, " +
                "MuscleChoice TEXT" +
                ")"
        );
    }


    public boolean insertData(String ExerciseName, int SetNumber, int RepNumber, String restTime, String workoutName, String Choice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues myContent = new ContentValues();
        myContent.put("ExerciseName", ExerciseName);
        myContent.put("SetNumber", SetNumber);
        myContent.put("RepNumber", RepNumber);
        myContent.put("restTime", restTime);
        myContent.put("workoutName", workoutName);
        myContent.put("MuscleChoice", Choice);
        long result = db.insert("Workout", null, myContent);
        if (result == -1){
            return false;

        }else
            return true;
    }
    public Cursor getFirstListContents(){
      SQLiteDatabase db = this.getWritableDatabase();
        Cursor myData = db.rawQuery("SELECT DISTINCT workoutName, MuscleChoice FROM Workout ORDER BY workoutName ASC", null);
        return myData;
    }
    public Cursor getWorkoutData(String WorkoutName){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor Data = db.rawQuery("SELECT ExerciseName, MuscleChoice, SetNumber, RepNumber,restTime FROM Workout WHERE workoutName = '"+WorkoutName+"'", null);
        return Data;
    }

    public Cursor getExerciseData(String ExerciseName){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor Data = db.rawQuery("SELECT ExerciseName, restTime FROM Workout WHERE ExerciseName = '"+ExerciseName+"'", null);
        return Data;
    }
    public void updateProgram(String WorkoutName, int Sets, int Reps, String restTime,String name){
        this.getWritableDatabase().execSQL("UPDATE Workout SET ExerciseName ='" +WorkoutName+"'," +
                "SetNumber ='" + Sets +"'," +
                "RepNumber ='" + Reps +"'," +
                "restTime ='" + restTime + "' " +
                "WHERE ExerciseName ='"+ name +"';");
    }

    public Integer DeleteData(String ProgramName){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Workout","workoutName = ?",new String[]{ProgramName});
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS WORKOUT");
       }
        }
