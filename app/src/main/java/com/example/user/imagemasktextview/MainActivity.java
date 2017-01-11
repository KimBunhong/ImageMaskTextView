package com.example.user.imagemasktextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.attr.password;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl_image_button;
    EditText ed_id, ed_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_id = (EditText) findViewById(R.id.ed_id);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);

        ed_id.addTextChangedListener(new CustomEditTextPassword(this, ed_id, 0, CustomEditTextPassword.PASSWORD_NORMAL));
    }
}
