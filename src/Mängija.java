import java.util.List;

public class Mängija {
    private Ruum asukoht;
    private List<Ese> asjad;
    private RündeEse relv; //relv, mis suurendab rünnakut
    private int minTugevus; //minimaalne rünnaku tugevus
    private int maxTugevus; //maksimaalne rünnaku tugevus
    //private int kaitse; //buffer tase (ala 1st 5ni)
    private int maxHP; //maksimaalne HP (mängu alguses)
    private int HP; //HP mängu kestel

    public Mängija(List<Ese> asjad, int minTugevus, int maxTugevus, int maxHP) {
        this.asjad = asjad;
        this.minTugevus = minTugevus;
        this.maxTugevus = maxTugevus;
        this.maxHP = maxHP;
        this.HP = maxHP; //mängu alguses
    }

    public int Ründa() { //Rünnaku tugevus
        return (int) ((Math.random() * (maxTugevus - minTugevus)) + minTugevus);
    }

    public int rünnakuKahju(Koletis koletis) {
        int kolliRünnak = koletis.Ründa(); //kolli klassi meetod
        if (HP - kolliRünnak > 0) {
            return HP - kolliRünnak;
        } else {
            return 0;
        }
    }

    public boolean onElus() { //kontrolli kas on elus
        if (HP > 0)
            return true;
        return false;
    }

    public String esemeListSõneks(List<Ese> esemed){
        String tulemus = esemed.toString();
        return tulemus.replace("[", "").replace("]", "");
    }

    public Ruum getAsukoht() {
        return asukoht;
    }

    public void setAsukoht(Ruum asukoht) {
        this.asukoht = asukoht;
    }

    public List<Ese> getAsjad() {
        return asjad;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMinTugevus() {
        return minTugevus;
    }

    public int getMaxTugevus() {
        return maxTugevus;
    }

    public RündeEse getRelv() {
        return relv;
    }

    public void setRelv(RündeEse relv) {
        this.relv = relv;
    }

    public void setMinTugevus(int minTugevus) {
        this.minTugevus = minTugevus;
    }

    public void setMaxTugevus(int maxTugevus) {
        this.maxTugevus = maxTugevus;
    }

    @Override
    public String toString() {
        return "Sinu asjad: " +esemeListSõneks(asjad)+ ", HP: " +HP+ ", Rünnaku tugevus: " +minTugevus+ "-" +maxTugevus;
    }
}
