package com.example.converge.note.androidheight.jetpack.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class PersonDataSourceFactory extends DataSource.Factory<Integer, Person> {

    @NonNull
    @Override
    public DataSource<Integer, Person> create() {
        CustomPageDataSource pageDataSource = new CustomPageDataSource(new DataRepository());
        PersonDataSource personDataSource = new PersonDataSource();
        CustomItemDataSource customItemDataSource = new CustomItemDataSource(new DataRepository());
        return pageDataSource;
    }
}
