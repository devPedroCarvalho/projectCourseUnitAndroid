package wineapp.java.wineappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private TextView txtViewGoToRegister;
    private Button btnLogin;
    private FirebaseAuth user = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtViewGoToRegister= findViewById(R.id.textView_registerAction_login);
        btnLogin = findViewById(R.id.button_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser("2pmartins96@gmail.com","1234567");
            }
        });

        txtViewGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterActivity();
            }
        });
/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dtRef = database.getReference();
        dtRef.child("teste").setValue("Hello Wine !");
        dtRef.child("teste2").setValue("Hello Wine app !");

 */
    }

    private void goToRegisterActivity(){
        Intent intent = new Intent(
                LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void goToHomeActivity(){
        Intent intent = new Intent(
                LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

private void loginUser(String emailLogin, String passwordLogin){

        user.signInWithEmailAndPassword(emailLogin,passwordLogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "LOGINUserWithEmail:success");
                    goToHomeActivity();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("TAG", "LOGINUserWithEmail:failure" + task.getException());
                }
            }
        });
}

}