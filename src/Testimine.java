import java.io.File;
import java.util.*;

import static java.util.Arrays.asList;

public class Testimine {
    public static void main(String[] args) throws Exception {


// Algruumi loomise test
/*        File algus_tuba = new File("Algus tuba.txt");
        StringBuilder kirjeldus = new StringBuilder();
        try (Scanner sc = new Scanner(algus_tuba, "UTF-8")){  // loeb failist ja lisab kirjelduse muutujasse
            while (sc.hasNextLine()){
                String rida = sc.nextLine();
                kirjeldus.append(rida + " ");
            }
        }
        //HashMap<String, Ese> algruumiesemed = new HashMap<>();
        List<Ese> algruumiesemed = new ArrayList<>(asList(new Ese("Võti"), new Ese ("Taskulamp"))); // Esemete list
        Ruum algus = new Ruum("Algus", kirjeldus.toString(), false, algruumiesemed);
        System.out.println(algus);

        List<Ese> mängjaAsjad = new ArrayList<>();
        Mängija mängija = new Mängija(mängjaAsjad,10,20,100);

        //Eseme eemaldamine test
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Sisesta käsk: ");
            String[] käsk = sc.nextLine().split(" ");
            if (käsk[0].equals("Võta")) {  //Võtab ruumist eseme
                Ese uus = new Ese(käsk[1]);
                algus.getEsemed().remove(uus);
                mängija.getAsjad().add(uus);
                System.out.println(mängija.getAsjad());
                System.out.println(algus);
            }

            else if (käsk[0].equals("Mine")){  //Mine teise ruumi

            }

            else if (käsk[0].equals("Ründa")){

            }

            else if (käsk[0].equals("Stop"))  //Peata mäng
                break;
            else
                System.out.println("Vale käsk");
        }
*/
    }
}
