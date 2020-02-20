package com.example.dialysis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    TextView login;
    TextView password;
    Button loginBtn;
    Button returnBtn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        returnBtn = findViewById(R.id.returnBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login_str = login.getText().toString();
                String password_str = password.getText().toString();

                if (!emptyValidate(login, password)) {
                    DoLogin doLogin = new DoLogin(context);
                    doLogin.execute(login_str, password_str);
                    login.setText("");
                    password.setText("");

                } else {
                    Toast.makeText(getApplicationContext(), "Należy wypełnić wszystkie pola",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean emptyValidate(TextView login,
                                  TextView password) {
        String login_str = login.getText().toString();
        String password_str = password.getText().toString();
        return (login_str.isEmpty() || password_str.isEmpty());
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void powrot(View view) {
        openMainActivity();
    }

    public void openInsertActivity() {
        Intent intent = new Intent(this, InsertActivity.class);
        startActivity(intent);
    }
}
