package ie.app.sculpturefitness20;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdaptor extends ArrayAdapter<String>{

    ArrayList<String>MuscleName;
    ArrayList<String>ProgramName;
    ImageView myImage;
    ArrayList<String> MuscleChoice;

    public CustomAdaptor( Context context, ArrayList<String> ProgramName, ArrayList<String>MuscleChoice) {
        super(context,R.layout.my_custom_row ,ProgramName);
        this.MuscleChoice = MuscleChoice;
        this.ProgramName = ProgramName;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.my_custom_row,parent,false);
        String singleProgramName = getItem(position);
        TextView programName = (TextView) customView.findViewById(R.id.customText);
        TextView MuscleName = (TextView) customView.findViewById(R.id.MuscleType);
        myImage = (ImageView) customView.findViewById(R.id.myImage);
        RatingBar rating = (RatingBar) customView.findViewById(R.id.ratingBar);

        programName.setText(singleProgramName);

        String SingleMuscleName = MuscleChoice.get(position);
        MuscleName.setText(SingleMuscleName);
        String Back = "Back";
        String Chest = "Chest";
        String Triceps = "Triceps";
        String Biceps = "Biceps";
        String Shoulders = "Shoulders";
        String Legs = "Legs";

// Assigning an image to the image view based on the muscle type
        if(MuscleName.getText().toString().matches(Back)){
            myImage.setImageResource(R.drawable.back);
        }else if (MuscleName.getText().toString().matches(Chest)){
            myImage.setImageResource(R.drawable.chest);
        }else if (MuscleName.getText().toString().matches(Triceps)){
            myImage.setImageResource(R.drawable.tricep);
        }else if (MuscleName.getText().toString().matches(Triceps)){
            myImage.setImageResource(R.drawable.tricep);
        }else if (MuscleName.getText().toString().matches(Biceps)){
            myImage.setImageResource(R.drawable.bicep);
        }else if (MuscleName.getText().toString().matches(Legs)){
            myImage.setImageResource(R.drawable.legs);
        }else if (MuscleName.getText().toString().matches(Shoulders)){
            myImage.setImageResource(R.drawable.shoulder);
        }
        return customView;
    }
}
