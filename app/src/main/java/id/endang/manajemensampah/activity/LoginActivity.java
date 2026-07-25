package id.endang.manajemensampah.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import id.endang.manajemensampah.R;



public class LoginActivity extends AppCompatActivity {



    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private static final String USERNAME =
            "admin";
    private static final String PASSWORD =
            "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(v -> login());
    }

    private void initView(){
        etUsername =
                findViewById(R.id.etUsername);
        etPassword =
                findViewById(R.id.etPassword);
        btnLogin =
                findViewById(R.id.btnLogin);
    }

    private void login(){
        String username =
                etUsername.getText()
                        .toString()
                        .trim();
        String password =
                etPassword.getText()
                        .toString()
                        .trim();

        if(username.isEmpty()){
            etUsername.setError(
                    "Username wajib diisi"
            );
            etUsername.requestFocus();
            return;


        }

        if(password.isEmpty()){
            etPassword.setError(
                    "Password wajib diisi"
            );
            etPassword.requestFocus();
            return;
        }

        if(username.equals(USERNAME)
                &&
                password.equals(PASSWORD)){

            Toast.makeText(

                    this,

                    "Login berhasil",

                    Toast.LENGTH_SHORT

            ).show();

            Intent intent =
                    new Intent(

                            LoginActivity.this,

                            DashboardActivity.class

                    );


            intent.setFlags(

                    Intent.FLAG_ACTIVITY_NEW_TASK
                            |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK

            );


            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(

                    this,

                    "Username atau Password salah",

                    Toast.LENGTH_LONG

            ).show();
            etPassword.setText("");
            etPassword.requestFocus();

        }

    }

    @Override
    public void onBackPressed(){
        finishAffinity();

    }



}