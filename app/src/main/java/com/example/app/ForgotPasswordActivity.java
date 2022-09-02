package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.CustomDialogClass;
import com.example.app.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ForgotPasswordActivity extends AppCompatActivity {


    private EditText emailEditText;
    private Button resetPwButton, goToLogin;


    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //assigning id's


        emailEditText = findViewById(R.id.forgotPwEmail);
        resetPwButton = findViewById(R.id.resetPasswordButton);
        goToLogin = findViewById(R.id.goToLogin);
        auth = FirebaseAuth.getInstance();
        CustomDialogClass cdd=new CustomDialogClass(ForgotPasswordActivity.this);

        //intent to login screen when logout is clicked

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //reset password functionality


        resetPwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {

                String email = emailEditText.getText().toString().trim();

                if (email.isEmpty()){
                    emailEditText.setError("Email is required");
                    emailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEditText.setError("Please provide a valid email");
                    emailEditText.requestFocus();
                    return;
                }
                cdd.show();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            FancyToast.makeText(ForgotPasswordActivity.this, "Password Reset Email Sent", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            cdd.dismiss();
                        } else {
                            FancyToast.makeText(ForgotPasswordActivity.this, "Error Occurred, Please Try Again", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            cdd.dismiss();
                        }
                    }
                });
            }

        });



    }
}