package fr.letord.chapeau.Controle;

import android.content.Context;
import fr.letord.chapeau.Model.AccesLocal;
import fr.letord.chapeau.Model.Gage;
import fr.letord.chapeau.Model.Participant;
import fr.letord.chapeau.Model.SMSEnvoye;

import java.util.ArrayList;

public final class Controle {

    //déclarations
    private static Controle instance = null;
    private static ArrayList<Participant> participants;
    private static ArrayList<Gage> gages;
    private static ArrayList<SMSEnvoye> messages;
    private static AccesLocal accesLocal;

    public Controle() {
        super();
    }

    public static final Controle getInstance(Context context){
        if(Controle.instance==null){
            Controle.instance=new Controle();
            accesLocal=new AccesLocal(context);
            participants=accesLocal.recupAllParticipant();
            gages=accesLocal.recupAllGages();
            messages=accesLocal.recupAllSMS();
        }
        return Controle.instance;
    }


    /**
     * Ajout d'un participants à la liste de joueur
     * @param name
     * @param phone
     */
    public void ajoutParticipant(String name, String phone){
        accesLocal.ajout(new Participant(name, phone));
        updateParticipants();
    }
    public void ajoutGage(String intitule){
        accesLocal.ajout(new Gage(intitule));
        updateGages();
    }
    public void ajoutSMS(String joueur, String message, String cible){
        accesLocal.ajout(new SMSEnvoye(joueur,message,cible));
        updateSMS();
    }

    public ArrayList<Participant> getListParticipant (){
        return this.participants;
    }
    public ArrayList<Gage> getListGage (){ return this.gages; }
    public ArrayList<SMSEnvoye> getListSMS(){ return this.messages;}

    public void supprimeParticipant(int position){
        Participant participant = getListParticipant().get(position);
        accesLocal.DeleteJoueur(participant.getName(), participant.getNumber());
        updateParticipants();
    }
    public void supprimeGage(int position){
        Gage gage = getListGage().get(position);
        accesLocal.DeleteGage(gage.getIntitule());
        updateGages();
    }

    public void supprimePartie(){
        accesLocal.DeletePartie();
        updateGages();
    }

    public void updateParticipants(){
        this.participants=accesLocal.recupAllParticipant();
    }
    public void updateGages(){
        this.gages=accesLocal.recupAllGages();
    }
    public void updateSMS(){
        this.messages=accesLocal.recupAllSMS();
    }

}
