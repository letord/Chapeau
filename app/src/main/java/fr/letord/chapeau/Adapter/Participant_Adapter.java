package fr.letord.chapeau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import fr.letord.chapeau.Model.Participant;
import fr.letord.chapeau.R;

import java.util.ArrayList;

public class Participant_Adapter extends ArrayAdapter<Participant> {

    public Participant_Adapter(Context context, ArrayList<Participant> participants) {
        super(context,0 ,participants);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Participant participant = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        //if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_participant, parent, false);
        //}
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        // Populate the data into the template view using the data object
        tvName.setText(participant.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
