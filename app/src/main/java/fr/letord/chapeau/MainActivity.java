package fr.letord.chapeau;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button buttonSend=null;
    public ArrayList<String> listNumber= new ArrayList<String>();
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSend = findViewById(R.id.ButtonSend);
        buttonSend.setEnabled(false);

        //Verifie si la permission est bien acceptée, sinon redemande la permission
        if(checkPermission(Manifest.permission.SEND_SMS)){
            buttonSend.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                    ( Manifest.permission.SEND_SMS)}, REQUEST_CODE_PERMISSION_SEND_SMS);
        }

        //Action du bouton à chaque fois qu'on clique dessus
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsMain = SmsManager.getDefault();
                smsMain.sendTextMessage("0627521159", null, "Message envoyé depuis appli", null, null);
                Toast.makeText(MainActivity.this, "SMS send", Toast.LENGTH_LONG).show();
            }
        });
    }


    private boolean checkPermission(String permission){
        int checkpermission = ContextCompat.checkSelfPermission(this, permission);
        return checkpermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_SEND_SMS:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    buttonSend.setEnabled(true);
                }
        }
    }
    public void RecupContacts(){
        //accès au contenu du mobile
        ContentResolver contentResolver = this.getContentResolver();
        //récupération des contacts dans un curseur
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
        if (cursor==null){
            Log.d("recup ", "################# erreur cursor null");
        }
        else{

        }
    }
}