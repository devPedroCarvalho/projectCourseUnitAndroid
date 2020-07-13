package wineapp.java.wineappproject.ui;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;


class BaseClass extends AppCompatActivity {

    public FirebaseAuth userAuth = FirebaseAuth.getInstance();

    public void goToWithFinish(Context contextStart, Class classFinish){
        Intent intent = new Intent(
                contextStart, classFinish);
        startActivity(intent);
        finish();
    }

    public void goTo(Context contextStart, Class classFinish){
        Intent intent = new Intent(
                contextStart, classFinish);
        startActivity(intent);
    }

}
