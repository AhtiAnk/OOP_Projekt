import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws Exception {
        //Algus Ruum
        List<Ese> Algruumiesemed = new ArrayList<>(asList(new Ese("Võti"), new Ese ("Taskulamp"))); // Esemete list
        Ruum algus = new Ruum("Algus", loeFailist("Algus tuba.txt"), false, Algruumiesemed);
        //Teised toad

        //Mängu jooksutamine
        System.out.println(algus);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Sisesta käsk: ");
            String[] käsk = sc.nextLine().split(" ");
            if (käsk[0].equals("Võta")) {  //Võtab ruumist eseme
                Ese eemalda = new Ese(käsk[1]);
                algus.getEsemed().remove(eemalda);
                //Vaja ka lisada mängja asjade hulka
                System.out.println(algus);
            }

            else if (käsk[0].equals("Mine")){  //Mine teise ruumi

            }
            else if (käsk[0].equals("Stop"))  //Peata mäng
                break;
            else
                System.out.println("Vale käsk");
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
