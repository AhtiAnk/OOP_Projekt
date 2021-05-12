package OOP_Projekt;

import java.io.Serializable;

public class Koletis implements Serializable {
    private String nimi;
    // String kirjeldus;
    private int minTugevus; //minimaalne rünnaku tugevus
    private int maxTugevus; //maksimaalne rünnaku tugevus
    private int maxHP; //maksimaalne HP (mängu alguses)
    private int HP; //HP mängu kestel

    public Koletis(String nimi, int minTugevus, int maxTugevus, int maxHP) {
        this.nimi = nimi;
        //this.kirjeldus = kirjeldus;
        this.minTugevus = minTugevus;
        this.maxTugevus = maxTugevus;
        this.maxHP = maxHP;
        this.HP = maxHP; //alguses
    }
    public int Ründa() { //Rünnaku tugevus
        return (int) ((Math.random() * (maxTugevus - minTugevus)) + minTugevus);
    }
    public int rünnakuKahju(Mängija mängija) {
        int mängijaRünnak = mängija.Ründa(); //mängija klassi meetod
        if (mängija.getRelv() != null) { //Kontrollib kas mängijal on relv, kui on siis suurendab rünnakut
            mängijaRünnak += mängija.getRelv().getSuurendarünnakut();
        }
        if (HP - mängijaRünnak > 0) {
            HP = HP - mängijaRünnak;
            return HP;
        } else {
            HP = 0;
            return HP;
        }
    }
    //Loob suvalise koletise ruumi jaoks
    public static Koletis suvalineKoll() {
        int suvaline = (int) (Math.random() * 3) + 1;
        Koletis koll = null;
        switch (suvaline) {
            case 1:
                koll = ork();
                break;
            case 2:
                koll = vampiir();
                break;
            case 3:
                koll = lohe();
                break;
        }
        return koll;
    }

    //Erinevad koletised
    public static Koletis ork() {
        return new Koletis("ork", 5, 10, 40);
    }
    public static Koletis vampiir() {
        return new Koletis("vampiir", 5, 15, 50);
    }
    public static Koletis lohe() {
        return new Koletis("lohe", 10, 20, 60);
    }

    public boolean onElus() { //kui HP jõuab nulli, return false
        if (HP > 0)
            return true;
        return false;
    }

    public String getNimi() {
        return nimi;
    }

    /*public String getKirjeldus() {
        return kirjeldus;
    }*/

    public int getHP() {
        return HP;
    }

    @Override
    public String toString() {
       return "\n" + nimi + " - Rünnaku tugevus: " +minTugevus+ "-" +maxTugevus + ", HP: " + maxHP;
    }
}