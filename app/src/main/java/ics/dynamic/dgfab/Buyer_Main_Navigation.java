package ics.dynamic.dgfab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myhexaville.login.R;

public class Buyer_Main_Navigation extends AppCompatActivity {
    TextView logorreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer__main__navigation);
        logorreg =findViewById(R.id.logorreg);
        logorreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Buyer_Main_Navigation.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
