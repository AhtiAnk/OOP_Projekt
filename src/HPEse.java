public class HPEse extends Ese {
    private int taastaHP;

    public HPEse(String nimi, int taastaHP) {
        super(nimi);
        this.taastaHP = taastaHP;
    }

    public int getTaastaHP() {
        return taastaHP;
    }
}
