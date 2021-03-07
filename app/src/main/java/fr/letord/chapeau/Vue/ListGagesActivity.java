package fr.letord.chapeau.Vue;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import fr.letord.chapeau.Controle.Controle;
import fr.letord.chapeau.Model.Gage;
import fr.letord.chapeau.Model.Participant;
import fr.letord.chapeau.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListGagesActivity extends AppCompatActivity implements ConfirmDeleteDialog.ConfirmDeleteListener {



    EditText nameGage;
    private Button addGage;
    private Button buttonJouer;
    private ListView listView;
    private Controle controle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listgages);

        this.nameGage = findViewById(R.id.editTextTextPersonName);
        this.addGage = findViewById(R.id.addButton);
        this.buttonJouer = findViewById(R.id.ButtonPlay);
        this.listView = findViewById(R.id.listviewgages);
        this.controle= Controle.getInstance(this);

        listView.setAdapter(new ArrayAdapter<Gage>(this, android.R.layout.simple_list_item_1, controle.getListGage()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog(position);
                confirmDeleteDialog.show(getSupportFragmentManager(), "confirm dialog");
            }
        });

        addGage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intitule = nameGage.getText().toString();
                controle.ajoutGage(intitule);
                majListView();
                nameGage.setText("");
            }
        });

        buttonJouer = findViewById(R.id.ButtonPlay);
        buttonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controle.getListGage().size() < controle.getListParticipant().size()){
                    Toast.makeText(ListGagesActivity.this, "Vous avez moins de gages que de joueurs", Toast.LENGTH_SHORT).show();
                }
                else {
                    SendMessageAtAllPlayer();
                    finish();
                }
            }
        });
    }

    private void SendMessageAtAllPlayer() {
        ArrayList<Participant> participants = controle.getListParticipant();
        ArrayList<Gage> gages = controle.getListGage();
        Collections.shuffle(participants);
        Collections.shuffle(gages);
        for (int i = 0; i < participants.size(); i++) {
            int x=i+1;
            if (i==participants.size()-1){
                x=0;
            }
            String message = participants.get(i).getName()+",\n"
                    +"Tu dois "
                    +gages.get(i).getIntitule()+participants.get(x).getName();
            String destinataire = participants.get(i).getNumber();
            SendMessage(destinataire, message);
            controle.ajoutSMS(participants.get(i).getName(), ", Tu dois "+gages.get(i).getIntitule()+participants.get(x).getName(), participants.get(x).getName());
        }
        retourMainActivity();
    }
    private void SendMessage(String phone, String message){
        SmsManager smsMain = SmsManager.getDefault();
        smsMain.sendTextMessage(phone, null, message, null, null);
    }

    public void majListView( ) {
        listView.setAdapter(new ArrayAdapter<Gage>(this, android.R.layout.simple_list_item_1, controle.getListGage()));
    }
    private boolean checkPermission(String permission){
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }
    private void retourMainActivity(){
        Intent i = new Intent(ListGagesActivity.this, MainActivity.class);
        startActivity(i); // brings up the second activity
    }

    @Override
    public void DeletePlayer(boolean a,int position) {
        if(a){
            controle.supprimeGage(position);
            majListView();
        }
    }
}
