package wineapp.java.wineappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth user = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                verifyIfUserLogged();
                //goToMainActivity();
            }
        }, 3000);
    }

    private void verifyIfUserLogged(){
        if (user.getCurrentUser() != null){
            goToHomeActivity();
            Log.d("TAG", "User logado");
        }else {
            goToMainActivity();
            Log.d("TAG", "User nao logado");
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(
                SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToHomeActivity(){
        Intent intent = new Intent(
                SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }



}