import java.util.*;

import static java.util.Arrays.asList;

public class Ruum {
    private String nimi;
    private String ruumi_kirjeldus;
    private boolean sisaldab_koletist;
    private Koletis koletis;
    private List<Ese> esemed;

    public Ruum(String nimi, String ruumi_kirjeldus, boolean sisaldab_koletist, Koletis koletis, List<Ese> esemed) {
        this.nimi = nimi;
        this.ruumi_kirjeldus = ruumi_kirjeldus;
        this.sisaldab_koletist = sisaldab_koletist;
        this.koletis = koletis;
        this.esemed = esemed;
    }

    public static Ruum uusSuvalineRuum() {
        String nimi = null;
        String kirjeldus = null;
        List<Ese> esemed = null;
        int suvaline = (int) (Math.random() * 3) + 1;
        switch (suvaline) {
            case 1:
                nimi = "tuba1";
                kirjeldus = "Sa astud ruumi, mis haiseb tugevalt kopituse järgi. Su saapad pritsivad iga sammuga vett üles.\n" + "Midagi krabistab su selja taga. Ole valmis võitluseks!";
                esemed = new ArrayList<>(asList(new RündeEse("mõõk", 4)));
                break;
            case 2:
                nimi = "tuba2";
                kirjeldus = "Sa kõnnid läbi pimeda koridori kuni jõuad hiiglasliku tammepuidust ukseni.\n" +  "Sa avad ukse ning kaks kollast silma hiilgavad sulle pimeduses vastu. Ole valmis võitluseks!";
                esemed = new ArrayList<>(asList(new HPEse("potion", 5)));
                break;
            case 3:
                nimi = "tuba3";
                kirjeldus = "Sa leiad end mahajäätud ballisaalist. Laealused on täis ämblikuvõrke ning saalipõrandal vedeleb katkine lühter.\n" + "Toa nurgast kostub tasane urin. Ole valmis võitluseks!";
                esemed = new ArrayList<>(asList(new RündeEse("oda", 2)));
                break;
            //case 4: veel tube...
            //case 5: ..


        }return new Ruum(nimi, kirjeldus, true, Koletis.suvalineKoll(), esemed);
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

    public boolean isSisaldab_koletist() {
        return sisaldab_koletist;
    }

    public Koletis getKoletis() {
        return koletis;
    }

    public List<Ese> getEsemed() {
        return esemed;
    }

    public void setRuumi_kirjeldus(String ruumi_kirjeldus) {
        this.ruumi_kirjeldus = ruumi_kirjeldus;
    }

    public void setSisaldab_koletist(boolean sisaldab_koletist) {
        this.sisaldab_koletist = sisaldab_koletist;
    }

    public String toString(){
        return ruumi_kirjeldus +"\n"+ "Sa näed ruumis asju, mida võib hiljem vaja minna: " +
                esemeListSõneks(esemed);
    }
}
