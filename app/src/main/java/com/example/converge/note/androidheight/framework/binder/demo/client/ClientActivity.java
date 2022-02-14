package com.example.converge.note.androidheight.framework.binder.demo.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.converge.R;
import com.example.converge.note.androidheight.framework.binder.demo.bean.Person;
import com.example.converge.note.androidheight.framework.binder.demo.common.IPersonManager;
import com.example.converge.note.androidheight.framework.binder.demo.common.Stub;
import com.example.converge.note.androidheight.framework.binder.demo.server.RemoteService;

import java.util.List;

public class ClientActivity extends AppCompatActivity {

    private IPersonManager iPersonManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aidl);

        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.enjoy.binder");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.e("leo", "------------onClick:" + Thread.currentThread());
                    iPersonManager.addPerson(new Person("leo", 3));
                    List<Person> persons = iPersonManager.getPersonList();
                    Log.e("leo", persons.toString() + "," + Thread.currentThread());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("leo", "onServiceConnected: success");
            iPersonManager = Stub.asInterface(service);// proxy
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("leo", "onServiceDisconnected: success");
            iPersonManager = null;
        }
    };
}
