package ie.app.sculpturefitness20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class Rating extends AppCompatActivity {

    DB_Handler myDB;
    ListAdapter myAdapter;
    ListView myList;
    ArrayList<String> ProgramName;
    ArrayList<String> MuscleChoice;

    String PassName;


    String Name = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        myDB = new DB_Handler(this);
        ProgramName = new ArrayList<>();
        MuscleChoice = new ArrayList<>();
        Cursor myCursor = myDB.getFirstListContents();
        RatingBar Rate;


        if (myCursor.getCount() == 0) {
            Toast.makeText(Rating.this, "No programs to show", Toast.LENGTH_LONG).show();
        } else {
            while (myCursor.moveToNext()) {
                ProgramName.add(myCursor.getString(0));
                MuscleChoice.add(myCursor.getString(1));
                myAdapter = new CustomAdaptor(this, ProgramName,MuscleChoice);
                myList = (ListView) findViewById(R.id.RatingListView);

                myList.setAdapter(myAdapter);

            }


            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {


                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Rating.this);
                    alertDialog.setMessage("Please rate this program");

                    final RatingBar Rate = new RatingBar(Rating.this);

                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    Rate.setNumStars(5);
                    Rate.setLayoutParams(lp);

                    alertDialog.setView(Rate);

                    alertDialog.setPositiveButton("Save Rating", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            int Rating = ((int) Rate.getRating());
                            String Name = String.valueOf(parent.getItemAtPosition(position));

                        }
                    });

                    alertDialog.show();

                }
            });


        }

    }
}
