package com.example.mamanguovendor.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import com.example.mamanguovendor.R;
import com.example.mamanguovendor.ui.components.Navigation_activity;
import com.example.mamanguovendor.util.FormValidators;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";

    private TextInputLayout firstNameTextInput;
    private TextInputEditText firstNameEditText;
    private TextInputLayout lastNameTextInput;
    private TextInputEditText lastNameEditText;
    private TextInputLayout emailTextInput;
    private TextInputEditText emailEditText;
    private TextInputLayout mobileNoTextInput;
    private TextInputEditText mobileNoEditText;
    private TextInputLayout idNoTextInput;
    private TextInputEditText idNoEditText;
    private TextInputLayout locationTextInput;
    private TextInputEditText locationEditText;
    private TextInputLayout passwordTextInput;
    private TextInputEditText passwordEditText;
    private MaterialButton signupButton;
    private RegistrationActivityViewModel sviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstNameTextInput = findViewById(R.id.textInputLayoutFirstName);
        lastNameTextInput = findViewById(R.id.textInputLayoutLastName);
        emailTextInput = findViewById(R.id.textInputLayoutEmail);
        mobileNoTextInput = findViewById(R.id.textInputLayoutMobileNumber);
        idNoTextInput = findViewById(R.id.textInputLayoutIDNumber);
        locationTextInput = findViewById(R.id.textInputLayoutLocation);
        passwordTextInput = findViewById(R.id.textInputLayoutPassword);

        firstNameEditText = findViewById(R.id.textInputEditTextFirstName);
        lastNameEditText = findViewById(R.id.textInputEditTextLastName);
        emailEditText = findViewById(R.id.textInputEditTextEmail);
        mobileNoEditText = findViewById(R.id.textInputEditTextMobileNo);
        idNoEditText = findViewById(R.id.textInputEditTextIDNo);
        locationEditText = findViewById(R.id.textInputEditTextLocation);
        passwordEditText = findViewById(R.id.textInputEditTextPassword);

        signupButton = findViewById(R.id.material_text_button_signup);

        sviewModel = ViewModelProviders.of(this).get(RegistrationActivityViewModel.class);

        signupButton.setOnClickListener(v -> {
            boolean validForm;

            Editable firstName = firstNameEditText.getText();
            Editable lastName =lastNameEditText.getText();
            Editable email = emailEditText.getText();
            Editable mobileNo = mobileNoEditText.getText();
            Editable idNo = idNoEditText.getText();
            Editable location = locationEditText.getText();
            Editable password = passwordEditText.getText();

            validForm = isValidForm(firstName,lastName,email,mobileNo,idNo,location,password);

            if (validForm){
                assert firstName != null;
                assert lastName != null;
                assert email != null;
                assert password != null;
                assert mobileNo != null;
                assert location != null;
                assert idNo != null;
                signup(firstName,lastName,email,mobileNo,idNo,location,password);
            }

        });

        addOnKeyListeners();

    }

    private void signup(Editable firstName, Editable lastName, Editable email, Editable mobileNo,
                        Editable idNo, Editable location, Editable password){
        sviewModel.signup(firstName.toString(), lastName.toString(), email.toString(),
            mobileNo.toString(),idNo.toString(),location.toString(),password.toString())
            .observe(this, userClass -> {

                if (userClass!=null){
                    Log.d(TAG, "onChanged: Success"+userClass.getToken());
                    Intent intent = new Intent(RegistrationActivity.this,Navigation_activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegistrationActivity.this,
                            "Registration Failed", Toast.LENGTH_SHORT).show();
                    new MaterialAlertDialogBuilder(this)
                            .setMessage("Registration Failed")
                            .setPositiveButton("Try Again", (dialog, which) -> {
                                firstName.clear();
                                lastName.clear();
                                email.clear();
                                mobileNo.clear();
                                idNo.clear();
                                location.clear();
                                password.clear();
                                dialog.dismiss();

                            })
                            .show();
                }
            });
    }

    private boolean isValidForm(Editable firstName, Editable lastName, Editable email,
                                Editable password, Editable mobileNo, Editable idNumber,
                                Editable location) {

        if (!FormValidators.isNameValid(firstName)) {
            firstNameEditText.setError(getString(R.string.error_firstname));
            return false;
        } else {
            firstNameEditText.setError(null);
        }

        if (!FormValidators.isNameValid(lastName)) {
            lastNameEditText.setError(getString(R.string.error_lastname));
            return false;
        } else {
            lastNameEditText.setError(null);
        }

        if (!FormValidators.isEmailValid(email)) {
            emailEditText.setError(getString(R.string.error_email));
            return false;
        } else {
            emailEditText.setError(null);
        }

        if (!FormValidators.isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.error_password));
            return false;
        } else {
            passwordEditText.setError(null);
        }

        if (!FormValidators.isPhoneNumberValid(mobileNo)) {
            mobileNoEditText.setError(getString(R.string.error_mobileNo));
            return false;
        } else {
            mobileNoEditText.setError(null);
        }

        if (!FormValidators.isNameValid(location)) {
            locationEditText.setError(getString(R.string.error_location));
            return false;
        } else {
            locationEditText.setError(null);
        }

        if (!FormValidators.isIDNumberValid(idNumber)) {
            idNoEditText.setError(getString(R.string.error_idno));
            return false;
        } else {
            idNoEditText.setError(null);
        }

        return true;
    }

    private void addOnKeyListeners() {
        firstNameEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isNameValid(firstNameEditText.getText())) {
                firstNameEditText.setError(null);
            }
            return false;
        });

        lastNameEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isNameValid(lastNameEditText.getText())) {
                lastNameEditText.setError(null);
            }
            return false;
        });

        emailEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isEmailValid(emailEditText.getText())) {
                emailTextInput.setError(null);
            }
            return false;
        });

        passwordEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isPasswordValid(passwordEditText.getText())) {
                passwordEditText.setError(null);
            }
            return false;
        });

        locationEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isNameValid(
                    locationEditText.getText())) {
                locationEditText.setError(null);
            }
            return false;
        });

        idNoEditText.setOnKeyListener((v, keyCode, event) -> {
            if (FormValidators.isIDNumberValid(
                    idNoEditText.getText())) {
                idNoEditText.setError(null);
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
