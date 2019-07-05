package ics.dynamic.dgfab;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.myhexaville.login.R;
import com.myhexaville.login.databinding.ActivityMainBinding;
import ics.dynamic.dgfab.login.LoginFragment;
import ics.dynamic.dgfab.login.SignUpFragment;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static ics.dynamic.dgfab.FlexibleFrameLayout.ORDER_LOGIN_STATE;
import static ics.dynamic.dgfab.FlexibleFrameLayout.ORDER_SIGN_UP_STATE;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private boolean isLogin = true;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    TextView tv_skip_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        tv_skip_login=findViewById(R.id.tv_skip_login);

        tv_skip_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Buyer_Main_Navigation.class);
                startActivity(intent);
                finish();
            }
        });

//        LoginFragment topLoginFragment = new LoginFragment();
//        SignUpFragment topSignUpFragment = new SignUpFragment();
        SignUpFragment topLoginFragment = new SignUpFragment();
         LoginFragment topSignUpFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sign_up_fragment, topSignUpFragment)
                .replace(R.id.login_fragment, topLoginFragment)
                .commit();

        if(checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }


        binding.loginFragment.setRotation(-90);

        binding.button.setOnSignUpListener(topLoginFragment);
        binding.button.setOnLoginListener(topSignUpFragment);

        binding.button.setOnButtonSwitched(isLogin -> {
            binding.getRoot()
                    .setBackgroundColor(ContextCompat.getColor(
                            this,
                            isLogin ? R.color.secondPage: R.color.colorPrimary));
        });

        binding.loginFragment.setVisibility(INVISIBLE);
       // binding.signUpFragment.setVisibility(INVISIBLE);
    }

    //*******************************************************************
    private  boolean checkAndRequestPermissions() {
        int permissionCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionStorage1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        //int permissionPhone = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionCamara != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (permissionStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionStorage1 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
//    if (permissionPhone != PackageManager.PERMISSION_GRANTED) {
//        listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
//    }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
//**********************************************************************

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        binding.loginFragment.setPivotX(binding.loginFragment.getWidth() / 2);
        binding.loginFragment.setPivotY(binding.loginFragment.getHeight());
        binding.signUpFragment.setPivotX(binding.signUpFragment.getWidth() / 2);
        binding.signUpFragment.setPivotY(binding.signUpFragment.getHeight());
    }

    public void switchFragment(View v) {
        if (isLogin) {
            binding.loginFragment.setVisibility(VISIBLE);
            binding.loginFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    binding.signUpFragment.setVisibility(INVISIBLE);
                    binding.signUpFragment.setRotation(90);
                    binding.wrapper.setDrawOrder(ORDER_LOGIN_STATE);
                }
            });
        } else {
            binding.signUpFragment.setVisibility(VISIBLE);
            binding.signUpFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    binding.loginFragment.setVisibility(INVISIBLE);
                    binding.loginFragment.setRotation(-90);
                    binding.wrapper.setDrawOrder(ORDER_SIGN_UP_STATE);
                }
            });
        }

        isLogin = !isLogin;
        binding.button.startAnimation();
    }

}