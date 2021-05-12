package OOP_Projekt;

import java.io.Serializable;
import java.util.List;

public class Mängija implements Serializable {
    private Ruum asukoht;
    private List<Ese> asjad;
    private RündeEse relv; //relv, mis suurendab rünnakut
    private int minTugevus; //minimaalne rünnaku tugevus
    private int maxTugevus; //maksimaalne rünnaku tugevus
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
            HP = HP - kolliRünnak;
            return HP;
        } else {
            HP = 0;
            return HP;
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

    public void võtaEse(String nimi){
        for (int i = 0; i < asukoht.getEsemed().size(); i++) {
            if (nimi.equals(asukoht.getEsemed().get(i).getNimi())) {
                asjad.add(asukoht.getEsemed().get(i));
                asukoht.getEsemed().remove(i);
                break;
            }
        }
    }

    public void kasuta(String nimi){
        for (int i = 0; i < asjad.size(); i++) {
            if (nimi.equals(asjad.get(i).getNimi())) { //Kontrollib kas selline ese on mängijal
                if (asjad.get(i) instanceof HPEse) { //Kontrollib kas kasutatav ese on HPEse, kui on siis taastab HP
                    HP += ((HPEse) asjad.get(i)).getTaastaHP();
                    if (HP > maxHP) //Kontrollib, et HP ei oleks üle max väärtuse
                        HP = maxHP;
                    asjad.remove(i);
                    System.out.println("Sinu HP taastati");

                    break;
                }
                else if (asjad.get(i) instanceof RündeEse) { //Võtab kasutusele, kui on ründeese
                    relv = (RündeEse) asjad.get(i);
                    System.out.println("Su rünnak on nüüd tugevam");
                    break;
                }
                else {
                    System.out.println("Ei saa kasutada");
                    break;
                }
            }
        }
    }

    public String kasRelvOnKasutusel(){
        if (relv != null)
            return " " + relv.toString();
        return "";
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

    public void setAsjad(List<Ese> asjad) {
        this.asjad = asjad;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
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
        return "\nSinu asjad: " +esemeListSõneks(asjad)+ ", HP: " +HP+ ", Rünnaku tugevus: " +minTugevus+ "-" +maxTugevus +
                ", Relv: " + kasRelvOnKasutusel();
    }
}
