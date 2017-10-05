package ie.app.sculpturefitness20;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class UpdateProgram extends AppCompatActivity {

    DB_Handler myDB;
    String Name;
    EditText WorkoutName;
    EditText restTime;
    Button Update;
    NumberPicker SetPicker;
    NumberPicker RepPicker;
    ListView myList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_program);

        WorkoutName = (EditText) findViewById(R.id.WorkoutNameTextBox);
        restTime = (EditText) findViewById(R.id.restTimePicker);
        Update = (Button) findViewById(R.id.UpdateButton);
        SetPicker = (NumberPicker) findViewById(R.id.setPicker);
        RepPicker = (NumberPicker) findViewById(R.id.repPicker);
        myList =   (ListView) findViewById(R.id.ShowWorkoutListView);

        SetPicker.setMinValue(1);
        SetPicker.setMaxValue(20);
        RepPicker.setMinValue(1);
        RepPicker.setMaxValue(25);


        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            Name = extras.getString(Name);

        }

        myDB = new DB_Handler(this);

        Cursor cursor = myDB.getExerciseData(Name);

        if (cursor.getCount() == 0) {
            Toast.makeText(UpdateProgram.this, "No data to show "  + Name, Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
              WorkoutName.setText(cursor.getString(0));
                restTime.setText(cursor.getString(1));


            }

        }

        Update.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String name = WorkoutName.getText().toString();
                int sets = SetPicker.getValue();
                int reps = RepPicker.getValue();
                String rest = restTime.getText().toString();

                myDB.updateProgram(name, sets, reps, rest, Name);


                    Toast.makeText(UpdateProgram.this, "Workout Successfully updated ", Toast.LENGTH_LONG).show();

                Intent open = new Intent(UpdateProgram.this, MainMenu.class);
                startActivity(open);

            }
        });
    }
}