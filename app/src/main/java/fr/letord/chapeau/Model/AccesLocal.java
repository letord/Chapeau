package fr.letord.chapeau.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.letord.chapeau.Tools.MySQLiteOpenHelper;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AccesLocal {

    private static final String TABLE_PARTICIPANT ="PARTICIPANT";
    private static final String PRIMARY_KEY_PARTICIPANT = "ID_USER";
    private static final String KEY_USER_NAME = "NAME";
    private static final String KEY_USER_PHONE = "PHONE";
    private static final String TABLE_GAGE ="GAGE";
    private static final String PRIMARY_KEY_GAGE = "ID_GAGE";
    private static final String KEY_GAGE_INTITULE = "INTITULE";
    private static final String TABLE_SMS ="SMS";
    private static final String PRIMARY_KEY_SMS = "ID_SMS";
    private static final String KEY_SMS_JOUEUR = "JOUEUR";
    private static final String KEY_SMS_MESSAGE = "MESSAGE";
    private static final String KEY_SMS_CIBLE = "CIBLE";
    //propriétés
    private String nomParticipantbd = "bdParticipant.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomParticipantbd, null, versionBase);
    }

    /**
     * Ajout d'un participant à la base de donnée
     * @param participant
     */
    public void ajout(Participant participant){
        bd = accesBD.getWritableDatabase();
        String rec = String.format
                ("INSERT INTO %s ( %s, %s) VALUES (\"%s \" , \"%s\");",
                        TABLE_PARTICIPANT,
                        KEY_USER_NAME, KEY_USER_PHONE,
                        participant.getName(), participant.getNumber());
        bd.execSQL(rec);
    }
    public void ajout(Gage gage){
        bd = accesBD.getWritableDatabase();
        String rec = String.format
                ("INSERT INTO %s ( %s) VALUES (\"%s \");",
                        TABLE_GAGE,
                        KEY_GAGE_INTITULE,
                        gage.getIntitule());
        bd.execSQL(rec);
    }
    public void ajout(SMSEnvoye sms){
        bd = accesBD.getWritableDatabase();
        String rec = String.format
                ("INSERT INTO %s ( %s, %s, %s) VALUES (\"%s \" , \"%s\" , \"%s\");",
                        TABLE_SMS,
                        KEY_SMS_JOUEUR, KEY_SMS_MESSAGE, KEY_SMS_CIBLE,
                        sms.getJoueur(), sms.getGage(), sms.getCible());
        bd.execSQL(rec);
    }

    public ArrayList<Gage> recupAllGages(){
        bd = accesBD.getReadableDatabase();
        String read = String.format
                ("SELECT * FROM %s ;",
                        TABLE_GAGE);
        Cursor cursor = bd.rawQuery(read, null);
        ArrayList<Gage> gages = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                do {

                    String intitule = cursor.getString(cursor.getColumnIndex(KEY_GAGE_INTITULE));
                    gages.add(new Gage(intitule));
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return gages;
    }
    public ArrayList<Participant> recupAllParticipant(){
        bd = accesBD.getReadableDatabase();
        String read = String.format
                ("SELECT * FROM %s ;",
                        TABLE_PARTICIPANT);
        Cursor cursor = bd.rawQuery(read, null);
        ArrayList<Participant> participants = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                do {

                    String name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
                    String phone = cursor.getString(cursor.getColumnIndex(KEY_USER_PHONE));
                    participants.add(new Participant(name, phone));
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return participants;
    }
    public ArrayList<SMSEnvoye> recupAllSMS(){
        bd = accesBD.getReadableDatabase();
        String read = String.format
                ("SELECT * FROM %s ;",
                        TABLE_SMS);
        Cursor cursor = bd.rawQuery(read, null);
        ArrayList<SMSEnvoye> messages = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                do {

                    String joueur = cursor.getString(cursor.getColumnIndex(KEY_SMS_JOUEUR));
                    String message = cursor.getString(cursor.getColumnIndex(KEY_SMS_MESSAGE));
                    String cible = cursor.getString(cursor.getColumnIndex(KEY_SMS_MESSAGE));
                    messages.add(new SMSEnvoye(joueur,message,cible));
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return messages;
    }

    public void DeleteJoueur(String name, String phone){
        bd = accesBD.getWritableDatabase();
        String rec = String.format
                ("DELETE FROM %s WHERE %s = \"%s\" AND %s = \"%s\";",
                TABLE_PARTICIPANT,
                KEY_USER_NAME, name,
                KEY_USER_PHONE, phone);
        bd.execSQL(rec);
    }
    public void DeleteGage(String intitule){
        bd = accesBD.getWritableDatabase();
        String rec = String.format
                ("DELETE FROM %s WHERE %s = \"%s\";",
                        TABLE_GAGE,
                        KEY_GAGE_INTITULE, intitule);
        bd.execSQL(rec);
    }
    public void DeletePartie(){
        bd = accesBD.getWritableDatabase();
        String rec = String.format
                ("DELETE FROM %s ;",
                        TABLE_SMS);
        bd.execSQL(rec);
    }
}
