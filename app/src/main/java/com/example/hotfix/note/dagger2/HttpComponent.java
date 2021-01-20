package com.example.hotfix.note.dagger2;

import dagger.Component;

@Component( modules= {HttpModule.class})
public interface HttpComponent {

    void injectFirstActivity(FirstActivity firstActivity);

    void injectSecondActivity(SecondActivity secondActivity);

}
