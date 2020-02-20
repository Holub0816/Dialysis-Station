package com.example.dialysis;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DoPost extends AsyncTask<String, String, String> {

    Context context;

    String z = "";
    ConnectionClass connectionClass = new ConnectionClass();


    public DoPost(Context mContext) {
        this.context = mContext;
    }


    @Override
    protected String doInBackground(String... params) {
        String pesel = params[0];
        String uf = params[1];
        String puls = params[2];
        String rr = params[3];
        String ct = params[4];
        String cz = params[5];
        String tmp = params[6];

        Connection con = connectionClass.CONN("root", "root");
        if (con == null) {
            z = "Błąd połączenia z serwerem MySQL.";


        } else {
            try {
                PreparedStatement ps = con.prepareStatement("insert into przebieg_dializy(data,godzina,PESEL,RR,puls,uf,cisnienie_tetnicze,cisnienie_zylne,tmp) values(?,?,?,?,?,?,?,?,?)");

                Calendar calendar = Calendar.getInstance();
                java.sql.Date dd = new java.sql.Date(calendar.getTime().getTime());
                ps.setDate(1, dd);
                ps.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
                long pesel_long = Long.valueOf(pesel);
                int puls_int = Integer.valueOf(puls);
                double rr_d = Double.valueOf(rr);
                double uf_d = Double.valueOf(uf);
                double ct_d = Double.valueOf(ct);
                double cz_d = Double.valueOf(cz);
                double tmp_d = Double.valueOf(tmp);
                ps.setLong(3, pesel_long);
                ps.setDouble(4, rr_d);
                ps.setInt(5, puls_int);
                ps.setDouble(6, uf_d);
                ps.setDouble(7, ct_d);
                ps.setDouble(8, cz_d);
                ps.setDouble(9, tmp_d);
                ps.executeUpdate();
                z = "Rekord został dodany do bazy danych.";


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, z, Toast.LENGTH_LONG).show();
    }
}

