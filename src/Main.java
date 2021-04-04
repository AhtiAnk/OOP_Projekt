import java.io.File;
import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws Exception {

        //Algus tuba
        List<Ese> algruumiesemed = new ArrayList<>(asList(new RündeEse("võti", 10), new HPEse("potion", 10)));
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
                    mängija.võtaEse(käsk[1]);
                    System.out.println(mängija.getAsukoht());
                    break;

                //Kasutab mängja eset
                case "Kasuta":
                    mängija.kasuta(käsk[1]);
                    break;

                //Prindib välja mängja asjad ja andmed
                case "Info":
                    System.out.println(mängija);
                    break;

                //Mine teise ruumi
                case "Mine":

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
