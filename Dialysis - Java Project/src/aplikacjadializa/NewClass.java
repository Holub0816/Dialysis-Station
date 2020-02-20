/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadializa;

import java.sql.Time;


public class NewClass {
    public static void main(String[] args) {
        String time = "14:35:00";
        Time czas = Time.valueOf(time);
        System.out.println(czas);
    }
}
