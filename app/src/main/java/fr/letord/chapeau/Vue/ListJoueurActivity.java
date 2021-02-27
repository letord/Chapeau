package fr.letord.chapeau.Vue;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import fr.letord.chapeau.Controle.Controle;
import fr.letord.chapeau.Model.AccesLocal;
import fr.letord.chapeau.Model.Participant;
import fr.letord.chapeau.R;

public class ListJoueurActivity extends AppCompatActivity implements ConfirmDeleteDialog.ConfirmDeleteListener {



    private EditText namePlayer;
    private EditText phonePlayer;
    private Button addPlayer;
    private Button buttonSuivant;
    private ListView listView;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listjoueur);

        this.namePlayer = findViewById(R.id.editTextTextPersonName);
        this.phonePlayer = findViewById(R.id.editTextPhone);
        this.addPlayer = findViewById(R.id.addButton);
        this.listView = (ListView) findViewById(R.id.listviewjoueur);
        this.controle= Controle.getInstance(this);

        this.listView.setAdapter(new ArrayAdapter<Participant>(this, android.R.layout.simple_list_item_1, controle.getListParticipant()));

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog(position);
                confirmDeleteDialog.show(getSupportFragmentManager(), "confirm dialog");
            }
        });

        this.addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = namePlayer.getText().toString();
                String phone = phonePlayer.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(ListJoueurActivity.this, "il faut remplir le nom", Toast.LENGTH_SHORT).show();
                }
                else if(phone.isEmpty()){
                    Toast.makeText(ListJoueurActivity.this, "il faut remplir le telephone", Toast.LENGTH_SHORT).show();
                }
                else if (name.isEmpty() && phone.isEmpty()){
                    Toast.makeText(ListJoueurActivity.this, "il faut remplir le nom et le telephone", Toast.LENGTH_SHORT).show();
                }
                else{
                    controle.ajoutParticipant(name, phone);
                    majListView();
                    namePlayer.setText("");
                    phonePlayer.setText("");
                }
            }
        });

        this.buttonSuivant = findViewById(R.id.buttonSuivant);
        this.buttonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchConfigurationGages();
            }
        });


    }

    public void launchConfigurationGages() {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(ListJoueurActivity.this, ListGagesActivity.class);
        startActivity(i); // brings up the second activity
    }

    public void majListView( ) {

        listView.setAdapter(new ArrayAdapter<Participant>(this, android.R.layout.simple_list_item_1, controle.getListParticipant()));
    }

    @Override
    public void DeletePlayer(boolean a,int position) {
        if(a){
            this.controle.supprimeParticipant(position);
            majListView();
        }
    }

}