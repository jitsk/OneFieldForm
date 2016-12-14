package com.android.onefiledform;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.onefieldform.FormBox;


public class MainActivity extends ActionBarActivity {

    private FormBox formBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formBox = (FormBox) findViewById(R.id.formbox);
        formBox.setFormBoxListener(new FormBox.FormBoxListener() {
            @Override
            public void OnInitiated() {

            }

            @Override
            public void onNameEntered(String name) {
                TextView nameView = (TextView) findViewById(R.id.name);
                nameView.setText(name);
            }

            @Override
            public void onEmailEntered(String email) {
                TextView mailView = (TextView) findViewById(R.id.mail);
                mailView.setText(email);

            }

            @Override
            public void onPasswordEntered(String password) {
                TextView passView = (TextView) findViewById(R.id.pass);
                passView.setText(password);
            }

            @Override
            public void onNameError() {
                Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmailError() {
                Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPasswordError() {
                Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
            }
        });
    }



}
