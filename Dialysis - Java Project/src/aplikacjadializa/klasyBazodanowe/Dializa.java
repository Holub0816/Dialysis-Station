/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadializa.klasyBazodanowe;

import java.util.Date;
import java.util.ArrayList;


public class Dializa {

    int numerDializy;
    Date dataDializy;
    double wagaPacjenta;
    double heparynaBolus;
    double heparynaPompa;
    int heparynaStopHH;
    int heparynaStopMM;
    double clexaneDawka;

    double Ca;
    double K;
    double Na;
    double glukoza;
    double epo;
    double fe;
    String rodzajDializatora;
    double numerKoncentratu;
    int numerStanowiska;
    int numerAparatu;
    String rodzajDializy;
    double ultrafiltracja;
    int czasMM;
    int czasHH;
    double konduktywnosc;
    double przeplyw;
    double temperatura;
    double heparynaStop;
    double czas;

    public Dializa() {
    }

    public Dializa(Date dataDializy, String rodzajDializatora, String rodzajDializy,
            ArrayList<Double> spinnersDouble, ArrayList<Double> textFieldsDouble,
            ArrayList<Integer> textFieldsInt) {
        this.dataDializy = dataDializy;
        this.rodzajDializatora = rodzajDializatora;
        this.rodzajDializy = rodzajDializy;
        this.wagaPacjenta = spinnersDouble.get(0);
        this.K = spinnersDouble.get(1);
        this.Ca = spinnersDouble.get(2);
        this.Na = spinnersDouble.get(3);
        this.glukoza = spinnersDouble.get(4);
        this.epo = spinnersDouble.get(5);
        this.fe = spinnersDouble.get(6);
        this.numerKoncentratu = spinnersDouble.get(7);
        this.heparynaBolus = spinnersDouble.get(8);
        this.heparynaPompa = spinnersDouble.get(9);
        this.clexaneDawka = spinnersDouble.get(10);

        this.ultrafiltracja = textFieldsDouble.get(0);
        this.konduktywnosc = textFieldsDouble.get(1);
        this.przeplyw = textFieldsDouble.get(2);
        this.temperatura = textFieldsDouble.get(3);
        this.numerStanowiska = textFieldsInt.get(0);
        this.numerAparatu = textFieldsInt.get(1);
        this.czasHH = textFieldsInt.get(2);
        this.czasMM = textFieldsInt.get(3);
        this.heparynaStopHH = textFieldsInt.get(4);
        this.heparynaStopMM = textFieldsInt.get(5);
        this.heparynaStop = textFieldsInt.get(4) * 60 + textFieldsInt.get(5);
        this.czas = textFieldsInt.get(2) * 60 + textFieldsInt.get(3);
    }

    public Dializa(int numerDializy, Date dataDializy, double wagaPacjenta, double heparynaBolus, double heparynaPompa, int heparynaStopHH, int heparynaStopMM, double clexaneDawka,
            double Ca, double K, double Na, double glukoza, double epo, double fe, String rodzajDializatora, double numerKoncentratu, int numerStanowiska, int numerAparatu, String rodzajDializy, double ultrafiltracja, int czasHH, int czasMM, double konduktywnosc, double przeplyw, double temperatura) {
        this.numerDializy = numerDializy;
        this.dataDializy = dataDializy;
        this.wagaPacjenta = wagaPacjenta;
        this.heparynaBolus = heparynaBolus;
        this.heparynaPompa = heparynaPompa;
        this.heparynaStopHH = heparynaStopHH;
        this.heparynaStopMM = heparynaStopMM;
        this.clexaneDawka = clexaneDawka;
        this.Ca = Ca;
        this.K = K;
        this.Na = Na;
        this.glukoza = glukoza;
        this.epo = epo;
        this.fe = fe;
        this.rodzajDializatora = rodzajDializatora;
        this.numerKoncentratu = numerKoncentratu;
        this.numerStanowiska = numerStanowiska;

        this.numerAparatu = numerAparatu;
        this.rodzajDializy = rodzajDializy;
        this.ultrafiltracja = ultrafiltracja;
        this.czasMM = czasMM;
        this.czasHH = czasHH;
        this.konduktywnosc = konduktywnosc;
        this.przeplyw = przeplyw;
        this.temperatura = temperatura;
    }

    public Dializa(Date dataDializy, double wagaPacjenta, double heparynaBolus,
            double heparynaPompa, double heparynaStop, double clexaneDawka, double Ca,
            double K, double Na, double glukoza, double epo, double fe, String rodzajDializatora,
            double numerKoncentratu, int numerStanowiska, int numerAparatu, String rodzajDializy,
            double czas, double ultrafiltracja) {

        this.dataDializy = dataDializy;
        this.wagaPacjenta = wagaPacjenta;
        this.heparynaBolus = heparynaBolus;
        this.heparynaPompa = heparynaPompa;
        this.heparynaStop = heparynaStop;
        this.clexaneDawka = clexaneDawka;
        this.Ca = Ca;
        this.K = K;
        this.Na = Na;
        this.glukoza = glukoza;
        this.epo = epo;
        this.fe = fe;
        this.rodzajDializatora = rodzajDializatora;
        this.numerKoncentratu = numerKoncentratu;
        this.numerStanowiska = numerStanowiska;
        this.numerAparatu = numerAparatu;
        this.rodzajDializy = rodzajDializy;
        this.ultrafiltracja = ultrafiltracja;
        this.czas = czas;

    }

    public double getHeparynaStop() {
        return heparynaStop;
    }

    public void setHeparynaStop(double heparynaStop) {
        this.heparynaStop = heparynaStop;
    }

    public double getCzas() {
        return czas;
    }

    public void setCzas(double czas) {
        this.czas = czas;
    }

    public double getGlukoza() {
        return glukoza;
    }

    public void setGlukoza(double glukoza) {
        this.glukoza = glukoza;
    }

    public double getEpo() {
        return epo;
    }

    public void setEpo(double epo) {
        this.epo = epo;
    }

    public double getFe() {
        return fe;
    }

    public void setFe(double fe) {
        this.fe = fe;
    }

    public double getWagaPacjenta() {
        return wagaPacjenta;
    }

    public void setWagaPacjenta(double wagaPacjenta) {
        this.wagaPacjenta = wagaPacjenta;
    }

    public double getCa() {
        return Ca;
    }

    public void setCa(double Ca) {
        this.Ca = Ca;
    }

    public double getK() {
        return K;
    }

    public void setK(double K) {
        this.K = K;
    }

    public double getNa() {
        return Na;
    }

    public void setNa(double Na) {
        this.Na = Na;
    }

    public String getRodzajDializatora() {
        return rodzajDializatora;
    }

    public void setRodzajDializatora(String rodzajDializatora) {
        this.rodzajDializatora = rodzajDializatora;
    }

    public Date getDataDializy() {
        return dataDializy;
    }

    public int getNumerStanowiska() {
        return numerStanowiska;
    }

    public int getNumerAparatu() {
        return numerAparatu;
    }

    public double getNumerKoncentratu() {
        return numerKoncentratu;
    }

    public double getHeparynaBolus() {
        return heparynaBolus;
    }

    public double getHeparynaPompa() {
        return heparynaPompa;
    }

    public double getClexaneDawka() {
        return clexaneDawka;
    }

    public String getRodzajDializy() {
        return rodzajDializy;
    }

    public double getUltrafiltracja() {
        return ultrafiltracja;
    }

    public int getHeparynaStopHH() {
        return heparynaStopHH;
    }

    public int getHeparynaStopMM() {
        return heparynaStopMM;
    }

    public int getCzasMM() {
        return czasMM;
    }

    public int getCzasHH() {
        return czasHH;
    }

    public int getNumerDializy() {
        return numerDializy;
    }

    public double getKonduktywnosc() {
        return konduktywnosc;
    }

    public double getPrzeplyw() {
        return przeplyw;
    }

    public double getTemperatura() {
        return temperatura;
    }

}
