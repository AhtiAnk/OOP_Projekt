package OOP_Projekt;

import java.io.Serializable;
import java.util.*;

import static java.util.Arrays.asList;

public class Ruum implements Serializable {
    private String nimi;
    private String ruumi_kirjeldus;
    private Koletis koletis;
    private List<Ese> esemed;

    public Ruum(String nimi, String ruumi_kirjeldus, Koletis koletis, List<Ese> esemed) {
        this.nimi = nimi;
        this.ruumi_kirjeldus = ruumi_kirjeldus;
        this.koletis = koletis;
        this.esemed = esemed;
    }

    //Loob uue ruumi, kui mängja liigub
    public static Ruum uusSuvalineRuum() {
        String nimi = null;
        String kirjeldus = null;
        List<Ese> esemed = null;
        int suvaline = (int) (Math.random() * 3) + 1;
        switch (suvaline) {
            case 1:
                nimi = "tuba1";
                kirjeldus = "\nSa astud ruumi, mis haiseb tugevalt kopituse järgi. Su saapad pritsivad iga sammuga vett üles.\n" + "Midagi krabistab su selja taga. Ole valmis võitluseks!";
                esemed = new ArrayList<>(asList(new RündeEse("mõõk", 4)));
                break;
            case 2:
                nimi = "tuba2";
                kirjeldus = "\nSa kõnnid läbi pimeda koridori kuni jõuad hiiglasliku tammepuidust ukseni.\n" +  "Sa avad ukse ning kaks kollast silma hiilgavad sulle pimeduses vastu. Ole valmis võitluseks!";
                esemed = new ArrayList<>(asList(new HPEse("potion", 5)));
                break;
            case 3:
                nimi = "tuba3";
                kirjeldus = "\nSa leiad end mahajäätud ballisaalist. Laealused on täis ämblikuvõrke \nning saalipõrandal vedeleb katkine lühter.\n" + "Toa nurgast kostub tasane urin. Ole valmis võitluseks!";
                esemed = new ArrayList<>(asList(new RündeEse("oda", 2)));
                break;
            //case 4: veel tube...
            //case 5: ..


        }return new Ruum(nimi, kirjeldus, Koletis.suvalineKoll(), esemed);
    }

    //Esemete map sõneks, et oleks loetav
    public String esemeListSõneks(List<Ese> esemed){
        String tulemus = esemed.toString();
        return tulemus.replace("[", "").replace("]", "");
    }

    public String getNimi() {
        return nimi;
    }

    public String getRuumi_kirjeldus() {
        return ruumi_kirjeldus;
    }

    public Koletis getKoletis() {
        return koletis;
    }

    public List<Ese> getEsemed() {
        return esemed;
    }

    public void setKoletis(Koletis koletis) {
        this.koletis = koletis;
    }

    public String toString(){
        return "\n" +ruumi_kirjeldus +"\n"+ "Sa näed ruumis asju, mida võib hiljem vaja minna: " +
                esemeListSõneks(esemed);
    }
}
