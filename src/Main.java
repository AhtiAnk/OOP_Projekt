import java.io.File;
import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws Exception {

        //Algus tuba
        List<Ese> algruumiesemed = new ArrayList<>(asList(new Ese("võti"), new HPEse("potion", 10)));
        Ruum algus = new Ruum("Algus", loeFailist("Algus tuba.txt"), false, algruumiesemed);

        //Teised toad

        //Mängja
        List<Ese> mängjaAsjad = new ArrayList<>();
        Mängija mängija = new Mängija(mängjaAsjad,10,20,100);

        //Mängu jooksutamine
        System.out.println(algus);
        mängija.setAsukoht(algus);
        Scanner sc = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Sisesta käsk: ");
            String[] käsk = sc.nextLine().split(" ");

            //Võtab ruumist eseme ja lisab mängja esemetesse
            switch (käsk[0]) {
                case "Võta":
                    for (int i = 0; i < mängija.getAsukoht().getEsemed().size(); i++) { //Läbib ruumi esemete listi
                        if (käsk[1].equals(mängija.getAsukoht().getEsemed().get(i).getNimi())) { //Kontrollib kas ese on olemas
                            mängija.getAsjad().add(mängija.getAsukoht().getEsemed().get(i));
                            mängija.getAsukoht().getEsemed().remove(mängija.getAsukoht().getEsemed().get(i));
                            break;
                        }
                    }
                    System.out.println(algus);
                    break;

                //Kasutab mängja eset
                case "Kasuta":
                    for (int i = 0; i < mängija.getAsjad().size(); i++) {
                        if (käsk[1].equals(mängija.getAsjad().get(i).getNimi())) {

                            if (mängija.getAsjad().get(i) instanceof HPEse) { //Kontrollib kas kasutatav ese kuulub klassi
                                mängija.setHP(mängija.getHP() + ((HPEse) mängija.getAsjad().get(i)).getTaastaHP()); //Taastab HP-d
                                if (mängija.getHP() > mängija.getMaxHP()) //Kontrollib, et HP ei oleks üle max väärtuse
                                    mängija.setHP(mängija.getMaxHP());
                                mängija.getAsjad().remove(i);
                                System.out.println("Sinu HP taastati");
                                break;
                            } else if (mängija.getAsjad().get(i) instanceof RündeEse) { //Suurendab mängja rünnakut kui on ründeese
                                mängija.setRelv((RündeEse) mängija.getAsjad().get(i));
                                System.out.println("Su rünnak on nüüd tugevam");
                                break;
                            } else {
                                System.out.println("Ei saa kasutada");
                                break;
                            }
                        }
                    }
                    break;

                //Prindib välja mängja asjad ja andmed
                case "Info":
                    System.out.println(mängija);
                    break;

                case "Mine":   //Mine teise ruumi

                    break;
                //Peata mäng
                case "Stop":
                    break label;

                default:
                    System.out.println("Vale käsk");
                    break;
            }
        }
    }

    public static String loeFailist(String failinimi) throws Exception{
        File tuba = new File(failinimi);
        StringBuilder kirjeldus = new StringBuilder();
        try (Scanner sc = new Scanner(tuba, "UTF-8")){  // loeb failist ja lisab kirjelduse muutujasse
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                kirjeldus.append(rida + " ");
            }
        }
        return kirjeldus.toString();
    }
}
