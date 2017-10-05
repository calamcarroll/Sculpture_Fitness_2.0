package ie.app.sculpturefitness20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete_Workout extends AppCompatActivity {
    DB_Handler myDB;
    ListAdapter deleteAdapter;
    ListView myList;
    ArrayList<String> ProgramName;
    ArrayList<String> MuscleChoice;

    String Pass;


    String Name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_workout);


        myDB = new DB_Handler(this);
        ProgramName = new ArrayList<>();
        MuscleChoice = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        Cursor myCursor = myDB.getFirstListContents();

        if(extras != null){
            Name =  extras.getString(Name);

        }



        if (myCursor.getCount() == 0) {
            Toast.makeText(Delete_Workout.this, "No programs to show", Toast.LENGTH_LONG).show();
        } else {
            while (myCursor.moveToNext()) {
                ProgramName.add(myCursor.getString(0));
                MuscleChoice.add(myCursor.getString(1));
                deleteAdapter = new CustomAdaptor(this, ProgramName,MuscleChoice);
                myList = (ListView) findViewById(R.id.DeleteWorkoutListView);


                myList.setAdapter(deleteAdapter);



            }


            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
//                  Intent openShowWorkout = new Intent(Delete_Workout.this, .class);
//
//                    openShowWorkout.putExtra(PassName, String.valueOf(parent.getItemAtPosition(position)));
//                    startActivity(openShowWorkout);


                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Delete_Workout.this);
                    alertDialog.setTitle("Are you sure you want to delete " + String.valueOf(parent.getItemAtPosition(position)) + "?");

                    alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            Pass = String.valueOf(parent.getItemAtPosition(position));

                            int Delete = myDB.DeleteData(Pass);


                            if (Delete>0) {
                                Toast.makeText(Delete_Workout.this, "Success", Toast.LENGTH_LONG).show();
                                Intent back = new Intent(Delete_Workout.this, MainMenu.class);


                                startActivity(back);


                            }else{
                                Toast.makeText(Delete_Workout.this, "Failure", Toast.LENGTH_LONG).show();
                            }
                        }
                    });




                    alertDialog.show();


                }

            });

        }

    }

}
