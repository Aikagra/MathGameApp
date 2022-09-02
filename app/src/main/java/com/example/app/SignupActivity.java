package com.example.app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupActivity extends AppCompatActivity {

    Button backBtnSignup, signupBtn;
    EditText passwordSignup, nameSignup, emailSignup;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://mathgame-25a50-default-rtdb.asia-southeast1.firebasedatabase.app/");
    private DatabaseReference reference = db.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }

        setContentView(R.layout.activity_signup);

        backBtnSignup = findViewById(R.id.backBtnSignup);
        signupBtn = findViewById(R.id.signupButton);
        passwordSignup = findViewById(R.id.passwordSignup);
        nameSignup = findViewById(R.id.nameSignup);
        mAuth = FirebaseAuth.getInstance();
        emailSignup = findViewById(R.id.emailSignup);
        CustomDialogClass cdd = new CustomDialogClass(SignupActivity.this);





        //Toast for signup button

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

            private void registerUser() {
                String email = emailSignup.getText().toString().trim();
                String password = passwordSignup.getText().toString().trim();
                String name = nameSignup.getText().toString().trim();

                if (name.isEmpty()){
                    nameSignup.setError("Full Name is required");
                    nameSignup.requestFocus();
                    return;
                }

                if (name.length() > 10){
                    passwordSignup.setError("Name cannot be longer than 10 characters");
                    passwordSignup.requestFocus();
                    return;
                }

                if (email.isEmpty()){
                    emailSignup.setError("Email is required");
                    emailSignup.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailSignup.setError("Provide a valid email");
                    emailSignup.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    passwordSignup.setError("Password is Required");
                    passwordSignup.requestFocus();
                    return;
                }

                if (password.length() < 6){
                    passwordSignup.setError("Provide a password with atleast 6 characters");
                    passwordSignup.requestFocus();
                    return;
                }

                cdd.show();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    UserHelperClass helperClass = new UserHelperClass(name, email);

                                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(helperClass);

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            FancyToast.makeText(SignupActivity.this, "Verification Email Sent", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                            cdd.dismiss();
                                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "on Failure : email not sent" + e.getMessage());
                                            cdd.dismiss();
                                        }
                                    });
                                } else {
                                    FancyToast.makeText(SignupActivity.this, "Signup Unsuccessful", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                    cdd.dismiss();
                                }
                            }
                        });



            }
        });



        //functionality for login button on signup page

        backBtnSignup.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            }else {
                startActivity(intent);
            }


        });

    }


}

