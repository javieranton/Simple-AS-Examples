package Classes;

/**
 * Created by Javier on 09/02/2016.
 */
public class Puntuation {

    private String name;
    private String attemps;

    public Puntuation(String name, String attemps){
        this.name = name;
        this.attemps = attemps;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttemps() {
        return attemps;
    }

    public void setAttemps(String attemps) {
        this.attemps = attemps;
    }

    @Override
    public String toString() {
        return name+": "+attemps;
    }
}
