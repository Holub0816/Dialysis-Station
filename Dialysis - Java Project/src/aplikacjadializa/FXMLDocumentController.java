/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadializa;

import obslugaRaportow.ZapisDoPliku;
import aplikacjadializa.klasyBazodanowe.Dializa;
import aplikacjadializa.klasyBazodanowe.Pacjent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;


public class FXMLDocumentController implements Initializable {

    DataBaseQueries dbq = new DataBaseQueries();
    public ObservableList<Dializa> wyszukaneDializy = FXCollections.observableArrayList();
    public ObservableList<Pacjent> wyszukanyPacjent = FXCollections.observableArrayList();
    ObservableList<String> rodzajeDostepow = FXCollections.observableArrayList();
    ObservableList<String> rodzajeDializatorow = FXCollections.observableArrayList();

    ObservableList<String> miejscaWklucia = FXCollections.observableArrayList();
    ObservableList<String> dniTygodnia = FXCollections.observableArrayList();
    ObservableList<String> rodzajeZmian = FXCollections.observableArrayList();
    ObservableList<String> rodzajeDializ = FXCollections.observableArrayList();
    ArrayList<Spinner> dodajDializeSpinners = new ArrayList<>();
    ArrayList<TextField> dodajDializeTextFieldsDouble = new ArrayList<>();
    ArrayList<TextField> dodajDializeTextFieldsInt = new ArrayList<>();
    ArrayList<TextField> dodajDializeTextFields = new ArrayList<>();
    ArrayList<ChoiceBox> listaChoiceBoxes = new ArrayList<>();
    ArrayList<ObservableList<String>> listaObserwatorow = new ArrayList<>();
    ArrayList<TextField> dodajPacjentaPolaTekstowe = new ArrayList<>();
    ArrayList<ButtonType> listaPrzyciskow = new ArrayList<>();
    ArrayList<ButtonType> przyciskiZapisywanieDializy = new ArrayList<>();
    private ArrayList<Long> listaPeseli = new ArrayList<>();

    private ArrayList<String> listaOpisow = new ArrayList<>();
    private ArrayList<TextField> zakonczDializePolaTekstowe = new ArrayList<>();
    ArrayList<TextField> zakonczDializePolaTekstoweDouble = new ArrayList<>();
    ArrayList<TextField> zakonczDializePolaTekstoweInt = new ArrayList<>();
    private ArrayList<String> imonaZbazy = new ArrayList<>();
    @FXML
    private TextField objetoscSubstytucji;

    @FXML
    private TextField zakonczenieHH;

    @FXML
    private TextField zakonczenieMM;

    @FXML
    private TableView<Dializa> tabelaDializ;

    @FXML
    private TableColumn<Dializa, Date> dataDializyTableColumn;

    @FXML
    private TableColumn<Dializa, Double> wagaPacjentaTableColumn;

    @FXML
    private TableColumn<Dializa, Double> CaTableColumn;

    @FXML
    private TableColumn<Dializa, Double> KTableColumn;
    @FXML
    private TableColumn<Dializa, Double> NaTableColumn;
    @FXML
    private TableColumn<Dializa, Double> glukozaTableColumn;
    @FXML
    private TableColumn<Dializa, Double> epoTableColum;
    @FXML
    private TableColumn<Dializa, Double> feTableColum;
    @FXML
    private TableColumn<Dializa, String> rodzajDializatoraTableColum;
    @FXML
    private TableColumn<Dializa, Double> koncentratTableColumn;
    @FXML
    private TableColumn<Dializa, Double> ultrafiltracjaTableColumn;
    @FXML
    private TableColumn<Dializa, Double> czasTableColumn;
    @FXML
    private TableColumn<Dializa, String> rodzajTableColumn;
    @FXML
    private TableView<Pacjent> tabelaPacjenta;

    @FXML
    private TableColumn<Pacjent, String> imietableColum;
    @FXML
    private TableColumn<Pacjent, String> nazwiskoTableColum;
    @FXML
    private TableColumn<Pacjent, String> tel1tableColum;
    @FXML
    private TableColumn<Pacjent, String> tel2TableColum;
    @FXML
    private TableColumn<Pacjent, String> dniDializytableColum;
    @FXML
    private TableColumn<Pacjent, String> zmianaTableColum;
    @FXML
    private TableColumn<Pacjent, String> rodzajDosteputableColum;
    @FXML
    private TableColumn<Pacjent, String> miejsceWkluciaTableColum;
    @FXML
    private TableColumn<Dializa, Integer> numerStanowiskaTableColumn;

    @FXML
    private TableColumn<Dializa, Integer> numerAparatuTableColumn;

    @FXML
    private Pane paneInnyDializator;
    @FXML
    private TextField dodajDializeNumerStanowiska;

    @FXML
    private TextField dodajDializeNumerAparatu;
    @FXML
    private TextField ultrafiltracja;
    @FXML
    private TextField czasHH;

    @FXML
    private TextField czasMM;

    @FXML
    private TextField opisInnegoDializatora;
    @FXML
    private TextField wagaPo;
    @FXML
    private TextField heparynaStopHH;

    @FXML
    private TextField heparynaStopMM;

    @FXML
    private TextField objetoscKrwiPo;
    @FXML
    private TextField dodajDializePesel;

    @FXML
    private RadioButton dataDializyDzis;

    @FXML
    private RadioButton dataDializyInna;

    @FXML
    private DatePicker dodajDializeDatePicker;

    @FXML
    private Spinner<Double> suchaWaga;

    @FXML
    private TextField peselTXT;

    @FXML
    private TextField imieTXT;

    @FXML
    private TextField nazwiskoTXT;

    @FXML
    private TextField telefon1TXT;
    @FXML
    private TextField numerHemodializy;
    @FXML
    private TextField telefon2TXT;
    @FXML
    private Label label;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;

    @FXML
    private ChoiceBox<String> rodzajDializyChoiceBox;
    @FXML
    private ChoiceBox<String> dniDializyCHBox;

    @FXML
    private ChoiceBox<String> zmianaCHBox;

    @FXML
    private ChoiceBox<String> rodzajDostepuCHBox;

    @FXML
    private ChoiceBox<String> miejsceWkluciaCHBox;

    @FXML
    private TextField peselForSearching;

    @FXML
    private RadioButton searchingDaneDializCheckBox;

    @FXML
    private RadioButton searchingDaneOstatniejDializyCheckBox;

    @FXML
    private TextField stanCewnika;

    @FXML
    private TextField opisPrzed;
    @FXML
    private RadioButton searchingDaneDializyDataCheckBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Double> potas;

    @FXML
    private Spinner<Double> sod;

    @FXML
    private Spinner<Double> wapn;

    @FXML
    private Spinner<Double> glukoza;

    @FXML
    private Spinner<Double> epo;

    @FXML
    private Spinner<Double> fe;
    @FXML
    private Spinner<Double> numerKoncentratu;
    @FXML
    private Spinner<Double> bolusSpinner;

    @FXML
    private Spinner<Double> pompaSpinner;

    @FXML
    private Spinner<Double> dawkaSpinner;
    @FXML
    private ChoiceBox<String> dodajDializeRodzajDializatora;
    @FXML
    private TextField endPESEL;

    @FXML
    private TextField opisPoPielegniarka;

    @FXML
    private TextField opisPoLekarz;
    @FXML
    private TextField konduktywnosc;

    @FXML
    private TextField przeplyw;

    @FXML
    private TextField temperatura;

    @FXML
    void dodajPacjenta(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Potwierdź dodanie nowego pacjenta do bazy");
        alert.setContentText("Czy na pewno chcesz dodać nowego pacjenata?");

        alert.getButtonTypes().setAll(listaPrzyciskow);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == listaPrzyciskow.get(0)) {
            try {
                long PESEL = Long.parseLong(peselTXT.getText());

                String imie = imieTXT.getText();
                if (!dbq.isNameInDatabase(imie)) {
                    imonaZbazy.add(imie);
                }
                String nazwisko = nazwiskoTXT.getText();
                long telefon1 = 0;
                long telefon2 = 0;
                if (telefon1TXT.getText().isEmpty() || telefon1TXT.getText().trim().isEmpty()) {

                    telefon1 = 0;
                } else {
                    telefon1 = Long.parseLong(telefon1TXT.getText());
                }
                if (telefon2TXT.getText().isEmpty() || telefon2TXT.getText().trim().isEmpty()) {
                    telefon2 = Long.parseLong(telefon2TXT.getText());
                } else {
                    telefon2 = Long.parseLong(telefon2TXT.getText());
                }
                String dniDializyString = dniDializyCHBox.getValue();
                String zmianaString = zmianaCHBox.getValue();

                String rodzajDostepuString = rodzajDostepuCHBox.getValue();
                String miejsceWluciaString = miejsceWkluciaCHBox.getValue();

                if (peselTXT.getText().length() == 11 && imie.length() > 0 && nazwisko.length() > 0) {

                    if (!dbq.checkIfPESELexistsInDatabase(PESEL)) {

                        dbq.dodajPacjenta(PESEL, imie, nazwisko, telefon1, telefon2, dniDializyString,
                                dniTygodnia, zmianaString, rodzajeZmian, rodzajDostepuString, rodzajeDostepow,
                                miejsceWluciaString, miejscaWklucia);
                        Alert alert2 = new Alert(AlertType.INFORMATION);
                        alert2.setTitle("Information Dialog");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Poprawnie dodano pacjenta do bazy");
                        Optional<ButtonType> result2 = alert2.showAndWait();
                        if (result2.get() == ButtonType.OK) {
                            for (TextField textField : dodajPacjentaPolaTekstowe) {
                                textField.clear();
                            }
                        }

                    } else {
                        Alert alert2 = new Alert(AlertType.WARNING);
                        alert2.setHeaderText("Nie można dodać pacjenta do bazy");
                        alert2.setContentText("Pacjent o numerze PESEL " + peselTXT.getText() + " jest już w bazie");
                        alert2.showAndWait();
                    }
                } else {
                    String str1 = "OK", str3 = "OK";
                    if (peselTXT.getText().length() < 11) {
                        str1 = "krótszy niż 11 znaków";
                    }
                    if (peselTXT.getText().length() > 11) {
                        str1 = "dłuższy niż 11 znaków.";
                    }

                    if (nazwisko.length() <= 0) {
                        str3 = "BRAK";
                    }

                    String str2 = null;

                    Alert alert2 = new Alert(AlertType.ERROR);

                    alert2.setTitle(null);
                    alert2.setHeaderText("Błąd w dodawaniu nowego pacjenta");
                    alert2.setContentText("\n" + "PESEL: " + str1 + "\n" + "Imię: " + str2 + "\n" + "Nazwisko: " + str3);
                    alert2.showAndWait();

                }
            } catch (NumberFormatException e) {
                Alert alert2 = new Alert(AlertType.ERROR);

                alert2.setTitle(null);
                alert2.setHeaderText("Błąd w dodawaniu nowego pacjenta");

                alert2.setContentText("Podany numer PESEL zawiera niedozwolone znaki");
                alert2.showAndWait();
            }

        }
    }

    @FXML
    void szukajPacjenta(ActionEvent event
    ) {
        tabelaDializ.getItems().clear();
        tabelaPacjenta.getItems().clear();
        try {

            long PESEL = Long.parseLong(peselForSearching.getText());
            if (peselForSearching.getText().length() == 11) {

                Pacjent pacjent = dbq.wyszukajPacjenta(PESEL);
                wyszukanyPacjent.add(pacjent);

                if (!dbq.checkIfPESELexistsInDatabase(PESEL)) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Pacjent o numerze PESEL " + peselForSearching.getText()
                            + " nie jest zapisany w bazie.\nDodaj pacjenta do bazy");
                    alert.showAndWait();
                }

                ArrayList<Dializa> dializy = dbq.wyszukajDializy(PESEL, searchingDaneDializyDataCheckBox, searchingDaneOstatniejDializyCheckBox, searchingDaneDializCheckBox, datePicker);
                for (int i = 0; i < dializy.size(); i++) {
                    wyszukaneDializy.add(dializy.get(i));

                }

                dataDializyTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Date>("dataDializy"));
                wagaPacjentaTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("wagaPacjenta"));

                CaTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("Ca"));
                KTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("K"));
                NaTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("Na"));
                glukozaTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("glukoza"));
                epoTableColum.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("epo"));
                feTableColum.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("fe"));
                rodzajDializatoraTableColum.setCellValueFactory(new PropertyValueFactory<Dializa, String>("rodzajDializatora"));
                numerStanowiskaTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Integer>("numerStanowiska"));
                numerAparatuTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Integer>("numerAparatu"));
                rodzajTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, String>("rodzajDializy"));
                czasTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("czas"));
                ultrafiltracjaTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("ultrafiltracja"));
                koncentratTableColumn.setCellValueFactory(new PropertyValueFactory<Dializa, Double>("numerKoncentratu"));
                tabelaDializ.setItems(wyszukaneDializy);

                imietableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("imie"));
                nazwiskoTableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("nazwisko"));
                tel1tableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("numerTelefonu"));
                tel2TableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("numerTelefonu2"));
                dniDializytableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("dniDializy"));
                zmianaTableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("zmiana"));
                rodzajDosteputableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("rodzajDostepu"));
                miejsceWkluciaTableColum.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("miejsceWklucia"));

                tabelaPacjenta.setItems(wyszukanyPacjent);

            } else {
                String str1 = null;
                if (dodajDializePesel.getText().length() < 11) {
                    str1 = "krótszy niż 11 znaków";
                } else if (dodajDializePesel.getText().length() > 11) {
                    str1 = "dłuższy niż 11 znaków.";
                }

                Alert alert2 = new Alert(AlertType.ERROR);

                alert2.setTitle(null);
                alert2.setHeaderText("Błąd w dodawaniu nowego pacjenta");

                alert2.setContentText("\n" + "PESEL: " + str1);
                alert2.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert2 = new Alert(AlertType.ERROR);

            alert2.setTitle(null);
            alert2.setHeaderText("Błąd w wyszukiwaniu pacjenta");
            alert2.setContentText("Podany numer PESEL zawiera niedozwolone znaki");
            alert2.showAndWait();
        } catch (NullPointerException e) {
            Alert alert2 = new Alert(AlertType.ERROR);

            alert2.setTitle(null);
            alert2.setHeaderText("Błąd w wyszukiwaniu dializy");
            alert2.setContentText("Nie wybrano daty z kalendarza");
            alert2.showAndWait();
        }

    }

    @FXML
    void podpowiedzDaneDializy(ActionEvent event
    ) {
        numerHemodializy.clear();
        for (Spinner spinner : dodajDializeSpinners) {
            spinner.getEditor().clear();
        }
        for (TextField dodajDializeTextField : dodajDializeTextFields) {
            dodajDializeTextField.clear();
        }

        try {

            long PESEL = Long.parseLong(dodajDializePesel.getText());
            if (dodajDializePesel.getText().length() == 11) {
                ArrayList<ResultSet> resultSets = dbq.podpowiedzDaneDializy(PESEL);
                int resultsCounter = 0;
                int results2Counter = 0;
                int numerDializy = 1;
                while (resultSets.get(2).next()) {
                    numerDializy = resultSets.get(2).getInt("numer_dializy") + 1;
                }
                while (resultSets.get(0).next()) {
                    results2Counter++;
                    double suchaWagaDB = resultSets.get(0).getDouble("sucha_waga");

                    double caDB = resultSets.get(0).getDouble("ca");
                    double naDB = resultSets.get(0).getDouble("na");
                    double kDB = resultSets.get(0).getDouble("k");
                    double epoDB = resultSets.get(0).getDouble("epo");
                    double glukozaDB = resultSets.get(0).getDouble("glukoza");
                    double feDB = resultSets.get(0).getDouble("fe");
                    double heparynaBolus = resultSets.get(0).getDouble("heparyna_bolus");
                    double heparynaPompa = resultSets.get(0).getDouble("heparyna_pompa");
                    double clexaneDawka = resultSets.get(0).getDouble("clexane_dawka");

                    double numerKoncentratuDB = resultSets.get(0).getDouble("numer_koncentratu");
                    int numerStanowiska = resultSets.get(0).getInt("numer_stanowiska");
                    int numerAparatu = resultSets.get(0).getInt("numer_aparatu");
                    double heparynaStop = resultSets.get(0).getDouble("heparyna_stop");

                    int heparynaH = (int) (heparynaStop / 60);
                    int heparynaM = (int) heparynaStop - heparynaH * 60;
                    heparynaStopHH.appendText(String.valueOf(heparynaH));
                    heparynaStopMM.appendText(String.valueOf(heparynaM));
                    dodajDializeNumerStanowiska.appendText(String.valueOf(numerStanowiska));
                    dodajDializeNumerAparatu.appendText(String.valueOf(numerAparatu));

                    ArrayList<Double> listaParametrowZBazy = new ArrayList<>();
                    listaParametrowZBazy.add(suchaWagaDB);
                    listaParametrowZBazy.add(kDB);
                    listaParametrowZBazy.add(naDB);
                    listaParametrowZBazy.add(caDB);
                    listaParametrowZBazy.add(epoDB);
                    listaParametrowZBazy.add(glukozaDB);
                    listaParametrowZBazy.add(feDB);
                    listaParametrowZBazy.add(numerKoncentratuDB);
                    listaParametrowZBazy.add(heparynaBolus);
                    listaParametrowZBazy.add(heparynaPompa);
                    listaParametrowZBazy.add(clexaneDawka);
                    for (Spinner spinner : dodajDializeSpinners) {
                        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 5000, listaParametrowZBazy.get(dodajDializeSpinners.indexOf(spinner)), 0.01));
                    }

                }
                numerHemodializy.appendText(String.valueOf(numerDializy));
                while (resultSets.get(1).next()) {
                    resultsCounter++;

                }
                if (resultsCounter == 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Pacjent o numerze PESEL " + dodajDializePesel.getText()
                            + " nie jest zapisany w bazie.\nDodaj pacjenta do bazy a następnie wprowadź dane dializy.");
                    alert.showAndWait();
                } else if (results2Counter == 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Ten pacjent nie posiada żadnej dializy w bazie. Uzupełnij dane ręcznie.");
                    alert.showAndWait();
                }

            } else {
                String str1 = null;
                if (dodajDializePesel.getText().length() < 11) {
                    str1 = "krótszy niż 11 znaków";
                } else if (dodajDializePesel.getText().length() > 11) {
                    str1 = "dłuższy niż 11 znaków.";
                }

                Alert alert2 = new Alert(AlertType.ERROR);

                alert2.setTitle(null);
                alert2.setHeaderText("Błąd w szukaniu pacjenta");

                alert2.setContentText("\n" + "PESEL: " + str1);
                alert2.showAndWait();

            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NumberFormatException e) {
            Alert alert2 = new Alert(AlertType.ERROR);

            alert2.setTitle(null);
            alert2.setHeaderText("Błąd w szukaniu pacjenta");

            alert2.setContentText("Podany numer PESEL zawiera niedozwolone znaki");
            alert2.showAndWait();
        }

    }

    @FXML
    void dodajDialize(ActionEvent event
    ) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Potwierdź dodanie nowej dializy");
        alert.setContentText("Czy na pewno chcesz dodać tę dializę?");
        alert.getButtonTypes().setAll(listaPrzyciskow);
        Optional<ButtonType> result = alert.showAndWait();
        ArrayList<Double> spinnersDouble = new ArrayList<>();
        ArrayList<Double> textFieldsDouble = new ArrayList<>();
        ArrayList<Integer> textFieldsInt = new ArrayList<>();
        if (result.get() == listaPrzyciskow.get(0)) {
            long PESEL = 0;
            try {
                PESEL = Long.parseLong(dodajDializePesel.getText());
            } catch (NumberFormatException e) {
                System.out.println("Brak wpisanego numeru PESEL");
            }
            if (dodajDializePesel.getText().length() == 11) {

                for (Spinner spinner : dodajDializeSpinners) {
                    try {
                        if (spinner.getEditor().getText().isEmpty() || spinner.getEditor().getText().trim().isEmpty()) {
                            spinnersDouble.add(0.0);
                        } else {
                            spinnersDouble.add(Double.parseDouble(spinner.getEditor().getText().replace(",", ".")));

                        }
                    } catch (NumberFormatException e) {
                        Alert alertError = new Alert(AlertType.ERROR);
                        alertError.setTitle("Wprowadzono błędne dane");
                        alertError.setContentText("Wprowadzono znaki inne niż cyfry do pola " + spinner.getId());
                        alertError.showAndWait();
                    }
                }

                for (TextField textField : dodajDializeTextFieldsDouble) {
                    try {
                        if (textField.getText().isEmpty() || textField.getText().trim().isEmpty()) {
                            textFieldsDouble.add(0.0);
                        } else {
                            textFieldsDouble.add(Double.parseDouble(textField.getText().replace(",", ".")));
                        }
                    } catch (NumberFormatException e) {
                        Alert alertError = new Alert(AlertType.ERROR);
                        alertError.setTitle("Wprowadzono błędne dane");
                        alertError.setContentText("Wprowadzono znaki inne niż cyfry do pola " + textField.getId());
                        alertError.showAndWait();
                    }
                }

                for (TextField textField : dodajDializeTextFieldsInt) {
                    try {
                        if (textField.getText().isEmpty() || textField.getText().trim().isEmpty()) {
                            textFieldsInt.add(0);
                        } else {
                            textFieldsInt.add(Integer.parseInt(textField.getText()));
                        }
                    } catch (NumberFormatException e) {
                        Alert alertError = new Alert(AlertType.ERROR);
                        alertError.setTitle("Wprowadzono błędne dane");
                        alertError.setContentText("Wprowadzono znaki inne niż cyfry do pola " + textField.getId());
                        alertError.showAndWait();
                    }
                }

                String rodzajDializy = rodzajDializyChoiceBox.getValue();
                String rodzajDializatora = dodajDializeRodzajDializatora.getValue();

                Date dateForDatabase = Date.valueOf(LocalDate.now().plusDays(1));;
                Date date = Date.valueOf(LocalDate.now());

                if (dataDializyInna.isSelected()) {
                    dateForDatabase = Date.valueOf(dodajDializeDatePicker.getValue().plusDays(1));
                    date = Date.valueOf(dodajDializeDatePicker.getValue());
                }

                dbq.dodajDialize(PESEL, rodzajeDializatorow, rodzajDializatora, date, rodzajeDializ,
                        rodzajDializy, spinnersDouble, textFieldsDouble, textFieldsInt);

                String dodatkowyOpisDializatora = opisInnegoDializatora.getText();
                if (!dodatkowyOpisDializatora.isEmpty()) {
                    dbq.dodajOpisDializatora(dodatkowyOpisDializatora);
                }

                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Information Dialog");
                alert2.setHeaderText(null);
                alert2.setContentText("Poprawnie dodano dializę do bazy");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if (result2.get() == ButtonType.OK) {

                    Alert alert3 = new Alert(AlertType.CONFIRMATION);
                    alert3.setTitle("Wygenerować kartę dializy?");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Wygenerować kartę dializy przygotowaną do druku?");
                    alert3.getButtonTypes().setAll(przyciskiZapisywanieDializy);
                    Optional<ButtonType> result3 = alert3.showAndWait();

                    Dializa dializa = new Dializa(date, rodzajDializatora, rodzajDializy, spinnersDouble,
                            textFieldsDouble, textFieldsInt);

                    Pacjent pacjent = dbq.danePacjenta(PESEL, miejscaWklucia, rodzajeDostepow);
                    if (!dbq.isDescriptionInDatabase(opisPrzed.getText())) {
                        listaOpisow.add(opisPrzed.getText());
                    }
                    ZapisDoPliku zapisDoPliku = new ZapisDoPliku(pacjent, dializa, opisPrzed.getText());
                    if (result3.get() == przyciskiZapisywanieDializy.get(0)) {
                        zapisDoPliku.save();

                    } else if (result3.get() == przyciskiZapisywanieDializy.get(1)) {
                        zapisDoPliku.saveAndPrint();
                    }
                    for (Spinner spinner : dodajDializeSpinners) {
                        spinner.getEditor().clear();
                    }

                    for (TextField dodajDializeTextField : dodajDializeTextFields) {
                        dodajDializeTextField.clear();
                    }
                }

            } else {
                String str1 = null;
                if (dodajDializePesel.getText().length() < 11) {
                    str1 = "krótszy niż 11 znaków";
                } else if (dodajDializePesel.getText().length() > 11) {
                    str1 = "dłuższy niż 11 znaków.";
                }

                Alert alert2 = new Alert(AlertType.ERROR);

                alert2.setTitle(null);
                alert2.setHeaderText("Błąd w dodawaniu nowej dializy");

                alert2.setContentText("\n" + "PESEL: " + str1);
                alert2.showAndWait();

            }

        }
    }

    @FXML
    void clearTextAreasInSearching(ActionEvent event) {
        peselForSearching.clear();
        datePicker.getEditor().clear();
    }

    @FXML
    void searchingHelp(ActionEvent event
    ) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Wyszukiwanie pacjenta");
        alert.setContentText("Aby wyszukać pacjenta, wpisz jego numer PESEL. "
                + "Automatycznie dializy uporządkowane są według dat, najnowsza jest na początku. "
                + "Możesz też wyświetlić dane tylko ostatniej dializy lub szukać jej po dacie. W tym celu zaznacz "
                + "odpowiedni przycisk i za pomocą kalendarza wybierz datę.");

        alert.showAndWait();
    }

    @FXML
    void addDialysisHelp(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Dodawanie nowej dializy");
        alert.setContentText("Aby dodać nową dializę, uzupełnij PESEL pacjenta. "
                + "Następnie naciśnij przycisk Podpowiedz. Jeżeli dany pacjent ma zapisane dializy w bazie, "
                + "w odpowiednich miejscach pojawią się wartości parametrów z poprzedniej dializy. Możesz "
                + "przed dodaniem dializy edytować te dane używając strzałek pzy danym polu lub wpisać je ręcznie. "
                + "Gdy wszystkie pola są wypełnione wciśnij przycisk dodaj. "
                + "Zostaniesz przekierowany do nowego okna, w którym zostaniesz poproszony o potwierdzenie wykonania akcji dodawania.");

        alert.showAndWait();
    }

    @FXML
    void addPatientHelp(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Dodawanie nowego pacjenta");
        alert.setContentText("Aby dodać nowego pacjenta, uzupełnij ręcznie jego dane osobowe i za pomocą "
                + "list rozwijanych wybierz parametry dializ. Podanie danych kontaktowych nie jest obowiązkowe, "
                + "te dwa pola możesz pozostawić puste. "
                + "Gdy wszystkie obowiązkowe pola są wypełnione wciśnij przycisk dodaj. "
                + "Następnie w nowym oknie zostaniesz poproszony o potwierdzenie wykonania akcji dodawania.");

        alert.showAndWait();
    }

    @FXML
    void changeDatePickerStatus(ActionEvent event) {
        boolean status = dodajDializeDatePicker.isVisible();
        dodajDializeDatePicker.setVisible(!status);
    }

    @FXML
    void changeDatePickerStatus2(ActionEvent event) {
        datePicker.setVisible(true);
    }

    @FXML
    void changeDatePickerStatus3(ActionEvent event) {
        datePicker.setVisible(false);
    }

    @FXML
    void zakonczDialize(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Potwierdź zakończenie dializy");
        alert.setContentText("Czy na pewno chcesz zakończyć tę dializę?");
        alert.getButtonTypes().setAll(listaPrzyciskow);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == listaPrzyciskow.get(0)) {
            try {
                long PESEL = Long.parseLong(endPESEL.getText());
                ArrayList<Double> textFieldsDouble = new ArrayList<>();
                ArrayList<Integer> textFieldsInt = new ArrayList<>();
                for (TextField textField : zakonczDializePolaTekstoweDouble) {
                    try {
                        if (textField.getText().isEmpty() || textField.getText().trim().isEmpty()) {
                            textFieldsDouble.add(0.0);
                        } else {
                            textFieldsDouble.add(Double.parseDouble(textField.getText().replace(",", ".")));
                        }
                    } catch (NumberFormatException e) {
                        Alert alertError = new Alert(AlertType.ERROR);
                        alertError.setTitle("Wprowadzono błęne dane");
                        alertError.setContentText("Wprowadzono znaki inne niż cyfry do pola " + textField.getId());
                        alertError.showAndWait();
                    }
                }
                for (TextField textField : zakonczDializePolaTekstoweInt) {
                    try {
                        if (textField.getText().isEmpty() || textField.getText().trim().isEmpty()) {
                            textFieldsInt.add(0);
                        } else {
                            textFieldsInt.add(Integer.parseInt(textField.getText()));
                        }
                    } catch (NumberFormatException e) {
                        Alert alertError = new Alert(AlertType.ERROR);
                        alertError.setTitle("Wprowadzono błęne dane");
                        alertError.setContentText("Wprowadzono znaki inne niż cyfry do pola " + textField.getId());
                        alertError.showAndWait();
                    }
                }

                String czas = textFieldsInt.get(0) + ":" + textFieldsInt.get(1) + ":00";
                Time czasZakonczeniaZ = Time.valueOf(czas);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                String nurseDescription = opisPoPielegniarka.getText();
                String doctorDescription = opisPoLekarz.getText();

                ArrayList<ResultSet> resultSets = new ArrayList<>();
                resultSets = dbq.zakonczDialize(PESEL, textFieldsDouble, czasZakonczeniaZ,
                        doctorDescription, nurseDescription);

                String imie = null;
                String nazwisko = null;
                String rodzajDostepu = null;
                String miejsceWklucia = null;
                while (resultSets.get(1).next()) {
                    imie = resultSets.get(1).getString("imie");
                    nazwisko = resultSets.get(1).getString("nazwisko");
                    rodzajDostepu = resultSets.get(1).getString("rodzaj_dostepu");
                    miejsceWklucia = resultSets.get(1).getString("miejsce_wklucia");

                }
                Pacjent pacjent = new Pacjent(PESEL, imie, nazwisko, rodzajDostepu, miejsceWklucia);

                ArrayList<String> godzina = new ArrayList<>();
                ArrayList<Double> rr = new ArrayList<>();
                ArrayList<Double> uf = new ArrayList<>();

                ArrayList<Double> pulse = new ArrayList<>();

                while (resultSets.get(0).next()) {

                    godzina.add(sdf.format(resultSets.get(0).getTimestamp("godzina").getTime()));
                    rr.add(resultSets.get(0).getDouble("rr"));
                    pulse.add(resultSets.get(0).getDouble("puls"));
                    uf.add(resultSets.get(0).getDouble("uf"));

                }

                ArrayList<String> opisyZProgramu = new ArrayList<>();
                opisyZProgramu.add(opisPoLekarz.getText());
                opisyZProgramu.add(opisPoPielegniarka.getText());

                for (int i = 0; i < opisyZProgramu.size(); i++) {

                    if (!dbq.isDescriptionInDatabase(opisyZProgramu.get(i))) {
                        listaOpisow.add(opisyZProgramu.get(i));
                    }
                }

                Alert alert2 = new Alert(AlertType.CONFIRMATION);
                alert2.setTitle("Confirmation Dialog");
                alert2.setHeaderText("Generowanie karty zakończenia dializy");
                alert2.setContentText("Wygenerować kartę?");
                alert2.getButtonTypes().setAll(przyciskiZapisywanieDializy);
                Optional<ButtonType> result2 = alert2.showAndWait();

                ZapisDoPliku zapisDoPliku = new ZapisDoPliku(pacjent, textFieldsDouble,
                        nurseDescription, doctorDescription, czas, stanCewnika.getText());
                if (result2.get() == przyciskiZapisywanieDializy.get(0)) {
                    zapisDoPliku.zakonczDialize(false);
                } else if (result2.get() == przyciskiZapisywanieDializy.get(1)) {
                    zapisDoPliku.zakonczDialize(true);
                }
                for (TextField textField : zakonczDializePolaTekstowe) {
                    textField.clear();

                }

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void connect() {

        try {
            Process process = Runtime.getRuntime().exec("cmd /c start C:\\Users\\Maciek\\Desktop\\Dialysis\\Dialysis - Java Project\\polaczenie.bat");

            while (process.isAlive()) {
                process.waitFor();
                Thread.sleep(1000);

            }

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void sprawdzRodzajDializatora(MouseEvent event) {
        if (dodajDializeRodzajDializatora.getValue().equals("Inny")) {
            paneInnyDializator.setVisible(true);
        } else {
            paneInnyDializator.setVisible(false);
        }
    }

    //Inicjalizacja pracy programu
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect();

        rodzajeDializatorow = dbq.pobierzRodzajeDializatorow();

        listaOpisow = dbq.pobierzOpisy();
        miejscaWklucia = dbq.pobierzMiejscaWklucia();

        rodzajeDostepow = dbq.pobierzRodzajeDostepow();
        dniTygodnia = dbq.pobierzDniTygodnia();

        rodzajeZmian = dbq.pobierzRodzajeZmian();

        listaPeseli = dbq.pobierzListePeseli();

        imonaZbazy = dbq.pobierzListeImion();

        rodzajeDializ = dbq.pobierzRodzajeDializ();

        listaChoiceBoxes.add(dniDializyCHBox);
        listaObserwatorow.add(dniTygodnia);

        listaChoiceBoxes.add(zmianaCHBox);
        listaObserwatorow.add(rodzajeZmian);

        listaChoiceBoxes.add(dodajDializeRodzajDializatora);
        listaObserwatorow.add(rodzajeDializatorow);

        listaChoiceBoxes.add(miejsceWkluciaCHBox);
        listaObserwatorow.add(miejscaWklucia);

        listaChoiceBoxes.add(rodzajDostepuCHBox);
        listaObserwatorow.add(rodzajeDostepow);

        listaChoiceBoxes.add(rodzajDializyChoiceBox);
        listaObserwatorow.add(rodzajeDializ);

        for (ChoiceBox choiceBox : listaChoiceBoxes) {
            choiceBox.setItems(listaObserwatorow.get(listaChoiceBoxes.indexOf(choiceBox)));
            choiceBox.setValue(listaObserwatorow.get(listaChoiceBoxes.indexOf(choiceBox)).get(0));
        }

        dodajDializeSpinners.add(suchaWaga);//0
        dodajDializeSpinners.add(potas);//1
        dodajDializeSpinners.add(wapn);//2
        dodajDializeSpinners.add(sod);//3
        dodajDializeSpinners.add(glukoza);//4
        dodajDializeSpinners.add(epo);//5
        dodajDializeSpinners.add(fe);//6
        dodajDializeSpinners.add(numerKoncentratu);//7
        dodajDializeSpinners.add(bolusSpinner);//8
        dodajDializeSpinners.add(pompaSpinner);//9
        dodajDializeSpinners.add(dawkaSpinner);//10

        dodajDializeTextFields.add(opisPrzed);
        dodajDializeTextFields.add(dodajDializeNumerStanowiska);
        dodajDializeTextFields.add(dodajDializeNumerAparatu);
        dodajDializeTextFields.add(temperatura);
        dodajDializeTextFields.add(konduktywnosc);
        dodajDializeTextFields.add(przeplyw);
        dodajDializeTextFields.add(ultrafiltracja);
        dodajDializeTextFields.add(czasHH);
        dodajDializeTextFields.add(czasMM);
        dodajDializeTextFields.add(heparynaStopHH);
        dodajDializeTextFields.add(heparynaStopMM);

        dodajDializeTextFieldsDouble.add(ultrafiltracja);//0
        dodajDializeTextFieldsDouble.add(konduktywnosc);//1
        dodajDializeTextFieldsDouble.add(przeplyw);//2
        dodajDializeTextFieldsDouble.add(temperatura);//3

        dodajDializeTextFieldsInt.add(dodajDializeNumerStanowiska);//0
        dodajDializeTextFieldsInt.add(dodajDializeNumerAparatu);//1
        dodajDializeTextFieldsInt.add(czasHH);//2
        dodajDializeTextFieldsInt.add(czasMM);//3
        dodajDializeTextFieldsInt.add(heparynaStopHH);//4
        dodajDializeTextFieldsInt.add(heparynaStopMM);//5

        dodajPacjentaPolaTekstowe.add(peselTXT);
        dodajPacjentaPolaTekstowe.add(imieTXT);
        dodajPacjentaPolaTekstowe.add(nazwiskoTXT);
        dodajPacjentaPolaTekstowe.add(telefon1TXT);
        dodajPacjentaPolaTekstowe.add(telefon2TXT);

        zakonczDializePolaTekstowe.add(objetoscKrwiPo);
        zakonczDializePolaTekstowe.add(wagaPo);
        zakonczDializePolaTekstowe.add(endPESEL);
        zakonczDializePolaTekstowe.add(opisPoLekarz);
        zakonczDializePolaTekstowe.add(opisPoPielegniarka);
        zakonczDializePolaTekstowe.add(zakonczenieHH);
        zakonczDializePolaTekstowe.add(zakonczenieMM);
        zakonczDializePolaTekstowe.add(objetoscSubstytucji);
        zakonczDializePolaTekstowe.add(stanCewnika);

        zakonczDializePolaTekstoweDouble.add(wagaPo);
        zakonczDializePolaTekstoweDouble.add(objetoscKrwiPo);
        zakonczDializePolaTekstoweDouble.add(objetoscSubstytucji);

        zakonczDializePolaTekstoweInt.add(zakonczenieHH);
        zakonczDializePolaTekstoweInt.add(zakonczenieMM);

        ButtonType buttonTypeTak = new ButtonType("TAK", ButtonData.OK_DONE);
        ButtonType buttonTypeNie = new ButtonType("NIE", ButtonData.CANCEL_CLOSE);
        listaPrzyciskow.add(buttonTypeTak);
        listaPrzyciskow.add(buttonTypeNie);

        ButtonType buttonTypeZapisz = new ButtonType("Zapisz", ButtonData.OK_DONE);
        ButtonType buttonTypeZapiszIdrukuj = new ButtonType("Drukuj i zapisz", ButtonData.OK_DONE);
        przyciskiZapisywanieDializy.add(buttonTypeZapisz);
        przyciskiZapisywanieDializy.add(buttonTypeZapiszIdrukuj);
        przyciskiZapisywanieDializy.add(buttonTypeNie);

        dodajDializeDatePicker.setVisible(false);
        datePicker.setVisible(false);
        TextFields.bindAutoCompletion(dodajDializePesel, listaPeseli);
        TextFields.bindAutoCompletion(peselForSearching, listaPeseli);
        TextFields.bindAutoCompletion(endPESEL, listaPeseli);
        TextFields.bindAutoCompletion(stanCewnika, listaOpisow);
        TextFields.bindAutoCompletion(opisPrzed, listaOpisow);
        TextFields.bindAutoCompletion(opisPoLekarz, listaOpisow);
        TextFields.bindAutoCompletion(opisPoPielegniarka, listaOpisow);
        TextFields.bindAutoCompletion(imieTXT, imonaZbazy);
        label.textProperty().bind(dodajDializePesel.textProperty()
                .length()
                .asString("%d" + "/11"));
        label2.textProperty().bind(peselForSearching.textProperty()
                .length()
                .asString("%d" + "/11"));
        label3.textProperty().bind(peselTXT.textProperty()
                .length()
                .asString("%d" + "/11"));
        label4.textProperty().bind(endPESEL.textProperty().length().asString("%d" + "/11"));
        paneInnyDializator.setVisible(false);

    }

}
