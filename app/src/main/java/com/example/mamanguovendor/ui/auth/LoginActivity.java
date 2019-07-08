package com.example.mamanguovendor.ui.auth;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.mamanguovendor.R;
import com.example.mamanguovendor.ui.components.NavigationActivity;
import com.example.mamanguovendor.util.FormValidators;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

        findViewById(R.id.textViewCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(view -> {

            boolean validForm;

            Editable mobileNo = mobileNoEditText.getText();
            Editable password = passwordEditText.getText();

            validForm = isValidForm(mobileNo,password);

            if (validForm) {
                assert mobileNo != null;
                assert password != null;
                login(mobileNo, password);
            }
        });
        addOnKeyListeners();
    }



    private void login(Editable mobileNo, Editable password) {
        viewModel.login(mobileNo.toString(), password.toString()).observe(this, userClass -> {
            if (userClass == null){
                new MaterialAlertDialogBuilder(this)
                        .setMessage("Authorisation Failed")
                        .setPositiveButton("Try Again", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }else {
                Log.d(LOG_TAG, userClass.getToken());
                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidForm(Editable mobileNo, Editable password) {
        if (!FormValidators.isPhoneNumberValid(mobileNo)) {
            mobileNoEditText.setError(getString(R.string.error_mobileNo));
            return false;
        } else {
            mobileNoEditText.setError(null);
        }


        if (!FormValidators.isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.error_password));
            return false;
        } else {
            passwordEditText.setError(null);
        }

        return true;
    }
    private void addOnKeyListeners() {

        passwordEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isPasswordValid(passwordEditText.getText())) {
                passwordEditText.setError(null);
            }
            return false;
        });
        mobileNoEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isIDNumberValid(
                    mobileNoEditText.getText())) {
                mobileNoEditText.setError(null);
            }
            return false;
        });
    }
}
