package wineapp.java.wineappproject.ui;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import wineapp.java.wineappproject.R;

public class SplashActivity extends BaseClass {

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
        if (userAuth.getCurrentUser() != null){
            goToWithFinish(SplashActivity.this, HomeActivity.class);
            //Log.d("TAG", "User logado");
        }else {
           goToWithFinish(SplashActivity.this, LoginActivity.class);
           // Log.d("TAG", "User nao logado");
        }
    }

}