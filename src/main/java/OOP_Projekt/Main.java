package OOP_Projekt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        output.setEditable(false);

        input.setOnAction(e -> {
            String inputText = input.getText();
            käivitamine(inputText);
            input.clear();

        });

        VBox juur = new VBox(15, output, input);
        juur.setPrefSize(650, 500);
        juur.setPadding(new Insets(15));
        uusMäng();
        return juur;
    }

    private void menüü() {
        käsud.forEach((nimi, käsk) ->  {
            output.appendText("\n\n" + nimi + " : " + käsk.getKirjeldus());
        });
        output.appendText("\nSisesta käsk: ...");
    }

    private void uusMäng(){
        output.appendText("Tere tulemast mängima teksti-baasil seiklusmängu! Kui soovid alustada\nuut mängu kirjuta \"uus\", kui laadida salvestatud mängu, kirjuta \"lae\".");
        uusKäsk();
    }
    private void uusKäsk(){
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
    private void käivitamine(String line) {

        String[] sisend = line.split(" ");
        if (!käsud.containsKey(sisend[0])) {
            output.appendText("\nKäsku ei leitud!");
            return;
        }
        käsud.get(sisend[0]).käivita();
}
    private void uusAlgus() {
        output.clear();
        output.appendText(algus.toString());
        mängija.setAsukoht(algus);
        menüü();
    }
    private void failistAlgus() {
        try {
            mängija = laeMäng();
        } catch (IOException e) {
            e.printStackTrace();
            output.appendText("\n\nEt laadida salvestatud mängu, pead kõigepealt mängu salvestama!");
            //menüü();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            output.appendText("\n\nEt laadida salvestatud mängu, pead kõigepealt mängu salvestama!");
            //menüü();
        }
        output.appendText(mängija.getAsukoht().toString());
        //menüü();

    }
    private static void salvestaMäng(Mängija mängija) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("salvestus.txt"))){
            oos.writeObject(mängija);
        }
    }

    private static Mängija laeMäng() throws IOException, ClassNotFoundException {
        Mängija mängija;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("salvestus.txt"))){
            mängija = (Mängija) ois.readObject();
        }
        return mängija;
    }
    private void võtaEse() {
        String[] käsk = input.getText().split(" ");
        mängija.võtaEse(käsk[1]);
        output.appendText(mängija.getAsukoht().toString());
    }
    private void kasutaEset() {
        String[] käsk = input.getText().split(" ");
        //mängija.kasuta(käsk[1]);
        output.appendText(mängija.kasuta(käsk[1]));
    }
    private void ründa() {
        if (mängija.getAsukoht().getKoletis() != null) //kontroll kas koletis on olemas
            lahing(mängija, mängija.getAsukoht().getKoletis(), mängija.getAsukoht());
        else
            output.appendText("\nSiin ei ole ühtegi koletist");

        if (!mängija.onElus()) {
            //input.setEditable(false);
            input.setVisible(false);
        }
    }
    private void info() {
        output.appendText(mängija.toString());
    }
    private void liigu() {
        Ruum sihtPunkt = Ruum.uusSuvalineRuum();
        mängija.setAsukoht(sihtPunkt);
        output.appendText(sihtPunkt.toString());
    }
    private void salvesta() {
        try {
            salvestaMäng(mängija);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void lahing(Mängija mängija, Koletis koletis, Ruum ruum) {
        tutvustus(koletis);
        if (mängija.onElus() && koletis.onElus()) {

            output.appendText("\nSinu HP on: " + mängija.getHP());
            output.appendText("\nKoletise HP on: " + koletis.getHP());
            output.appendText("\n\n\n");
            uusKäsk();
            //if (input.equals("ründa"))
            koletis.rünnakuKahju(mängija);
            if (koletis.onElus()) {
                mängija.rünnakuKahju(koletis);
                }
            output.appendText("\nSinu HP peale rünnakut on: " + mängija.getHP());
            output.appendText("\nKoletise HP peale rünnakut on: " + koletis.getHP());
            output.appendText("\n\nKui tahad oma rünnakut tugevdada, vaheta relva (nt kasuta oda).\nKui tahad end ravida, kasuta potionit (nt kasuta potion)." +
                    "\nVastasel juhul ründa uuesti.");
            }
        if (!mängija.onElus()) {
            output.appendText("\nMäng läbi! Oled surnud.");
        }else if (!koletis.onElus()) {
            output.appendText("\nVõitlus läbi! Sa võitsid.");
            mängija.getAsukoht().setKoletis(null);
        }
    }

    private void tutvustus(Koletis koletis) {
        output.appendText("\n\n\n");
        output.appendText("\nSee on ju " + koletis.getNimi() + "!");
        output.appendText(koletis.toString());
        output.appendText("\nOle ettevaatlik!");
    }





    @Override
    public void start(Stage peaLava) throws IOException, ClassNotFoundException {

        peaLava.setScene(new Scene(looAken()));
        peaLava.show();
    }

    public static void main(String[] args) {
        launch();
    }
    }
