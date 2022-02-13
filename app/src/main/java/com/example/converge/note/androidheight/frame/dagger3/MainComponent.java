package com.example.converge.note.androidheight.frame.dagger3;

import dagger.Component;

@Component(modules = { MainModule.class})
public interface MainComponent {

    TestSubComponent getTestSubComponent();

}
