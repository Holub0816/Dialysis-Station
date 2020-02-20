/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadializa.klasyBazodanowe;


public class Pacjent {

    long PESEL;
    String imie;
    String nazwisko;
    String numerTelefonu;
    String numerTelefonu2;
    String dniDializy;
    String zmiana;
    String stacjaDializ;
    String rodzajDostepu;
    String miejsceWklucia;
    

    public Pacjent() {
    }


 
    public Pacjent(long PESEL, String imie, String nazwisko, String numerTelefonu, String numerTelefonu2, String dniDializy, String zmiana, String stacjaDializ, String rodzajDostepu, String miejsceWklucia) {
        this.PESEL = PESEL;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        this.numerTelefonu2 = numerTelefonu2;
        this.dniDializy = dniDializy;
        this.zmiana = zmiana;
        this.stacjaDializ = stacjaDializ;
        this.rodzajDostepu = rodzajDostepu;
        this.miejsceWklucia = miejsceWklucia;
    }

    public Pacjent(long PESEL, String imie, String nazwisko, String numerTelefonu, String numerTelefonu2, String dniDializy, String zmiana) {
        this.PESEL = PESEL;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        this.numerTelefonu2 = numerTelefonu2;
        this.dniDializy = dniDializy;
        this.zmiana = zmiana;
    }

    public Pacjent(long PESEL, String imie, String nazwisko) {
        this.PESEL = PESEL;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Pacjent(long PESEL, String imie, String nazwisko, String rodzajDostepu, String miejsceWklucia) {
        this.PESEL = PESEL;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rodzajDostepu = rodzajDostepu;
        this.miejsceWklucia = miejsceWklucia;
    }
   public long getPESEL() {
        return PESEL;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getRodzajDostepu() {
        return rodzajDostepu;
    }

    public String getMiejsceWklucia() {
        return miejsceWklucia;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public String getNumerTelefonu2() {
        return numerTelefonu2;
    }

    public String getDniDializy() {
        return dniDializy;
    }

    public String getZmiana() {
        return zmiana;
    }

    public String getStacjaDializ() {
        return stacjaDializ;
    }


  
}
