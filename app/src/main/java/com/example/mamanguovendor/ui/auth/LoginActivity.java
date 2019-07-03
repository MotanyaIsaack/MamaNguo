package com.example.mamanguovendor.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mamanguovendor.R;
import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.ui.requests.RequestsActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();
    private TextInputEditText mobileNoEditText;
    private TextInputLayout mobileTextInput;

    private TextInputEditText passwordEditText;
    private TextInputLayout passwordTextInput;
    private LoginActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.material_text_button_login);

        mobileTextInput = findViewById(R.id.textInputLayoutMobileNumber);
        mobileNoEditText = findViewById(R.id.textInputEditTextMobile);

        passwordEditText = findViewById(R.id.textInputEditTextPassword);
        passwordTextInput = findViewById(R.id.textInputLayoutPassword);

        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);

        loginButton.setOnClickListener(view -> {

            Editable mobileNo = mobileNoEditText.getText();
            Editable password = passwordEditText.getText();

            login(mobileNo, password);


        });

        findViewById(R.id.textViewCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

    }

    private void login(Editable mobileNo, Editable password) {
        viewModel.login(mobileNo.toString(), password.toString()).observe(this, new Observer<UserClass>() {
            @Override
            public void onChanged(UserClass userClass) {
                if (userClass == null){
                    Toast.makeText(LoginActivity.this, "Authorisation Failed", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d(LOG_TAG, userClass.getToken());
//                    Intent intent = new Intent(LoginActivity.this, RequestsActivity.class);
//                    intent.putExtra("userdata")
                    Toast.makeText(LoginActivity.this, userClass.getToken(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
