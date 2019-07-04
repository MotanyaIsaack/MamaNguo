package com.example.mamanguovendor.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mamanguovendor.R;
import com.example.mamanguovendor.data.models.UserClass;
import com.example.mamanguovendor.ui.Components.Navigation_activity;
import com.google.android.material.button.MaterialButton;
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

        firstNameEditText = findViewById(R.id.textInputEditTextFirstName);
        lastNameEditText = findViewById(R.id.textInputEditTextLastName);
        emailEditText = findViewById(R.id.textInputEditTextEmail);
        mobileNoEditText = findViewById(R.id.textInputEditTextMobileNo);
        idNoEditText = findViewById(R.id.textInputEditTextIDNo);
        locationEditText = findViewById(R.id.textInputEditTextLocation);
        passwordEditText = findViewById(R.id.textInputEditTextPassword);

        signupButton = findViewById(R.id.material_text_button_signup);

        sviewModel = ViewModelProviders.of(this).get(RegistrationActivityViewModel.class);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable firstName = firstNameEditText.getText();
                Editable lastName =lastNameEditText.getText();
                Editable email = emailEditText.getText();
                Editable mobileNo = mobileNoEditText.getText();
                Editable idNo = idNoEditText.getText();
                Editable location = locationEditText.getText();
                Editable password = passwordEditText.getText();

                signup(firstName,lastName,email,mobileNo,idNo,location,password);
            }
        });

    }

    private void signup(Editable firstName, Editable lastName, Editable email, Editable mobileNo,
                        Editable idNo, Editable location, Editable password){
        sviewModel.signup(firstName.toString(), lastName.toString(), email.toString(),
                mobileNo.toString(),idNo.toString(),location.toString(),password.toString())
                .observe(this, new Observer<UserClass>() {
                    @Override
                    public void onChanged(UserClass userClass) {
                        if (userClass!=null){
                            Log.d(TAG, "onChanged: Sucsess"+userClass.getToken());
                            Toast.makeText(RegistrationActivity.this, userClass.getToken(),
                                    Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(RegistrationActivity.this,Navigation_activity.class));
                        }else{
                            Toast.makeText(RegistrationActivity.this,
                                    "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
