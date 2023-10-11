package com.miniproject.fitswitchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.miniproject.fitswitchapp.ui.home.HomeFragment;

public class LoginActivity extends AppCompatActivity {

    TextView NewUser;
    EditText Emailid;
    EditText Password;
    ImageButton Loginbtn;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);
        NewUser = findViewById(R.id.newUser);
        ProgressDialog progressDialog = new ProgressDialog(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Emailid = findViewById(R.id.loginEmail);
        Loginbtn = findViewById(R.id.imageButton);
        Password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, NewRegistration.class));
            }
        });

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
            }
        });
    }

//    private void setProgressDialog() {
//        int llPadding = 30;
//        LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(LinearLayout.HORIZONTAL);
//        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
//        ll.setGravity(Gravity.CENTER);
//        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        llParam.gravity = Gravity.CENTER;
//        ll.setLayoutParams(llParam);
//
//        // Creating a ProgressBar inside the layout
//        ProgressBar progressBar = new ProgressBar(this);
//        progressBar.setIndeterminate(true);
//        progressBar.setPadding(0, 0, llPadding, 0);
//        progressBar.setLayoutParams(llParam);
//        llParam = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//        llParam.gravity = Gravity.CENTER;
//
//        // Creating a TextView inside the layout
//        TextView tvText = new TextView(this);
//        tvText.setText("");
//        tvText.setTextColor(Color.parseColor("#000000"));
//        tvText.setTextSize(20f);
//        tvText.setLayoutParams(llParam);
//        ll.addView(progressBar);
//        ll.addView(tvText);
//
//        // Setting the AlertDialog Builder view
//        // as the Linear layout created above
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setView(ll);
//
//        // Displaying the dialog
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        Window window = dialog.getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//            layoutParams.copyFrom(dialog.getWindow().getAttributes());
//            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
//            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//            dialog.getWindow().setAttributes(layoutParams);
//
//            // Disabling screen touch to avoid exiting the Dialog
//            ((Window) window).setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        }
//    }

    private void perforLogin() {
        String emailid = Emailid.getText().toString();
        String userPassword = Password.getText().toString();

        if (userPassword.isEmpty() || userPassword.length() < 6) {
            Password.setError("Enter a valid password");
        } else {
//            progressDialog.
//            progressDialog.setTitle("Login");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();

            //Loginbtn.setOnClickListener(v -> setProgressDialog());

            mAuth.signInWithEmailAndPassword(emailid, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendUserToNextActivity();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
//                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
