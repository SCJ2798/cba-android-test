package com.test.chathurangajay.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.chathurangajay.db.DbAdapter;
import com.test.chathurangajay.model.UserData;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<UserData> userLiveData;
    private DbAdapter adapter;

    public LiveData<UserData> getUserData(String id, DbAdapter adapter){
        try{
            userLiveData = new MutableLiveData<UserData>();
            this.adapter = adapter;
            ArrayList<UserData> userDatas = adapter.getDataById(id);
            if(userDatas.size() <= 0) throw new Exception("");
            userLiveData.setValue(userDatas.get(0));

        }catch (Exception e){
            userLiveData.setValue(null);
        }

        return  userLiveData;
    }

}
