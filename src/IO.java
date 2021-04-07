import java.util.Scanner;

public class IO {
    public static void lahing(Mängija mängija, Koletis koletis, Ruum ruum) {
        tutvustus(koletis);
        System.out.println("--------------------------------");
        while (mängija.onElus() && koletis.onElus()) {
            System.out.println("Sinu HP on: " + mängija.getHP());
            System.out.println("Koletise HP on: " + koletis.getHP());
            System.out.println("-----------------------------------");
            System.out.println("Ründa (ründa) / ravi ennast (potion) / kasuta relva (relv)");
            Scanner sc = new Scanner(System.in);
            String sisend = sc.nextLine();
            if (sisend.equals("ründa")) {
                koletis.rünnakuKahju(mängija);
                if (koletis.onElus()) {
                    mängija.rünnakuKahju(koletis);
                }
            }
            else if (sisend.equals("potion")) {
                //System.out.println(mängija);
                //System.out.println("Vali potion ning sisesta selle nimi.");
                //String sisend1 = sc.nextLine();
                mängija.kasuta(sisend);
            }
            else if (sisend.equals("relv")) {
                System.out.println(mängija);
                System.out.println("Vali relv ning sisesta selle nimi.");
                String sisend2 = sc.nextLine();
                mängija.kasuta(sisend2);
            }
        }
        if (!mängija.onElus()) {
            System.out.println("Mäng läbi! Oled surnud.");
        }else if (!koletis.onElus()) {
            System.out.println("Võitlus läbi! Sa võitsid.");
            mängija.getAsukoht().setKoletis(null);
        }
    }

    public static void tutvustus(Koletis koletis) {
        System.out.println("\n\n\n");
        System.out.println("See on ju " + koletis.getNimi() + "!");
        System.out.println(koletis);
        System.out.println("Ole ettevaatlik!");


    }
}
