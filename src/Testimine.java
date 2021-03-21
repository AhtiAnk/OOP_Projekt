import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        List<Ese> Algruumiesemed = new ArrayList<>(Arrays.asList(new Ese("Võti"), new Ese ("Taskulamp"))); // Esemete list
        Ruum algus = new Ruum("Algus", kirjeldus.toString(), false, Algruumiesemed);
        System.out.println(algus);

        //Eseme eemaldamine test
        List<Ese> alg_ese = algus.getEsemed();
        Ese uus = new Ese("Võti"); //kasutaja inputina
        alg_ese.remove(uus);
        algus.setEsemed(alg_ese);
        System.out.println(algus);

    }
}
