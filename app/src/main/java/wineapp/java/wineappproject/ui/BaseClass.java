package wineapp.java.wineappproject.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

    public void toastMessageShort(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT)
                .show();
    }

    public void toastMessageLong(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_LONG)
                .show();
    }

}
