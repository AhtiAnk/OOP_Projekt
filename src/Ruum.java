import java.util.List;

public class Ruum {
    private String nimi;
    private String ruumi_kirjeldus;
    private boolean sisaldab_koletist;
    private List<Ese> esemed;

    public Ruum(String nimi, String ruumi_kirjeldus, boolean sisaldab_koletist, List<Ese> esemed) {
        this.nimi = nimi;
        this.ruumi_kirjeldus = ruumi_kirjeldus;
        this.sisaldab_koletist = sisaldab_koletist;
        this.esemed = esemed;
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
