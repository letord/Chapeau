package fr.letord.chapeau.Model;

public class Participant {
    private String name;
    private String number;

    public Participant(){

    }

    public Participant(String name, String number){
        this.name=name;
        this.number=number;
    }

    /**
     * @return affichage Participants
     */
    public String toString(){
        return this.name+"   "+this.number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
