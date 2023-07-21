package com.test.chathurangajay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.chathurangajay.db.DbAdapter;
import com.test.chathurangajay.model.UserData;
import com.test.chathurangajay.viewmodel.HomeViewModel;

public class HomeScreenActivity extends AppCompatActivity {

    TextView idTxtView,nameTxtView,emailTxtView,dobTxtView,genderTxtView,companyTxtView,positionTxtView;
    private HomeViewModel userViewModel;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        userViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        dbAdapter = new DbAdapter(this);

        idTxtView = findViewById(R.id.text_view_id);
        nameTxtView = findViewById(R.id.text_view_name);
        emailTxtView = findViewById(R.id.text_view_email);
        dobTxtView = findViewById(R.id.text_view_dob);
        genderTxtView = findViewById(R.id.text_view_gender);
        companyTxtView = findViewById(R.id.text_view_company);
        positionTxtView = findViewById(R.id.text_view_position);


    }

    public void onView(View view) {
        userViewModel.getUserData("ID001",dbAdapter).observe(this, new Observer<UserData>() {
            @Override
            public void onChanged(UserData userData) {
                if(userData != null) {
                    idTxtView.setText(userData.getId());
                    nameTxtView.setText(userData.getName());
                    emailTxtView.setText(userData.getEmail());
                    dobTxtView.setText(userData.getDob());
                    genderTxtView.setText(userData.getGender());
                    companyTxtView.setText(userData.getCompany());
                    positionTxtView.setText(userData.getPosition());

                }

            }
        });
    }
}