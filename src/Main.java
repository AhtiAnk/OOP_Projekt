import java.io.*;
import java.util.*;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Algus tuba
        List<Ese> algruumiesemed = new ArrayList<>(asList(new Ese("võti"), new HPEse("potion", 10)));
        Ruum algus = new Ruum("Algus","Uurides oma ümbrut näed sa ust.", null, algruumiesemed);

        //Mängija
        List<Ese> mängjaAsjad = new ArrayList<>();
        Mängija mängija = new Mängija(mängjaAsjad,10,20,100);

        //Mängu jooksutamine
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Kui soovid alustada uut mängu kirjuta \"uus\", kui laadida salvestatud mängu, kirjuta \"lae\"");
            String käsk = sc.nextLine();
            if (käsk.equals("uus")) {
                System.out.println("Sa ärkad üles hämaras ruumis.");
                System.out.println(algus);
                mängija.setAsukoht(algus);
                break;
            } else if (käsk.equals("lae")) {
                mängija = laeMäng();
                System.out.println(mängija.getAsukoht());
                break;
            } else
                System.out.println("Vale käsk");
        }
        label:
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Valikud: võta <ese> / kasuta <ese> / ründa / info / liigu / stopp / salvesta / lae");
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

                //Ründab koletist
                case "ründa":
                    if (mängija.getAsukoht().getKoletis() != null) //kontroll kas koletis on olemas
                        IO.lahing(mängija, mängija.getAsukoht().getKoletis(), mängija.getAsukoht());
                    else
                        System.out.println("Siin ei ole ühtegi koletist");

                    if (!mängija.onElus())
                        break label;
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

                //Salvesta mäng
                case "salvesta":
                    salvestaMäng(mängija);
                    break;

                //Lae mäng
                case "lae":
                    mängija = laeMäng();
                    System.out.println(mängija.getAsukoht());
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

    private static void salvestaMäng(Mängija mängija) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("salvestus.txt"))){
            oos.writeObject(mängija);
        }
    }

    private static Mängija laeMäng() throws IOException, ClassNotFoundException{
        Mängija mängija;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("salvestus.txt"))){
            mängija = (Mängija) ois.readObject();
        }
        return mängija;
    }
}
