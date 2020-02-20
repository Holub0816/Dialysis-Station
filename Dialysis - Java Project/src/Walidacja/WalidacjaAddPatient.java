/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Walidacja;

import aplikacjadializa.dbUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;


public class WalidacjaAddPatient {

    ArrayList<String> imionaZbazy;

    public WalidacjaAddPatient(ArrayList<String> imionaZbazy) {
        this.imionaZbazy = imionaZbazy;
    }

    public String checkName(String name) {
        if (name.length() <= 0) {
            return "BRAK";
        }
        if (!imionaZbazy.contains(name)) {
            return "Wprowadzono imię spoza bazy";
        } else {
            return "OK";
        }

    }

}
