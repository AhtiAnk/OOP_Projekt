import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Testimine {
    public static void main(String[] args) throws Exception {

// Algruumi loomise test
        File algus_tuba = new File("Algus tuba.txt");
        StringBuilder kirjeldus = new StringBuilder();
        try (Scanner sc = new Scanner(algus_tuba, "UTF-8")){  // loeb failist ja lisab kirjelduse muutujasse
            while (sc.hasNextLine()){
                String rida = sc.nextLine();
                kirjeldus.append(rida + " ");
            }
        }
        List<Ese> Algruumiesemed = new ArrayList<>(asList(new Ese("Võti"), new Ese ("Taskulamp"))); // Esemete list
        Ruum algus = new Ruum("Algus", kirjeldus.toString(), false, Algruumiesemed);
        System.out.println(algus);

        //Eseme eemaldamine test
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
}
