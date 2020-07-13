package wineapp.java.wineappproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import wineapp.java.wineappproject.R;

public class LoginActivity extends BaseClass {

    private TextView txtViewGoToRegister;
    private Button btnLogin;
    private EditText emailLogin;
    private EditText passwordLogin;
    private String email;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtViewGoToRegister= findViewById(R.id.textView_registerAction_login);
        btnLogin = findViewById(R.id.button_login);
        emailLogin = findViewById(R.id.edt_email_id);
        passwordLogin = findViewById(R.id.editTextTextPassword);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailLogin.getText().toString().trim();
                password = passwordLogin.getText().toString().trim();

                if (TextUtils.isEmpty(email) ){
                    toastMessageShort(LoginActivity.this, getString(R.string.text_emptyEmail));
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    toastMessageShort(LoginActivity.this,getString(R.string.text_CorrectEmail));
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    toastMessageShort(LoginActivity.this,getString(R.string.text_emptyPassword));
                    return;
                }
                if (password.length() < 6) {
                    toastMessageShort(LoginActivity.this,getString(R.string.text_lengthPassword));
                    return;
                }
                    //loginUser("2pmartins96@gmail.com","1234567");
                    loginUser(email,password);


            }
        });

        txtViewGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(LoginActivity.this, RegisterActivity.class);
            }
        });
/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dtRef = database.getReference();
        dtRef.child("teste").setValue("Hello Wine !");
        dtRef.child("teste2").setValue("Hello Wine app !");

 */
    }

private void loginUser(String emailLogin, String passwordLogin){

    userAuth.signInWithEmailAndPassword(emailLogin,passwordLogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    goToWithFinish(LoginActivity.this, HomeActivity.class);
                    //Log.d("TAG", "LOGINUserWithEmail:success");
                } else {
                    // If sign in fails, display a message to the user.
                    toastMessageLong(LoginActivity.this,"ERROR!!LOGINUserWithEmail:failure "  + task.getException());
                    //Log.d("TAG", "LOGINUserWithEmail:failure" + task.getException());
                }
            }
        });
}

}