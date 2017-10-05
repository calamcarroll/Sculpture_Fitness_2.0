package ie.app.sculpturefitness20;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowEditWorkouts extends AppCompatActivity {

    WorkoutsAdaptor myWork;
    DB_Handler myDB_Handler;
    ArrayList<String> ExerciseName;
    ArrayList<String> MuscleName;
    ArrayList<Integer> Sets;
    ArrayList<Integer> Reps;
    ArrayList<Integer> RestTime;
    String Name;
    ListView showWorkoutListView;
    String PassName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edit_workouts);

        Bundle extras = getIntent().getExtras();


        ExerciseName = new ArrayList<>();
        MuscleName = new ArrayList<>();
        Sets = new ArrayList<>();
        Reps = new ArrayList<>();
        RestTime = new ArrayList<>();
        if (extras != null) {
            Name = extras.getString(Name);

        }

        myDB_Handler = new DB_Handler(this);


        Cursor cursor = myDB_Handler.getWorkoutData(Name);


        if (cursor.getCount() == 0) {
            Toast.makeText(ShowEditWorkouts.this, "Nothing to show ", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                ExerciseName.add(cursor.getString(0));
                MuscleName.add(cursor.getString(1));
                Sets.add(Integer.valueOf(cursor.getString(2)));
                Reps.add(Integer.valueOf(cursor.getString(3)));
                RestTime.add(Integer.valueOf(cursor.getString(4)));
                showWorkoutListView = (ListView) findViewById(R.id.ShowEditListView);
                myWork = new WorkoutsAdaptor(this, ExerciseName, MuscleName, Sets, Reps, RestTime);
                showWorkoutListView.setAdapter(myWork);
            }


        }
        showWorkoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent openShowWorkout = new Intent(ShowEditWorkouts.this, UpdateProgram.class);

                openShowWorkout.putExtra(PassName, String.valueOf(parent.getItemAtPosition(position)));
                startActivity(openShowWorkout);

            }

        });
    }
}
