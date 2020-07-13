package wineapp.java.wineappproject.ui;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import wineapp.java.wineappproject.R;

public class RegisterActivity extends BaseClass {

    private Button btnRegisterUser;
    private EditText emailRegister;
    private EditText passwordRegister;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegisterUser = findViewById(R.id.button_register);
        emailRegister = findViewById(R.id.edt_emailRegister_id);
        passwordRegister = findViewById(R.id.edt_passwordRegister_id);


        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailRegister.getText().toString().trim();
                password = passwordRegister.getText().toString().trim();

                if (TextUtils.isEmpty(email) ){
                    toastMessageShort(RegisterActivity.this, getString(R.string.text_emptyEmail));
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    toastMessageShort(RegisterActivity.this,getString(R.string.text_CorrectEmail));
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    toastMessageShort(RegisterActivity.this,getString(R.string.text_emptyPassword));
                    return;
                }
                if (password.length() < 6) {
                    toastMessageShort(RegisterActivity.this,getString(R.string.text_lengthPassword));
                    return;
                }
                registerUser(email,password);
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
                            toastMessageLong(RegisterActivity.this,"ERROR!!createUserWithEmail:failure:failure "  + task.getException());
                            //Log.d("TAG", "createUserWithEmail:failure" + task.getException());
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