package com.android.onefiledform;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.onefieldform.Events.EmailError;
import com.android.onefieldform.Events.EmailReceived;
import com.android.onefieldform.Events.EmailVerified;
import com.android.onefieldform.Events.NameError;
import com.android.onefieldform.Events.NameReceived;
import com.android.onefieldform.Events.NameVerified;
import com.android.onefieldform.Events.PasswordError;
import com.android.onefieldform.Events.PasswordReceived;
import com.android.onefieldform.Events.PasswordVerified;
import com.android.onefieldform.FormBox;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MainActivity extends ActionBarActivity {

    private FormBox formBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formBox = (FormBox) findViewById(R.id.formbox);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onNameReceived(NameReceived nameReceived) {
        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(nameReceived.getName());
        //process name
        EventBus.getDefault().post(new NameVerified());
    }

    @Subscribe
    public void onEmailReceived(EmailReceived emailReceived) {
        TextView mailView = (TextView) findViewById(R.id.mail);
        mailView.setText(emailReceived.getEmail());
        //process email
        EventBus.getDefault().post(new EmailVerified());
    }

    @Subscribe
    public void onPasswordReceived(PasswordReceived passwordReceived) {
        TextView passView = (TextView) findViewById(R.id.pass);
        passView.setText(passwordReceived.getPassword());
        //process passsword
        EventBus.getDefault().post(new PasswordVerified());
    }

    @Subscribe
    public void onNameError(NameError nameError) {
        Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEmailError(EmailError emailError) {
        Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onPasswordError(PasswordError passwordError) {
        Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_SHORT).show();
    }
}

