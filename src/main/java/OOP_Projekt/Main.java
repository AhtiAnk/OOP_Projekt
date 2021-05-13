package OOP_Projekt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static java.util.Arrays.asList;
import static javafx.application.Platform.exit;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main extends Application {
    private TextArea output = new TextArea();
    private TextField input = new TextField();
    private Map<String, Käsk> käsud = new HashMap<>();


    //Algus tuba
    List<Ese> algruumiesemed = new ArrayList<>(asList(new Ese("võti"), new HPEse("potion", 10)));


    Ruum algus = new Ruum("Algus","Sa ärkad üles hämaras ruumis.\n Uurides oma ümbrut näed sa ust.", null, algruumiesemed);

    //Mängija
    List<Ese> mängjaAsjad = new ArrayList<>();
    Mängija mängija = new Mängija(mängjaAsjad,10,20,100);

    private Parent looAken() {

        output.setPrefHeight(500);
        output.setFont(Font.font(15));
        input.setFont(Font.font(15));
        //output.setStyle("-fx-text-inner-color: white;");
        output.setStyle("-fx-control-inner-background: black;");
        input.setStyle("-fx-control-inner-background: black;");
        output.setEditable(false);
        output.setFocusTraversable(false); //võtab fookuse output aknalt ära

        input.setOnAction(e -> {  //kui kasutaja vajutab enterit, siis käivitab input realt saadud käsu ning kustutab selle
            String inputText = input.getText();
            käivitamine(inputText);
            input.clear();

        });

        VBox juur = new VBox(15, output, input);
        juur.setPrefSize(750, 500);
        juur.setPadding(new Insets(15));
        juur.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        uusMäng();
        return juur;
    }

    private void menüü() { //suur menüü mängu alguses
        käsud.forEach((nimi, käsk) ->  {
            output.appendText("\n\n" + nimi + " : " + käsk.getKirjeldus());
        });
        output.appendText("\n\nSisesta käsk: ...");
    }
    private void menüüVäike() { //väike menüü peale uute ruumi saabumist
        käsud.forEach((nimi, käsk) ->  {
            output.appendText(nimi + " | ");
        });
    }

    private void uusMäng(){
        output.appendText("Tere tulemast mängima teksti-baasil seiklusmängu! Kui soovid alustada\nuut mängu kirjuta \"uus\", kui laadida salvestatud mängu, kirjuta \"lae\".");
        uusKäsk();
    }
    private void uusKäsk(){ //käskude map
        käsud.put("välju", new Käsk("välju", "väljub mängust", Platform::exit));
        käsud.put("lae", new Käsk("lae", "laeb varasemalt salvestatud mängu", this::failistAlgus));
        käsud.put("uus", new Käsk("uus", "alustab uut mängu", this::uusAlgus));
        käsud.put("võta", new Käsk("võta", "võtab valitud eseme üles, NB. lisa ka eseme nimi! (nt. võta oda)", this::võtaEse));
        käsud.put("kasuta", new Käsk("kasuta", "kasutab valitud eset, NB. lisa ka eseme nimi! (nt. kasuta oda)", this::kasutaEset));
        käsud.put("ründa", new Käsk("ründa", "ründab koletist", this::ründa));
        käsud.put("liigu", new Käsk("liigu", "liigub järgmisesse ruumi", this::liigu));
        käsud.put("info", new Käsk("info", "kuvab mängija info", this::info));
        käsud.put("salvesta", new Käsk("salvesta", "salvestab mängu", this::salvesta));
    }
    private void käivitamine(String line) { //input realt saadud käskude käivitamine

        String[] sisend = line.split(" "); //juhul kui kasutaja sisestas mitteeksisteeriva käsu
        if (!käsud.containsKey(sisend[0])) {
            output.appendText("\n\nKäsku ei leitud!");
            return;
        }
        käsud.get(sisend[0]).käivita();
}
    private void uusAlgus() { //mängu alustamine täiesti algusest otsast peale
        output.clear();
        output.appendText(algus.toString());
        mängija.setAsukoht(algus);
        menüü();
    }
    private void failistAlgus() { //mängu alustamine varasemalt salvestatud failist
        try {
            mängija = laeMäng();
        } catch (IOException e) {
            e.printStackTrace();
            output.appendText("\n\nEt laadida salvestatud mängu, pead kõigepealt mängu salvestama!");
            input.clear();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            output.appendText("\n\nEt laadida salvestatud mängu, pead kõigepealt mängu salvestama!");
            input.clear();
        }
        output.appendText(mängija.getAsukoht().toString()); //varasemalt salvestatud mängus mängija viimane asukoht
        menüü();

    }
    private static void salvestaMäng(Mängija mängija) throws IOException { //käiva mängu salvestamine faili
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("salvestus.txt"))){
            oos.writeObject(mängija);
        }
    }

    private static Mängija laeMäng() throws IOException, ClassNotFoundException { //vana mängu laadimine
        Mängija mängija;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("salvestus.txt"))){
            mängija = (Mängija) ois.readObject();
        }
        return mängija;
    }
    private void võtaEse() { //ruumis oleva eseme üles võtmine
        String[] käsk = input.getText().split(" ");
        if (käsk.length == 1) { //juhul kui kasutaja kirjutab lihtsalt "võta"
            output.appendText("\n\nKirjuta mida sa võtta tahad (nt. võta oda)");
        }
        output.appendText(mängija.võtaEse(käsk[1]));
        output.appendText("\n" + mängija.getAsukoht().toString()); //väljastab ruumi seisukorra peale eseme üles korjamist
    }
    private void kasutaEset() { //mängija üles korjatud eseme kasutamine
        String[] käsk = input.getText().split(" ");
        if (käsk.length == 1) { //juhul kui mängija kirjutab ainult "kasuta"
            output.appendText("\n\nKirjuta mida sa kasutada tahad (nt kasuta oda)");
        }
        output.appendText(mängija.kasuta(käsk[1]));
    }
    private void ründa() { //mängija ründab koletist
        if (mängija.getAsukoht().getKoletis() != null) //kontroll kas koletis on olemas
            lahing(mängija, mängija.getAsukoht().getKoletis(), mängija.getAsukoht());
        else
            output.appendText("\n\nSiin ei ole ühtegi koletist");

        if (!mängija.onElus()) {  //kui mängija on surnud, siis ei lase uusi käske kirjutada
            //input.setEditable(false);
            input.setVisible(false);
        }
    }
    private void info() { //väljastab mängija info
        output.appendText("\n" + mängija.toString());
    }
    private void liigu() { //liigub järgmisesse ruumi
        Ruum sihtPunkt = Ruum.uusSuvalineRuum();
        mängija.setAsukoht(sihtPunkt);
        output.appendText(sihtPunkt.toString()); //väljastab ruumi kirjelduse
        output.appendText("\n\n");
        menüüVäike();
    }
    private void salvesta() { //salvestab poolelioleva mängu
        try {
            salvestaMäng(mängija);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void lahing(Mängija mängija, Koletis koletis, Ruum ruum) { //viib läbi lahingut mängija ja koletise vahel
        if (koletis.getHP() == koletis.getMaxHP()){ //väljastab koletise tutvustuse, kui mäng algab
            tutvustus(koletis);
            output.appendText("\nSinu HP on: " + mängija.getHP());
            output.appendText("\nKoletise HP on: " + koletis.getHP());}
        if (mängija.onElus() && koletis.onElus()) { //kui mängija ja koletis on elus saab rünnata

            output.appendText("\n\n");
            koletis.rünnakuKahju(mängija);
            if (koletis.onElus()) {
                mängija.rünnakuKahju(koletis);
                }
            output.appendText("\nSinu HP peale rünnakut on: " + mängija.getHP());
            output.appendText("\nKoletise HP peale rünnakut on: " + koletis.getHP());
            output.appendText("\n\nKui tahad oma rünnakut tugevdada, vaheta relva (nt kasuta oda).\nKui tahad end ravida, kasuta potionit (nt kasuta potion)." +
                    "\nEt näha oma esemeid, kirjuta \"info\"."
                     + "\nVastasel juhul ründa uuesti.");
            }
        if (!mängija.onElus()) { //kui mängija sureb
            output.appendText("\n\nMäng läbi! Oled surnud.");
        }else if (!koletis.onElus()) { //kui koletis sureb
            output.appendText("\n\nVõitlus läbi! Sa võitsid. Liigu edasi.");
            mängija.getAsukoht().setKoletis(null);
        }
    }

    private void tutvustus(Koletis koletis) { //koletise tutvustus
        output.appendText("\n\n\n");
        output.appendText("\nSee on ju " + koletis.getNimi() + "!");
        output.appendText(koletis.toString());
        output.appendText("\nOle ettevaatlik!");
    }





    @Override
    public void start(Stage peaLava) throws IOException, ClassNotFoundException {

        peaLava.setScene(new Scene(looAken()));
        peaLava.setTitle("Seiklus ja whatnot");
        peaLava.show();
    }

    public static void main(String[] args) {
        launch();
    }
    }
