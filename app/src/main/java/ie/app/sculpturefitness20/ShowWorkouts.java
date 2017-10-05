package ie.app.sculpturefitness20;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowWorkouts extends AppCompatActivity {

    WorkoutsAdaptor myWork;
    DB_Handler myDB_Handler;
    ArrayList<String> ExerciseName;
    ArrayList<String> MuscleName;
    ArrayList<Integer> Sets;
    ArrayList<Integer> Reps;
    ArrayList<Integer> RestTime;
    String Name;
    ListView  showWorkoutListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_workouts);

        Bundle extras = getIntent().getExtras();





        ExerciseName = new ArrayList<>();
        MuscleName = new ArrayList<>();
        Sets = new ArrayList<>();
        Reps = new ArrayList<>();
        RestTime = new ArrayList<>();
        if(extras != null){
            Name =  extras.getString(Name);

        }




        myDB_Handler = new DB_Handler(this);




        Cursor cursor = myDB_Handler.getWorkoutData(Name);


        if(cursor.getCount() == 0){
            Toast.makeText(ShowWorkouts.this, "Nothing to show ", Toast.LENGTH_LONG).show();
        }else
        {
            while (cursor.moveToNext()) {
                ExerciseName.add(cursor.getString(0));
                MuscleName.add(cursor.getString(1));
                Sets.add(Integer.valueOf(cursor.getString(2)));
                Reps.add(Integer.valueOf(cursor.getString(3)));
                RestTime.add(Integer.valueOf(cursor.getString(4)));
                showWorkoutListView = (ListView)findViewById(R.id.ShowWorkoutListView);
                myWork = new WorkoutsAdaptor(this, ExerciseName, MuscleName, Sets, Reps, RestTime);
                showWorkoutListView.setAdapter(myWork);


            }


        }





    }

}
