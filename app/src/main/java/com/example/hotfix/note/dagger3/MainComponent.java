package com.example.hotfix.note.dagger3;

import dagger.Component;

@Component(modules = { MainModule.class})
public interface MainComponent {

    TestSubComponent getTestSubComponent();

}
