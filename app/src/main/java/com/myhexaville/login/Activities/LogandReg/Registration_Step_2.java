package com.myhexaville.login.Activities.LogandReg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myhexaville.login.R;

public class Registration_Step_2 extends AppCompatActivity {
    EditText name,com_name,email,password,address,mobile;
    Button nxbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__step_2);
        name= findViewById(R.id.name);
        com_name =findViewById(R.id.com_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);
        nxbtn = findViewById(R.id.nxbtn);
        nxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() !=0 && com_name.getText().toString().length()!=0 && email.getText().toString().length()!=0
                && password.getText().toString().length() !=0) {
                    if(isValidEmail(email.getText().toString()) && mobile.getText().toString().length() ==10)
                    {
                        Intent intent = new Intent(Registration_Step_2.this, Registration_Step_3.class);
                        intent.putExtra("name" , name.getText().toString());
                        intent.putExtra("com_name" , com_name.getText().toString());
                        intent.putExtra("email" , email.getText().toString());
                        intent.putExtra("password" , password.getText().toString());
                        intent.putExtra("address" , address.getText().toString());
                        intent.putExtra("mobile" , mobile.getText().toString());
                        startActivity(intent);   
                    }else {
                        if(mobile.getText().toString().length() !=10)
                        {
                            mobile.setError("Mobile should be exactly 10 digit");
                        }
                        if(isValidEmail(email.getText().toString())) {
                            email.setError("Email is not valid");
                        }
                    }
                  
                }else {
                    Toast.makeText(Registration_Step_2.this, "Please fill all requirements", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public static boolean isValidEmail(CharSequence target) {

        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
