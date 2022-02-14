package com.example.converge.note.androidadvanced.jetpack.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
    }

    public void insert(Student... students) {
        studentRepository.insert(students);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public void update(Student student) {
        studentRepository.update(student);
    }

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public LiveData<List<Student>> getLiveDataAllStudent() {
        return studentRepository.getLiveDataAllStudent();
    }
}
