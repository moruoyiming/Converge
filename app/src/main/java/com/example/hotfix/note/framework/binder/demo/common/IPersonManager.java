package com.example.hotfix.note.framework.binder.demo.common;

import android.os.IInterface;
import android.os.RemoteException;


import com.example.hotfix.note.framework.binder.demo.bean.Person;

import java.util.List;

public interface IPersonManager extends IInterface {

    void addPerson(Person person) throws RemoteException;

    List<Person> getPersonList() throws RemoteException;
}
