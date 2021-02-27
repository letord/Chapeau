package fr.letord.chapeau.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import fr.letord.chapeau.Controle.Controle;
import fr.letord.chapeau.Model.Participant;
import fr.letord.chapeau.Model.SMSEnvoye;
import fr.letord.chapeau.R;

import java.util.ArrayList;

public class EtatPartieEnCoursActivity extends AppCompatActivity implements ConfirmDeleteDialog.ConfirmDeleteListener{

    private Button buttonReinitialiser;
    private ListView listView;
    private Controle controle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partieencours);
        this.controle= Controle.getInstance(this);

        this.buttonReinitialiser = findViewById(R.id.button_reinitialiser);
        this.listView = findViewById(R.id.listView_GageEnCours);

        buttonReinitialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();
                confirmDeleteDialog.show(getSupportFragmentManager(), "confirm dialog");
            }
        });


        this.listView.setAdapter(new ArrayAdapter<SMSEnvoye>(this, android.R.layout.simple_list_item_1, controle.getListSMS()));

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EtatPartieEnCoursActivity.this, controle.getListSMS().get(position).getGage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void DeletePlayer(boolean a, int position) {
        if(a){
            controle.supprimePartie();
            majListView();
            this.finish();
        }
    }
    public void majListView( ) {
        this.listView.setAdapter(new ArrayAdapter<SMSEnvoye>(this, android.R.layout.simple_list_item_1, controle.getListSMS()));
    }
}
