package com.test.chathurangajay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.test.chathurangajay.db.DbAdapter;
import com.test.chathurangajay.model.UserRespone;
import com.test.chathurangajay.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private MainViewModel mainViewModel;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.edit_txt_username);
        editTextPassword = findViewById(R.id.edit_txt_password);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        dbAdapter = new DbAdapter(this);
    }

    public void onSubmit(View view) {
        final String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        mainViewModel.loginUser(username,password,dbAdapter).observe(this, new Observer<UserRespone>() {
            @Override
            public void onChanged(UserRespone userRespone) {

                System.out.println(userRespone.getRes_code());
                if (Integer.parseInt(userRespone.getRes_code().toString()) == 0) {
                    System.out.println(userRespone.getUser_data().getCompany());

                    Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, userRespone.getRes_desc(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}