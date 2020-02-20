/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadializa;

import aplikacjadializa.klasyBazodanowe.Dializa;
import aplikacjadializa.klasyBazodanowe.Pacjent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;


public class DataBaseQueries {

    public ArrayList<ResultSet> podpowiedzDaneDializy(long PESEL) {
        ArrayList<ResultSet> resultSets = new ArrayList<>();
        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String queryDaneDializy = " select x.data,x.sucha_waga,x.heparyna_bolus,x.heparyna_pompa,"
                    + "x.heparyna_stop,x.CLEXANE_dawka,x.ca,x.na,x.k,x.epo,x.glukoza,"
                    + "x.fe,x.numer_koncentratu,x.numer_stanowiska,x.numer_aparatu,q.nazwa\n"
                    + "from pacjenci p  join parametry_dializy x on p.PESEL = x.PESEL \n"
                    + "join rodzaj_dializatora q on x.rodzaj_dializatora = q.id where p.pesel like ? and data like \n"
                    + "(select max(x.data) \n"
                    + "from pacjenci p  join parametry_dializy x on p.PESEL = x.PESEL \n"
                    + "join rodzaj_dializatora q on x.rodzaj_dializatora = q.id where p.pesel like ?)";
            PreparedStatement preparedStatement2 = conn.prepareStatement(queryDaneDializy);
            preparedStatement2.setLong(1, PESEL);
            preparedStatement2.setLong(2, PESEL);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            resultSets.add(resultSet2);

            String queryPacjent = "select pesel from pacjenci p where p.pesel like ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(queryPacjent);
            preparedStatement.setLong(1, PESEL);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSets.add(resultSet);

            String queryNumerDializy = "select count(id) as 'numer_dializy' from parametry_dializy where pesel like ? group by pesel ";
            PreparedStatement preparedStatement3 = conn.prepareStatement(queryNumerDializy);
            preparedStatement3.setLong(1, PESEL);
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            resultSets.add(resultSet3);

            return resultSets;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


    public void dodajDialize(long PESEL, ObservableList<String> rodzajeDializatorow, String rodzajDializatora,
            Date date, ObservableList<String> rodzajeDializ, String rodzajDializy, ArrayList<Double> spinnersDouble,
            ArrayList<Double> textFieldsDouble, ArrayList<Integer> textFieldsInt) {

        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String queryDaneDializy = "insert into parametry_dializy (PESEL,sucha_waga,K,"
                    + "Ca,Na,glukoza,epo,fe,numer_koncentratu,rodzaj_dializatora,data,numer_stanowiska,"
                    + "numer_aparatu,heparyna_bolus,heparyna_pompa,heparyna_stop,clexane_dawka,rodzaj_dializy,czas,uf)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement2 = conn.prepareStatement(queryDaneDializy);

            preparedStatement2.setLong(1, PESEL);
            preparedStatement2.setDouble(2, spinnersDouble.get(0));
            preparedStatement2.setDouble(3, spinnersDouble.get(1));
            preparedStatement2.setDouble(4, spinnersDouble.get(2));
            preparedStatement2.setDouble(5, spinnersDouble.get(3));
            preparedStatement2.setDouble(6, spinnersDouble.get(4));
            preparedStatement2.setDouble(7, spinnersDouble.get(5));
            preparedStatement2.setDouble(8, spinnersDouble.get(6));
            preparedStatement2.setDouble(9, spinnersDouble.get(7));

            for (String string : rodzajeDializatorow) {
                if (rodzajDializatora.equals(string)) {

                    preparedStatement2.setInt(10, rodzajeDializatorow.indexOf(string) + 1);
                    break;
                }
            }

            preparedStatement2.setDate(11, date);
            preparedStatement2.setInt(12, textFieldsInt.get(0));
            preparedStatement2.setInt(13, textFieldsInt.get(1));
            preparedStatement2.setDouble(14, spinnersDouble.get(8));
            preparedStatement2.setDouble(15, spinnersDouble.get(9));
            double heparynaStop = textFieldsInt.get(4) * 60 + textFieldsInt.get(5);
            preparedStatement2.setDouble(16, heparynaStop);
            preparedStatement2.setDouble(17, spinnersDouble.get(10));
            for (String string : rodzajeDializ) {
                if (rodzajDializy.equals(string)) {

                    preparedStatement2.setInt(18, rodzajeDializ.indexOf(string) + 1);

                    break;
                }
            }
            double czas = textFieldsInt.get(2) * 60 + textFieldsInt.get(3);
            preparedStatement2.setDouble(19, czas);
            preparedStatement2.setDouble(20, textFieldsDouble.get(0));
            preparedStatement2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ResultSet> zakonczDialize(long PESEL, ArrayList<Double> textFieldsDouble, Time czasZakonczeniaZ, String doctorDescription, String nurseDescription) {
        ArrayList<ResultSet> resultSets = new ArrayList<>();

        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String queryDanePrzebieguDializy
                    = "select godzina,rr,puls,uf from przebieg_dializy where pesel like ? and data like ?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(queryDanePrzebieguDializy);
            preparedStatement1.setLong(1, PESEL);
            preparedStatement1.setDate(2, Date.valueOf(LocalDate.now().plusDays(1)));
            ResultSet rs = preparedStatement1.executeQuery();
            resultSets.add(rs);

            String queryDanePacjenta = "select imie, nazwisko, f.nazwa as 'rodzaj_dostepu',w.nazwa as 'miejsce_wklucia'\n"
                    + " from pacjenci p join miejsce_wklucia w on p.miejsce_wklucia = w.id join rodzaj_dostepu "
                    + "f on p.rodzaj_dostepu = f.id where p.pesel like ?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(queryDanePacjenta);
            preparedStatement2.setLong(1, PESEL);
            ResultSet resultSet = preparedStatement2.executeQuery();
            resultSets.add(resultSet);

            String queryZakonczenie = "insert into zakonczenie_dializy (PESEL,waga,objetosc_krwi,"
                    + "objetosc_substytucji,czas_zakonczenia,data,opis_lekarz,opis_pielegniarka) values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(queryZakonczenie);
            preparedStatement.setLong(1, PESEL);
            preparedStatement.setDouble(2, textFieldsDouble.get(0));
            preparedStatement.setDouble(3, textFieldsDouble.get(1));
            preparedStatement.setDouble(4, textFieldsDouble.get(2));
            preparedStatement.setTime(5, czasZakonczeniaZ);
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(7, doctorDescription);
            preparedStatement.setString(8, nurseDescription);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSets;
    }

    public boolean checkIfPESELexistsInDatabase(long PESEL) {
        int counter = 0;
        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String firstQuery = "select imie from pacjenci where pesel like ?";
            PreparedStatement ps = conn.prepareStatement(firstQuery);
            ps.setLong(1, PESEL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                counter++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (counter == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void dodajPacjenta(long PESEL, String imie, String nazwisko, long telefon1, long telefon2,
            String dniDializyString, ObservableList<String> dniTygodnia, String zmianaString,
            ObservableList<String> rodzajeZmian, String rodzajDostepuString, ObservableList<String> rodzajeDostepow,
            String miejsceWluciaString, ObservableList<String> miejscaWklucia) {

        try {
            String query = "INSERT INTO dializa_baza.pacjenci(PESEL,Imie,Nazwisko,Telefon_pacjenta,"
                    + "Telefon_dodatkowy,Dni_dializy,Zmiana,ID_stacji,Rodzaj_dostepu,Miejsce_wklucia)"
                    + " values(?,?,?,?,?,?,?,?,?,?)";

            Connection conn = dbUtil.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setLong(1, PESEL);
            preparedStatement.setString(2, imie);
            preparedStatement.setString(3, nazwisko);
            preparedStatement.setLong(4, telefon1);
            preparedStatement.setLong(5, telefon2);
            for (String string : dniTygodnia) {
                if (dniDializyString.equals(string)) {
                    preparedStatement.setInt(6, dniTygodnia.indexOf(string) + 1);
                    break;
                }
            }

            for (String string : rodzajeZmian) {
                if (zmianaString.equals(string)) {
                    preparedStatement.setInt(7, rodzajeZmian.indexOf(string) + 1);
                    break;
                }
            }
            preparedStatement.setInt(8, 1);
            for (String string : rodzajeDostepow) {
                if (rodzajDostepuString.equals(string)) {
                    preparedStatement.setInt(9, rodzajeDostepow.indexOf(string) + 1);
                    break;
                }
            }
            for (String string : miejscaWklucia) {
                if (miejsceWluciaString.equals(string)) {
                    preparedStatement.setInt(10, miejscaWklucia.indexOf(string) + 1);
                    break;
                }
            }

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dodajOpisDializatora(String opis) {
        try {
            String nowyOpisDializatoraQuery = "insert into rodzaj_dializatora (nazwa) values(?)";
            Connection conn = dbUtil.getInstance().getConnection();
            PreparedStatement preparedStatement3 = conn.prepareStatement(nowyOpisDializatoraQuery);
            preparedStatement3.setString(1, opis);
            preparedStatement3.executeUpdate();
            preparedStatement3.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pacjent danePacjenta(long PESEL, ObservableList<String> miejscaWklucia, ObservableList<String> rodzajeDostepow) {
        Pacjent pacjent = null;
        try {
            String queryDanePacjenta = "select imie,nazwisko,rodzaj_dostepu,miejsce_wklucia from pacjenci where pesel like ?";
            Connection conn = dbUtil.getInstance().getConnection();
            PreparedStatement preparedStatement1 = conn.prepareStatement(queryDanePacjenta);
            preparedStatement1.setLong(1, PESEL);
            ResultSet rs = preparedStatement1.executeQuery();

            String miejsce = null;
            String dostep = null;
            while (rs.next()) {
                miejsce = miejscaWklucia.get(rs.getInt("miejsce_wklucia") - 1);
                dostep = rodzajeDostepow.get(rs.getInt("rodzaj_dostepu") - 1);
                pacjent = new Pacjent(PESEL, rs.getString("imie"), rs.getString("nazwisko"), dostep, miejsce);
            }
            preparedStatement1.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacjent;
    }

    public boolean isDescriptionInDatabase(String opis) {
        try {
            String query = "select * from opisy where opis like ?";
            Connection conn = dbUtil.getInstance().getConnection();
            PreparedStatement preparedStatement3 = conn.prepareStatement(query);
            preparedStatement3.setString(1, opis);

            ResultSet rs3 = preparedStatement3.executeQuery();
            int counter = 0;
            while (rs3.next()) {
                counter++;
            }
            if (counter == 0) {

                String insertQuery = "Insert into opisy (opis) values(?)";
                PreparedStatement preparedStatement4 = conn.prepareStatement(insertQuery);
                preparedStatement4.setString(1, opis);
                preparedStatement4.executeUpdate();
                preparedStatement3.close();
                preparedStatement4.close();
                return false;
            } else {
                preparedStatement3.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    public boolean isNameInDatabase(String imie) {
        try {
            String query = "select imie from imiona where imie like ?";
            Connection conn = dbUtil.getInstance().getConnection();
            PreparedStatement preparedStatement3 = conn.prepareStatement(query);
            preparedStatement3.setString(1, imie);
            ResultSet rs3 = preparedStatement3.executeQuery();
            int counter = 0;
            while (rs3.next()) {
                counter++;
            }
            if (counter == 0)//nie ma takiego imienia w bazie
            {
                String queryToInsert = "Insert into imiona(imie) values (?)";
                PreparedStatement preparedStatement = conn.prepareStatement(queryToInsert);
                preparedStatement.setString(1, imie);
                preparedStatement.executeQuery();
                preparedStatement.close();
                preparedStatement3.close();
                conn.close();
                return false;
            } else {
                preparedStatement3.close();
                conn.close();
                return true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }

    }

    public Pacjent wyszukajPacjenta(long PESEL) {
        Pacjent pacjent = null;
        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String query = "select p.PESEL,Imie,Nazwisko,Telefon_pacjenta,Telefon_dodatkowy,\n"
                    + "                    dni,z.zmiana,r.nazwa as Rodzaj_dostepu,m.nazwa as Miejsce_wklucia from pacjenci p join dni_dializy d on p.Dni_dializy = d.id join zmiany z \n"
                    + "                  on p.Zmiana = z.id join rodzaj_dostepu r on p.rodzaj_dostepu = r.id join miejsce_wklucia m on p.miejsce_wklucia = m.id where p.PESEL like ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setLong(1, PESEL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int resultCounter = 0;
            while (resultSet.next()) {
                resultCounter++;
                String imie = resultSet.getString("Imie");
                String nazwisko = resultSet.getString("Nazwisko");
                String numerTelefonu = String.valueOf(resultSet.getInt("Telefon_pacjenta"));
                String numerTelefonu2 = String.valueOf(resultSet.getInt("Telefon_dodatkowy"));
                String dniDializy = resultSet.getString("dni");
                String zmiana = resultSet.getString("zmiana");
                String rodzaj = resultSet.getString("Rodzaj_dostepu");
                String miejsce = resultSet.getString("Miejsce_wklucia");

                String stacjaDializ = "Wrocław";
                pacjent = new Pacjent(PESEL, imie, nazwisko, numerTelefonu,
                        numerTelefonu2, dniDializy, zmiana, stacjaDializ, rodzaj, miejsce);

            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pacjent;
    }

    public ArrayList<Dializa> wyszukajDializy(long PESEL,
            RadioButton searchingDaneDializyDataCheckBox, RadioButton searchingDaneOstatniejDializyCheckBox,
            RadioButton searchingDaneDializCheckBox,
            DatePicker datePicker) {
        ArrayList<Dializa> wyszukaneDializy = new ArrayList<>();
        try {

            Connection conn = dbUtil.getInstance().getConnection();
            String queryDaneDializy = null;
            Date searchingDate = null;
            if (searchingDaneDializyDataCheckBox.isSelected()) {
                searchingDate = Date.valueOf(datePicker.getValue().plusDays(1));

                queryDaneDializy = "select x.data,x.sucha_waga,x.heparyna_bolus,"
                        + "x.heparyna_pompa,x.heparyna_stop,x.clexane_dawka,x.ca,x.na,x.k,x.glukoza,"
                        + "x.epo,x.fe,x.numer_koncentratu,x.numer_stanowiska,x.numer_aparatu,x.czas,x.uf,q.nazwa,d.rodzaj\n"
                        + "from pacjenci p  join parametry_dializy x on p.PESEL = x.PESEL \n"
                        + "join rodzaj_dializatora q on x.rodzaj_dializatora = q.id join rodzaje_dializ d on x.rodzaj_dializy = d.id where p.PESEL like ? and x.data like ?";
            } else if (searchingDaneOstatniejDializyCheckBox.isSelected()) {
                queryDaneDializy = " select x.data,x.sucha_waga,x.heparyna_bolus,x.heparyna_pompa,"
                        + "x.heparyna_stop,x.clexane_dawka,x.ca,x.na,x.k,x.glukoza,x.epo,x.fe,x.numer_koncentratu,"
                        + "x.numer_stanowiska,x.numer_aparatu,x.czas,x.uf,q.nazwa,d.rodzaj\n"
                        + "from pacjenci p  join parametry_dializy x on p.PESEL = x.PESEL \n"
                        + "join rodzaj_dializatora q on x.rodzaj_dializatora = q.id join rodzaje_dializ d on x.rodzaj_dializy = d.id where p.pesel like ? and data like \n"
                        + "(select max(x.data) \n"
                        + "from pacjenci p  join parametry_dializy x on p.PESEL = x.PESEL \n"
                        + "join rodzaj_dializatora q on x.rodzaj_dializatora = q.id  where p.pesel like ?)";
            } else if (searchingDaneDializCheckBox.isSelected()) {
                queryDaneDializy = "select x.data,x.sucha_waga,x.heparyna_bolus,x.heparyna_pompa,"
                        + "x.heparyna_stop,x.clexane_dawka,x.ca,x.na,x.k,x.glukoza,x.epo,"
                        + "x.fe,x.numer_koncentratu,x.numer_stanowiska,x.numer_aparatu,x.czas,x.uf,q.nazwa,d.rodzaj\n"
                        + "from pacjenci p  join parametry_dializy x on p.PESEL = x.PESEL \n"
                        + "join rodzaj_dializatora q on x.rodzaj_dializatora = q.id join rodzaje_dializ d on x.rodzaj_dializy = d.id where p.PESEL like ? order by x.data desc";
            }

            PreparedStatement preparedStatement2 = conn.prepareStatement(queryDaneDializy);
            preparedStatement2.setLong(1, PESEL);
            if (searchingDaneDializyDataCheckBox.isSelected()) {
                preparedStatement2.setDate(2, searchingDate);
            } else if (searchingDaneOstatniejDializyCheckBox.isSelected()) {
                preparedStatement2.setLong(2, PESEL);
            }
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet2.next()) {

                Date data = resultSet2.getDate("data");

                double suchaWaga = resultSet2.getDouble("sucha_waga");

                double ca = resultSet2.getDouble("ca");
                double na = resultSet2.getDouble("na");
                double k = resultSet2.getDouble("k");
                double glukoza = resultSet2.getDouble("glukoza");
                double epo = resultSet2.getDouble("epo");
                double fe = resultSet2.getDouble("fe");
                String nazwaDializatora = resultSet2.getString("nazwa");
                double numerKoncentratu = resultSet2.getDouble("numer_koncentratu");
                int numerStanowiska = resultSet2.getInt("numer_stanowiska");
                int numerAparatu = resultSet2.getInt("numer_aparatu");
                double heparynaBolus = resultSet2.getDouble("heparyna_bolus");
                double heparynaPompa = resultSet2.getDouble("heparyna_pompa");
                double clexaneDawka = resultSet2.getDouble("clexane_dawka");
                String rodzajDializy = resultSet2.getString("rodzaj");
                double heparynaStop = resultSet2.getDouble("heparyna_stop");
                double czas = resultSet2.getDouble("czas");
                double ultrafiltracjaDB = resultSet2.getDouble("uf");
                Dializa dializa = new Dializa(data, suchaWaga, heparynaBolus, heparynaPompa,
                        heparynaStop, clexaneDawka,
                        ca, na, k, glukoza, epo, fe, nazwaDializatora, numerKoncentratu,
                        numerStanowiska, numerAparatu, rodzajDializy, czas, ultrafiltracjaDB);
                wyszukaneDializy.add(dializa);
            }

            preparedStatement2.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wyszukaneDializy;
    }

    public ObservableList<String> pobierzRodzajeDializatorow() {

        ObservableList<String> rodzajeDializatorow = FXCollections.observableArrayList();
        try {//Pobieranie rodzajow dializatorow z bazy
            Connection conn = dbUtil.getInstance().getConnection();
            String queryRozdajeDializatorow = "select nazwa from rodzaj_dializatora";
            PreparedStatement preparedStatement = conn.prepareStatement(queryRozdajeDializatorow);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String nazwa = rs.getString("nazwa");
                rodzajeDializatorow.add(nazwa);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rodzajeDializatorow;
    }

    public ArrayList<String> pobierzOpisy() {
        ArrayList<String> listaOpisow = new ArrayList<>();
        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String pobranieOpisow = "select * from opisy";
            PreparedStatement preparedStatement120 = conn.prepareStatement(pobranieOpisow);
            ResultSet rs120 = preparedStatement120.executeQuery();
            while (rs120.next()) {

                String opis = rs120.getString("Opis");
                listaOpisow.add(opis);
            }
            rs120.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOpisow;
    }

    public ObservableList<String> pobierzMiejscaWklucia() {
        // Pobieranie miejsc wklucia z bazy
        ObservableList<String> miejscaWklucia = FXCollections.observableArrayList();
        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String queryMiejscaWklucia = "select nazwa from miejsce_wklucia";
            PreparedStatement preparedStatement2 = conn.prepareStatement(queryMiejscaWklucia);
            ResultSet rs2 = preparedStatement2.executeQuery();
            while (rs2.next()) {
                String nazwa = rs2.getString("nazwa");
                miejscaWklucia.add(nazwa);
            }
        } catch (SQLException e) {
        }
        return miejscaWklucia;
    }

    public ObservableList<String> pobierzRodzajeDostepow() {
        ObservableList<String> rodzajeDostepow = FXCollections.observableArrayList();
        try {
            //Pobieranie rodzajow dostepu

            Connection conn = dbUtil.getInstance().getConnection();
            String queryRodzajeDostepow = "select nazwa from rodzaj_dostepu";
            PreparedStatement preparedStatement3 = conn.prepareStatement(queryRodzajeDostepow);
            ResultSet rs3 = preparedStatement3.executeQuery();
            while (rs3.next()) {
                String nazwa = rs3.getString("nazwa");
                rodzajeDostepow.add(nazwa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rodzajeDostepow;
    }

    public ObservableList<String> pobierzDniTygodnia() {
        ObservableList<String> dniTygodnia = FXCollections.observableArrayList();

        try {
            Connection conn = dbUtil.getInstance().getConnection();
//Pobieranie dni dializ
            String queryDniDializ = "select dni from dni_dializy";
            PreparedStatement preparedStatement4 = conn.prepareStatement(queryDniDializ);
            ResultSet rs4 = preparedStatement4.executeQuery();
            while (rs4.next()) {
                String dni = rs4.getString("dni");
                dniTygodnia.add(dni);
            }
        } catch (SQLException e) {
        }
        return dniTygodnia;
    }

    public ObservableList<String> pobierzRodzajeZmian() {
        ObservableList<String> rodzajeZmian = FXCollections.observableArrayList();

        try {
            Connection conn = dbUtil.getInstance().getConnection();
            String queryZmiany = "select zmiana from zmiany";
            PreparedStatement preparedStatement5 = conn.prepareStatement(queryZmiany);
            ResultSet rs5 = preparedStatement5.executeQuery();
            while (rs5.next()) {
                String zmiana = rs5.getString("zmiana");
                rodzajeZmian.add(zmiana);
            }
        } catch (SQLException e) {
        }
        return rodzajeZmian;
    }

    public ArrayList<Long> pobierzListePeseli() {
        ArrayList<Long> listaPeseli = new ArrayList<>();
        try {

            Connection conn = dbUtil.getInstance().getConnection();
            String queryPesele = "select pesel from pacjenci";
            PreparedStatement preparedStatement6 = conn.prepareStatement(queryPesele);
            ResultSet rs6 = preparedStatement6.executeQuery();
            while (rs6.next()) {
                Long pesel = rs6.getLong("pesel");
                listaPeseli.add(pesel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPeseli;
    }

    public ArrayList<String> pobierzListeImion() {
        ArrayList<String> imionaZbazy = new ArrayList<>();
        try {

            Connection conn = dbUtil.getInstance().getConnection();
            String queryImiona = "select * from imiona";
            PreparedStatement preparedStatement7 = conn.prepareStatement(queryImiona);
            ResultSet rs7 = preparedStatement7.executeQuery();
            while (rs7.next()) {

                imionaZbazy.add(rs7.getString("imie"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imionaZbazy;
    }

    public ObservableList<String> pobierzRodzajeDializ() {
        ObservableList<String> rodzajeDializ = FXCollections.observableArrayList();
        try {

            Connection conn = dbUtil.getInstance().getConnection();
            String queryRdzajeDializy = "SELECT * FROM dializa_baza.rodzaje_dializ";
            PreparedStatement preparedStatement8 = conn.prepareStatement(queryRdzajeDializy);
            ResultSet rs8 = preparedStatement8.executeQuery();
            while (rs8.next()) {
                rodzajeDializ.add(rs8.getString("rodzaj"));
            }
            preparedStatement8.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rodzajeDializ;
    }

}
