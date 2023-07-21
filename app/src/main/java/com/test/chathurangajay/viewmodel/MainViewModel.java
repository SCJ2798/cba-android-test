package com.test.chathurangajay.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.test.chathurangajay.db.DbAdapter;
import com.test.chathurangajay.model.LoginRequest;
import com.test.chathurangajay.model.UserRespone;
import com.test.chathurangajay.service.ApiService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {

    private MutableLiveData<UserRespone> userLiveData;
    private DbAdapter adapter;
    public LiveData<UserRespone> loginUser(String username,String password,DbAdapter adapter){
       userLiveData = new MutableLiveData<UserRespone>();
       this.adapter = adapter;
        fetchData(username,password);
        return  userLiveData;
    }

    private void fetchData(final String username, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://123.231.9.43:3999")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        // Convert login request to request body
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),new Gson().toJson(new LoginRequest(username,password)));
        Call<UserRespone> call = apiService.login(requestBody);

        call.enqueue(new Callback<UserRespone>() {
            @Override
            public void onResponse(Call<UserRespone> call, Response<UserRespone> response) {

                if(response.isSuccessful()){
                    Log.e("FETCH USER DATA",response.body().getUser_data().getCompany());
                    userLiveData.postValue(response.body());
                    adapter.insertData(response.body().getUser_data());

                }else{
                    Log.e("FETCH USER DATA",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserRespone> call, Throwable t) {
                Log.e("FETCH USER DATA__",t.toString());
                userLiveData.postValue(new UserRespone("-1","Something went wrong",null));
            }
        });

        }

}
