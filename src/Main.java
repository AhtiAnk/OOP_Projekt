import java.io.File;
import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws Exception {

        //Algus tuba
        List<Ese> algruumiesemed = new ArrayList<>(asList(new Ese("võti"), new HPEse("potion", 10)));
        Ruum algus = new Ruum("Algus", loeFailist("Algus tuba.txt"), false, null, algruumiesemed);


        //Mängija
        List<Ese> mängjaAsjad = new ArrayList<>();
        Mängija mängija = new Mängija(mängjaAsjad,10,20,100);

        //Mängu jooksutamine
        System.out.println(algus);
        mängija.setAsukoht(algus);
        Scanner sc = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Valikud: võta <ese> / kasuta <ese> / ründa / info / liigu / stopp");
            System.out.println("Sisesta käsk: ");
            String[] käsk = sc.nextLine().split(" ");

            //Võtab ruumist eseme ja lisab mängja esemetesse
            switch (käsk[0]) {
                case "võta":
                    mängija.võtaEse(käsk[1]);
                    System.out.println(mängija.getAsukoht());
                    break;

                //Kasutab mängja eset
                case "kasuta":
                    mängija.kasuta(käsk[1]);
                    break;

                case "ründa":
                    IO.lahing(mängija, mängija.getAsukoht().getKoletis(), mängija.getAsukoht());
                    break;

                //Prindib välja mängja asjad ja andmed
                case "info":
                    System.out.println(mängija);
                    break;

                //Mine teise ruumi
                case "liigu":
                    Ruum sihtPunkt = Ruum.uusSuvalineRuum();
                    mängija.setAsukoht(sihtPunkt);
                    System.out.println(sihtPunkt);
                    break;

                //Peata mäng
                case "stopp":
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
