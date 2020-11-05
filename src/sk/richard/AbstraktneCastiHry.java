package sk.richard;

public class AbstraktneCastiHry  {
    private final int rychlostHry;
    private boolean hraBezi;

    public AbstraktneCastiHry(){
        this.rychlostHry=150;
        this.hraBezi=false;
    }

    public int getRychlostHry() {
        return rychlostHry;
    }

    public boolean isHraBezi() {
        return hraBezi;
    }

    public void setHraBezi(boolean hraBezi) {
        this.hraBezi = hraBezi;
    }

}
