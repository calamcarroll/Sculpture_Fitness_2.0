package ie.app.sculpturefitness20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutsAdaptor extends ArrayAdapter<String>{
    ArrayList<String> ExerciseName;
    ArrayList<String> MuscleName;
    ArrayList<Integer> mySets;
    ArrayList<Integer> myReps;
    ArrayList<Integer> myRestTime;


    public WorkoutsAdaptor(Context context, ArrayList<String> ExerciseName, ArrayList<String> MuscleName, ArrayList<Integer> Sets, ArrayList<Integer> Reps, ArrayList<Integer> RestTime) {
        super(context, R.layout.workouts_row, ExerciseName);
        this.ExerciseName = ExerciseName;
        this.MuscleName = MuscleName;
        this.mySets= Sets;
        this.myReps = Reps;
        this.myRestTime = RestTime;

    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.workouts_row,parent,false);


        //Binding for all textVies

        TextView Exercise = (TextView) customView.findViewById(R.id.showExerciseName);
        TextView Muscle = (TextView) customView.findViewById(R.id.showMuscleType);
        TextView Sets = (TextView) customView.findViewById(R.id.showExerciseSets);
        TextView Reps = (TextView) customView.findViewById(R.id.showExerciseReps);
        TextView RestTime = (TextView) customView.findViewById(R.id.showExerciseRestTime);

        String SingleExerciseName = ExerciseName.get(position);
        String SingleMuscleName = MuscleName.get(position);
        Integer SingleSets = mySets.get(position);
        Integer SingleReps = myReps.get(position);
        Integer SingleRestTime = myRestTime.get(position);

        Exercise.setText(SingleExerciseName);
        Muscle.setText(SingleMuscleName);
        Sets.setText(SingleSets.toString());
        Reps.setText(SingleReps.toString());
        RestTime.setText(SingleRestTime.toString());

        return customView;
    }
}
