package ie.app.sculpturefitness20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Create_Workout extends AppCompatActivity {

    Spinner MuscleChoice;
    String Choice;
    DB_Handler myDB;
    int num = 0;
    String Name = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__workout);
        myDB = new DB_Handler(this);

//First dialog box creation on create workout activity
        android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(Create_Workout.this);
        alertDialog.setMessage("What muscle are you training today? \n");
        String[] MuscleCategories = {"Chest", "Triceps", "Back", "Biceps", "Shoulders", "Legs"};
        ArrayAdapter<String> muscleGroup = new ArrayAdapter<String>
                (Create_Workout.this, android.R.layout.simple_spinner_item,MuscleCategories);
        MuscleChoice = new Spinner(Create_Workout.this);
        MuscleChoice.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        MuscleChoice.setAdapter(muscleGroup);

        alertDialog.setPositiveButton("Next Step", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Choice = MuscleChoice.getSelectedItem().toString();
            }
        });
        alertDialog.setView(MuscleChoice);
        alertDialog.show();


        // All binding for widgets in create workout activity
        final EditText ExerciseName =            (EditText) findViewById(R.id.WorkoutNameTextBox);
        final NumberPicker setNumber =            (NumberPicker) findViewById(R.id.setPicker);
        final NumberPicker repNumber =            (NumberPicker) findViewById(R.id.repPicker);
        final EditText restTime =              (EditText) findViewById(R.id.restTimePicker);
        final ProgressBar createWorkoutProgress =  (ProgressBar) findViewById(R.id.CreateWorkoutProgress);
        Button addWorkout =                 (Button) findViewById(R.id.UpdateButton);

        // Manipulation of widgets found in create workout activity
        setNumber.setMinValue(1);
        setNumber.setMaxValue(20);
        repNumber.setMinValue(1);
        repNumber.setMaxValue(25);
        createWorkoutProgress.setMax(100);

        //Second dialog box for naming your workout
        addWorkout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Create_Workout.this);
                alertDialog.setTitle("Nearly Done");
                alertDialog.setMessage("What workout should this be added to? ");

                final EditText workoutName = new EditText(Create_Workout.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                workoutName.setLayoutParams(lp);
                alertDialog.setView(workoutName);

                alertDialog.setPositiveButton("Add to Workout", new DialogInterface.OnClickListener() {
//Listener for add workout button.
                    public void onClick(DialogInterface dialog, int which) {

                        //Getting values from all widgets ìn Create workout and binding them to Strings
                        String Exercise = ExerciseName.getText().toString();
                        int sets = setNumber.getValue();
                        int reps = repNumber.getValue();
                        String rest = restTime.getText().toString();
                        String name = workoutName.getText().toString();

                        boolean isInserted;
                        // Input validation for all text inputs
                        if (Exercise.isEmpty()) {
                            isInserted = false;
                            Toast.makeText(Create_Workout.this, "Exercise name field was left blank", Toast.LENGTH_LONG).show();
                            return;
                        } else if (name.isEmpty()) {
                            isInserted = false;
                            Toast.makeText(Create_Workout.this, "Please specify what workout this is!", Toast.LENGTH_LONG).show();
                            return;

                        } else if (rest.isEmpty()) {
                            isInserted = false;
                            Toast.makeText(Create_Workout.this, "In order to make full use of this app's features please set a rest time", Toast.LENGTH_LONG).show();
                            return;


                            }else{
                            //Inserts values into database and adds a value to the spinner
                            isInserted = true;
                            if (isInserted = true) {
                                myDB.insertData(Exercise, sets, reps, rest, name, MuscleChoice.getSelectedItem().toString());
                                Toast.makeText(Create_Workout.this, "Exercise has been added", Toast.LENGTH_SHORT).show();

                                num = num + 20;
                                createWorkoutProgress.setProgress(num);

                                if (createWorkoutProgress.getProgress() == 100) {
                                    Toast.makeText(Create_Workout.this, "5 exercises have now been added to your Program", Toast.LENGTH_LONG).show();
                                    createWorkoutProgress.setProgress(0);

                                }
                            } else
                                Toast.makeText(Create_Workout.this, "Exercise failed to add", Toast.LENGTH_LONG).show();
                        }
                    }
                });
               alertDialog.show();
            }
        });
    }
}
