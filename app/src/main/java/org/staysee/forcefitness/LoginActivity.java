package org.staysee.forcefitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText UserName_Box;
    EditText Password_Box;
    private TextView loginAttempt;
    private Button Login_Button;
    private int attempt = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName_Box = (EditText) findViewById(R.id.UsernameBox);
        Password_Box = (EditText) findViewById(R.id.PasswordBox);
        loginAttempt = (TextView) findViewById(R.id.LoginAttempt);
        Login_Button = (Button) findViewById(R.id.LoginButton);
        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(UserName_Box.getText().toString(), Password_Box.getText().toString());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        String user_Name = UserName_Box.getText().toString();
        String passWord = Password_Box.getText().toString();

        savedInstanceState.putString("username", user_Name);
        savedInstanceState.putString("password", passWord);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        String username = savedInstanceState.getString("username");
        String password = savedInstanceState.getString("password");

        UserName_Box.setText(username);
        Password_Box.setText(password);

    }

    private void check(String userName, String password) {
        if ((userName.equals("jeremy")) && (password.equals("123")) ||
                (userName.equals("hunter")) && (password.equals("123"))) {
            startActivity(new Intent(this, MainMenuActivity.class));
        } else {
            attempt--;
            loginAttempt.setText("No of attempts remaining: " + String.valueOf(attempt));
            if (attempt == 0) {
                Login_Button.setEnabled(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserName_Box.setText("");
        Password_Box.setText("");

    }
}
