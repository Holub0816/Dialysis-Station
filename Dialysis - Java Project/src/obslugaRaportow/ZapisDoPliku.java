/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obslugaRaportow;

import aplikacjadializa.klasyBazodanowe.Dializa;
import aplikacjadializa.klasyBazodanowe.Pacjent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import obslugaRaportow.JasperReportOperations;

public class ZapisDoPliku {

    public ZapisDoPliku(Pacjent pacjent, ArrayList<Double> textFieldsDouble, String nurseDes, String doctorDes, String czasZakonczenia, String stanWklucia) {
        this.pacjent = pacjent;
        this.weigth = textFieldsDouble.get(0);
        this.bloodVolume = textFieldsDouble.get(1);
        this.objetoscSubstytucji = textFieldsDouble.get(2);
        this.nurseDes = nurseDes;
        this.doctorDes = doctorDes;
        this.czasZakonczenia = czasZakonczenia;
        this.stanWklucia = stanWklucia;
    }
    
    public ZapisDoPliku(Pacjent pacjent, Dializa dializa, String opisPrzed, String stanWklucia) {
        this.pacjent = pacjent;
        this.dializa = dializa;
        this.opisPrzed = opisPrzed;
        this.stanWklucia = stanWklucia;
    }
    
    public ZapisDoPliku(Pacjent pacjent, Dializa dializa, String opisPrzed) {
        this.pacjent = pacjent;
        this.dializa = dializa;
        this.opisPrzed = opisPrzed;
        
    }
    private static Pacjent pacjent;
    private static Dializa dializa;
    private static String opisPrzed;
    private static String stanWklucia;
    private static double objetoscSubstytucji;
    private static ArrayList<String> godzina = new ArrayList<>();
    private static ArrayList<Double> rr = new ArrayList<>();
    private static ArrayList<Double> uf = new ArrayList<>();
    private static double bloodVolume;
    private static ArrayList<Double> pulse = new ArrayList<>();
    private static double weigth;
    private static String nurseDes;
    private static String doctorDes;
    private static JasperReportOperations jro = new JasperReportOperations();
    private static String fileName;
    private static String templateName;
    private static String czasZakonczenia;
    
    public static String getPathToPrintStart() {
        return "C:/Users/Maciek/Desktop/Dialysis/Dializy/" + pacjent.getPESEL() + "_" + dializa.getDataDializy().toString() + "start.pdf";
    }
    
    public static String getPathToPrintEnd() {
        return "C:/Users/Maciek/Desktop/Dialysis/Dializy/" + pacjent.getPESEL() + "_" + Date.valueOf(LocalDate.now()) + "end.pdf";
    }
    
    public static void print(boolean printGeneratedReport) {
        fileName = getPathToPrintStart();
        templateName = "FirstPage.jasper";
        jro.makePDF(mapsfillStart(), fileName, printGeneratedReport, templateName);
    }
    
    public static void save() {
        print(false);
    }
    
    public static void saveAndPrint() {
        print(true);
    }
    
    public static void zakonczDialize(boolean printGeneratedReport) {
        fileName = getPathToPrintEnd();
        templateName = "SecondPage.jasper";
        jro.makePDF(mapsfillStop(), fileName, printGeneratedReport, templateName);
        
    }
    
    public static List<Map<String, ?>> mapsfillStart() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, ?>> maps = new ArrayList<Map<String, ?>>();
        
        map.put("name", pacjent.getImie());
        map.put("surname", pacjent.getNazwisko());
        map.put("pesel", pacjent.getPESEL());
        map.put("waga", dializa.getWagaPacjenta());
        map.put("OcenaLekarzaPrzed", opisPrzed);
        map.put("rodzajDializy", dializa.getRodzajDializy());
        map.put("potas", dializa.getK());
        map.put("sod", dializa.getNa());
        map.put("wapn", dializa.getCa());
        map.put("glukoza", dializa.getGlukoza());
        map.put("fe", dializa.getFe());
        map.put("epo", dializa.getEpo());
        map.put("nr_stanowiska", dializa.getNumerStanowiska());
        map.put("nr_aparatu", dializa.getNumerAparatu());
        map.put("nr_dializy", dializa.getNumerDializy());
        map.put("pompa", dializa.getHeparynaPompa());
        map.put("bolus", dializa.getHeparynaBolus());
        map.put("heparyna_stopHH", dializa.getHeparynaStopHH());
        map.put("heparyna_stopMM", dializa.getHeparynaStopMM());
        map.put("nr_koncentratu", dializa.getNumerKoncentratu());
        map.put("ultrafiltracja", dializa.getUltrafiltracja());
        map.put("rodzajDializatora", dializa.getRodzajDializatora());
        map.put("rodzajDializy", dializa.getRodzajDializy());
        map.put("clexane", dializa.getClexaneDawka());
        map.put("czasHH", dializa.getCzasHH());
        map.put("czasMM", dializa.getCzasMM());
        map.put("konduktywnosc", dializa.getKonduktywnosc());
        map.put("temperatura", dializa.getTemperatura());
        map.put("przeplyw", dializa.getPrzeplyw());
        maps.add(map);
        return maps;
    }
    
    public static List<Map<String, ?>> mapsfillStop() {
        
        List<Map<String, ?>> maps = new ArrayList<Map<String, ?>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stanWklucia", stanWklucia);
        map.put("miejsceWklucia", pacjent.getMiejsceWklucia());
        map.put("rodzajDostepu", pacjent.getRodzajDostepu());
        map.put("name", pacjent.getImie());
        map.put("surname", pacjent.getNazwisko());
        map.put("nurseDescription", nurseDes);
        map.put("doctorDescription", doctorDes);
        map.put("bloodVolume", bloodVolume);
        map.put("weigthAfter", weigth);
        map.put("subVolume", objetoscSubstytucji);
        
        map.put("endTime", czasZakonczenia);
        map.put("pesel", pacjent.getPESEL());
        
        maps.add(map);
        return maps;
    }
}
