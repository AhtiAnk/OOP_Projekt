public class HPEse extends Ese {
    private int taastaHP; //väärtus, kui palju taastada HP

    public HPEse(String nimi, int taastaHP) {
        super(nimi);
        this.taastaHP = taastaHP;
    }

    public int getTaastaHP() {
        return taastaHP;
    }

    @Override
    public String toString() {
        return super.toString() + " (HP +" +getTaastaHP()+ ")";
    }
}
