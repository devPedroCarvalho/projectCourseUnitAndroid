package wineapp.java.wineappproject.ui;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import wineapp.java.wineappproject.R;

public class RegisterActivity extends BaseClass {

    private Button btnRegisterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnRegisterUser = findViewById(R.id.button_register);
        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser("2pmartins96@gmail.com","1234567");
                Toast.makeText(RegisterActivity.this,"teste", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registerUser(String email, String password){
        userAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            goToHomeActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("TAG", "createUserWithEmail:failure" + task.getException());
                        }
                    }
                });
    }

    private void goToHomeActivity(){
        Intent intent = new Intent(
                RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}