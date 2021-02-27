package fr.letord.chapeau.Vue;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import fr.letord.chapeau.R;

// MainActivity.java
public class MainActivity extends AppCompatActivity {

    // Identifier for the permission request
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;

    private Button newGameButton;
    private Button partieEnCours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.newGameButton =findViewById(R.id.buttonNewGame);
        this.partieEnCours=findViewById(R.id.buttonpartieEnCours);

        this.newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LancerConfigurationPartie();
            }
        });
        this.partieEnCours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LancerDetailsPartie();
            }
        });
        // In an actual app, you'd want to request a permission when the user performs an action
        // that requires that permission.
        getPermission();
    }

    private void LancerConfigurationPartie() {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MainActivity.this, ListJoueurActivity.class);
        startActivity(i); // brings up the second activity
    }
    private void LancerDetailsPartie() {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MainActivity.this, EtatPartieEnCoursActivity.class);
        startActivity(i); // brings up the second activity
    }

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    public void getPermission() {
        /*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {


            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {

            }
            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSIONS_REQUEST);
        }

         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }
            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_PERMISSION_SEND_SMS);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        /*
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
            }
        }

         */
        if (requestCode == REQUEST_CODE_PERMISSION_SEND_SMS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Send SMS permission granted", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
