package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.respireapp.R;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton backButton=(ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.this.finish();
            }
        });

        Button usernameButton=(Button) findViewById(R.id.usernameButton);
        usernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeUsernameActivity.class);
                startActivity(intent);
            }
        });

        Button passwordButton=(Button) findViewById(R.id.passwordButton);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        Button phoneButton=(Button) findViewById(R.id.phoneButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangePhoneActivity.class);
                startActivity(intent);
            }
        });

        Button emailButton=(Button) findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeEmailActivity.class);
                startActivity(intent);
            }
        });

        Button sexButton=(Button) findViewById(R.id.sexButton);
        sexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeSexActivity.class);
                startActivity(intent);
            }
        });

        Button cityButton=(Button) findViewById(R.id.cityButton);
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeCityActivity.class);
                startActivity(intent);
            }
        });
    }
}
