package com.example.converge.note.androidheight.jetpack.room;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.converge.R;
import com.example.converge.databinding.ActivityViewmodelBinding;

import java.util.List;

public class ViewModelActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private ActivityViewmodelBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewmodel);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                binding.lsitview.setAdapter(new GoodsAdapter(ViewModelActivity.this, students));
            }
        });

        for (int i = 0; i < 50; i++) {
            studentViewModel.insert(new Student("jett", "123", i));
        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    studentViewModel.update(new Student(6, "jett" + i, "123", i));
                }
            }
        }.start();
    }

}
