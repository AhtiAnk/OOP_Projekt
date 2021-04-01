import java.util.List;

public class Mängja {
    private Ruum asukoht;
    private List<Ese> asjad;
    //lisada veel vajalikke isendivälju
    private int minTugevus; //minimaalne rünnaku tugevus
    private int maxTugevus; //maksimaalne rünnaku tugevus
    //private int kaitse; //buffer tase (ala 1st 5ni)
    private int maxHP; //maksimaalne HP (mängu alguses)
    private int HP; //HP mängu kestel

    public Mängja(int minTugevus, int maxTugevus, int maxHP) {
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

    public Ruum getAsukoht() {
        return asukoht;
    }

    public List<Ese> getAsjad() {
        return asjad;
    }

    public int getHP() {
        return HP;
    }
}
