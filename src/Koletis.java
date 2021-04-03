public class Koletis {
    //koletise isendiväljad
    private String nimi;
    private String kirjeldus;
    private int minTugevus; //minimaalne rünnaku tugevus
    private int maxTugevus; //maksimaalne rünnaku tugevus
    //private int kaitse; //buffer tase (ala 1st 5ni)
    private int maxHP; //maksimaalne HP (mängu alguses)
    private int HP; //HP mängu kestel

    public Koletis(String nimi, String kirjeldus, int minTugevus, int maxTugevus, int maxHP) {
        this.nimi = nimi;
        this.kirjeldus = kirjeldus;
        this.minTugevus = minTugevus;
        this.maxTugevus = maxTugevus;
        //this.kaitse = kaitse
        this.maxHP = maxHP;
        this.HP = maxHP; //alguses
    }
    public int Ründa() { //Rünnaku tugevus
        return (int) ((Math.random() * (maxTugevus - minTugevus)) + minTugevus);
    }
    public int rünnakuKahju(Mängija mängija) {
        int mängijaRünnak = mängija.Ründa(); //mängija klassi meetod
        if (mängija.getRelv() != null) { //Kontrollib kas mängjal on relv, kui on siis suurendab rünnakut
            mängijaRünnak =+ mängija.getRelv().getSuurendarünnakut();
        }
        if (HP - mängijaRünnak > 0) {
            return HP - mängijaRünnak;
        } else {
            return 0;
        }
    }
    public static Koletis suvalineKoll() {
        int suvaline = (int) (Math.random() * 3) + 1;
        Koletis koll = null;
        switch (suvaline) {
            case 1:
                koll = koll1();
                break;
            case 2:
                koll = koll2();
                break;
            case 3:
                koll = koll3();
                break;
        }
        return koll;
    }

    public static Koletis koll1() {
        return new Koletis("Koll1", "Kolli kirjeldus", 5, 10, 40);
    }
    public static Koletis koll2() {
        return new Koletis("Koll2", "kolli kirjeldus", 5, 15, 50);
    }
    public static Koletis koll3() {
        return new Koletis("Koll3", "kolli kirjeldus", 10, 20, 60);
    }

    public boolean onElus() { //kui HP jõuab nulli, return false
        if (HP > 0)
            return true;
        return false;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }

    public int getHP() {
        return HP;
    }
}


