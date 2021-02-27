package fr.letord.chapeau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.letord.chapeau.Model.Gage;
import fr.letord.chapeau.Model.SMSEnvoye;
import fr.letord.chapeau.R;

import java.util.ArrayList;

public class Message_Adapter extends ArrayAdapter<SMSEnvoye> {
    public Message_Adapter(@NonNull Context context, ArrayList<SMSEnvoye> messages) {
        super(context,0, messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SMSEnvoye message = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameJoueur);
        // Populate the data into the template view using the data object
        tvName.setText(message.getJoueur());
        // Return the completed view to render on screen
        return convertView;
    }
}
