package com.example.dialysis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoLogin extends AsyncTask<String, String, Boolean> {

    Context context;
    ConnectionClass connectionClass = new ConnectionClass();

    public DoLogin(Context mContext) {
        this.context = mContext.getApplicationContext();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String login = params[0];
        String password = params[1];
        Connection con = connectionClass.CONN("root", "root");

        if (con == null) {
            Toast.makeText(context, "Błąd połączenia z serwerem MySQL.", Toast.LENGTH_LONG).show();
        } else {
            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM dane_uzytkownikow WHERE login=? AND haslo=?");
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Boolean s) {
        super.onPostExecute(s);
        if (s) {
            Toast.makeText(context, "Zalogowano", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, InsertActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Zły login lub hasło. Spróbuj ponownie.", Toast.LENGTH_LONG).show();
        }
    }

}
