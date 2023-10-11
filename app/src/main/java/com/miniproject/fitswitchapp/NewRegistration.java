package com.miniproject.fitswitchapp;

import static android.view.WindowManager.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewRegistration extends AppCompatActivity {

    TextView ExistingUser;
    EditText Emailid;
    EditText Password;
    EditText ConfirmPassword;
    ImageButton Loginbtn;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);

        ExistingUser = findViewById(R.id.newUser);
        Emailid = findViewById(R.id.email);
        Loginbtn = findViewById(R.id.imageButton);
        Password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirmPassword);
        ProgressDialog progressDialog = new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        ExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alreadyExists = new Intent(NewRegistration.this, LoginActivity.class);
                startActivity(alreadyExists);
            }
        });


        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String emailid = Emailid.getText().toString();
        String userPassword = Password.getText().toString();
        String UserConfirmPassword = ConfirmPassword.getText().toString();

        if (userPassword.isEmpty() || userPassword.length()<6) {
            Password.setError("Enter a valid password");
        } else if (!userPassword.equals(UserConfirmPassword)) {
            ConfirmPassword.setError("Password and Confirm Password do no match!");
        }
        else {
//            progressDialog.setMessage("Please wait while registration");
//            progressDialog.setTitle("Registration");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();

            Loginbtn.setOnClickListener(v -> setProgressDialog());


            mAuth.createUserWithEmailAndPassword(emailid,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
//                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(NewRegistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                        progressDialog.dismiss();
                        Toast.makeText(NewRegistration.this, ""+task.getException() , Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void setProgressDialog() {
        int llPadding = 30;
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        // Creating a ProgressBar inside the layout
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, llPadding, 0);
        progressBar.setLayoutParams(llParam);
        llParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        llParam.gravity = Gravity.CENTER;

        // Creating a TextView inside the layout
        TextView tvText = new TextView(this);
        tvText.setText("");
        tvText.setTextColor(Color.parseColor("#000000"));
        tvText.setTextSize(20f);
        tvText.setLayoutParams(llParam);
        ll.addView(progressBar);
        ll.addView(tvText);

        // Setting the AlertDialog Builder view
        // as the Linear layout created above
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(ll);

        // Displaying the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);

            // Disabling screen touch to avoid exiting the Dialog
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(NewRegistration.this, HomeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}