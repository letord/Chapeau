package fr.letord.chapeau.Model;

public class SMSEnvoye {
    private String joueur;
    private String gage;
    private String cible;

    public SMSEnvoye(String joueur, String gage, String cible){
        this.joueur=joueur;
        this.cible=cible;
        this.gage=gage;
    }
    public String toString(){
        return joueur;
    }

    public String getGage() {
        return gage;
    }

    public void setGage(String gage) {
        this.gage = gage;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }
}
