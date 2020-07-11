package wineapp.java.wineappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wineapp.java.wineappproject.model.GetDataService;
import wineapp.java.wineappproject.model.ModelWine;
import wineapp.java.wineappproject.model.RetrofitClientInstance;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogout;
    private FirebaseAuth user = FirebaseAuth.getInstance();
    private TextView firstWine;
    private TextView secondWine;
    private TextView thirdWine;
    private TextView textAboutWine;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.button_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 logoutUser();
            }
        });

        firstWine = findViewById(R.id.textView_wine1_id);
        secondWine =  findViewById(R.id.textView_wine2_id);
        thirdWine = findViewById(R.id.textView_wine3_id);
        textAboutWine = findViewById(R.id.textView_description);


    }

    private void logoutUser(){
        user.signOut();
        goToLoginActivity();
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(
                HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void callAPI(String food){
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<ModelWine> call = service.getData("steak","e3d9c39a9b134ddabd2fb1faccb60865");
        call.enqueue(new Callback<ModelWine>() {
            @Override
            public void onResponse(Call<ModelWine> call, Response<ModelWine> response) {

                ModelWine responseModel = response.body();
                String getFirstWine = responseModel.getPairedWines().get(0);
                String getSecondWine = responseModel.getPairedWines().get(1);
                String getThirdWine= responseModel.getPairedWines().get(2);
                String getTextAboutWine = responseModel.getPairingText();

                firstWine.setText(getFirstWine);
                secondWine.setText(getSecondWine);
                thirdWine.setText(getThirdWine);
                textAboutWine.setText(getTextAboutWine);
            }

            @Override
            public void onFailure(Call<ModelWine> call, Throwable t) {
                Log.d("TAG", "onFailure: " +t);

            }
        });
    }

}