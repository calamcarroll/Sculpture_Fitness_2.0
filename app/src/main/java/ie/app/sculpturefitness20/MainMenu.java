package ie.app.sculpturefitness20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    String Choice;
    DB_Handler myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        Button deleteWorkout = (Button)findViewById(R.id.Delete_Workout);
        Button CreateButton = (Button) findViewById(R.id.Create_Workout_Button);
        Button MyWorkouts = (Button) findViewById(R.id.My_Workouts_Button);
        Button editWorkout = (Button) findViewById(R.id.Edit_Workouts_Button);
        Button Rating = (Button) findViewById(R.id.Rating);

        CreateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent openCreateWorkout = new Intent(MainMenu.this, Create_Workout.class);
                startActivity(openCreateWorkout);



            }
     });

        MyWorkouts.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent openMyWorkouts = new Intent(MainMenu.this, myWorkouts.class);
                startActivity(openMyWorkouts);
            }
        });





        deleteWorkout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent opeDeleteWorkouts = new Intent(MainMenu.this, Delete_Workout.class);
                startActivity(opeDeleteWorkouts);


            }
        });

        editWorkout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent openEditWorkouts = new Intent(MainMenu.this, EditWorkouts.class);
                startActivity(openEditWorkouts);


            }
        });
        Rating.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent openRatings = new Intent(MainMenu.this, Rating.class);
                startActivity(openRatings);

            }
        });



    }


}
