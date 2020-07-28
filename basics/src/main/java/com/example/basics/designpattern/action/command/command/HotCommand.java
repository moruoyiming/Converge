package com.example.basics.designpattern.action.command.command;

import com.example.basics.designpattern.action.command.Command;
import com.example.basics.designpattern.action.command.handler.HotHandler;

public class HotCommand extends Command {
    private HotHandler handler = new HotHandler();

    @Override
    public String execute() {
        return handler.getHots();
    }
}
