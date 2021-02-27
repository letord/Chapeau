package fr.letord.chapeau.Vue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import fr.letord.chapeau.R;

public class ConfirmDeleteDialog extends AppCompatDialogFragment {

    private ConfirmDeleteListener listener;
    private int position=0;

    public ConfirmDeleteDialog(){

    }

    public ConfirmDeleteDialog(int position){
        this.position=position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.confir_delete_dialog,null);

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.DeletePlayer(false, position);
                    }
                })
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.DeletePlayer(true, position);
                    }
                }).setMessage("Êtes-vous sûr de vouloir le supprimer ?");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener =(ConfirmDeleteListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implemented ConfirmDeleteListener");
        }
    }

    public interface ConfirmDeleteListener{
        void DeletePlayer(boolean a, int position);
    }
}
