package fr.letord.chapeau.Tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

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

    final private String creationParticipantTable =String.format("CREATE TABLE %s ( %s INT PRIMARY KEY, %s TEXT NOT NULL ,%s TEXT NOT NULL);",
            TABLE_PARTICIPANT, PRIMARY_KEY_PARTICIPANT, KEY_USER_NAME, KEY_USER_PHONE);
    final private String creationGageTable = String.format("CREATE TABLE %s ( %s INT PRIMARY KEY, %s TEXT NOT NULL);",
            TABLE_GAGE, PRIMARY_KEY_GAGE, KEY_GAGE_INTITULE);
    final private String creationParticipantSMS =String.format("CREATE TABLE %s ( %s INT PRIMARY KEY, %s TEXT NOT NULL ,%s TEXT NOT NULL, %s TEXT NOT NULL);",
            TABLE_SMS, PRIMARY_KEY_SMS, KEY_SMS_JOUEUR, KEY_SMS_MESSAGE,  KEY_SMS_CIBLE);

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Si changement de BD
     * @param db Database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creationParticipantTable);
        db.execSQL(creationGageTable);
        db.execSQL(creationParticipantSMS);
    }

    /**
     * Si changement de version
     * @param db Database
     * @param oldVersion ancienne version
     * @param newVersion nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
