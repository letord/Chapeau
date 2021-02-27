package fr.letord.chapeau.Model;

public class Gage {
    private String intitule;

    public Gage(){

    }
    public Gage(String intitule){
        this.intitule=intitule;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public String toString(){
        return this.intitule;
    }
}
