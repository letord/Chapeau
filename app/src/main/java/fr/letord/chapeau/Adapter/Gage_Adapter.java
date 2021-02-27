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
import fr.letord.chapeau.Model.Participant;
import fr.letord.chapeau.R;

import java.util.ArrayList;

public class Gage_Adapter extends ArrayAdapter<Gage> {

    public Gage_Adapter(@NonNull Context context, ArrayList<Gage> gages) {
        super(context, 0, gages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Gage gage = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_gage, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvIntituleGage);
        // Populate the data into the template view using the data object
        tvName.setText(gage.getIntitule());
        // Return the completed view to render on screen
        return convertView;
    }
}
