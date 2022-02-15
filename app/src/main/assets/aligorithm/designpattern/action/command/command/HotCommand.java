package com.algorithm.demo.designpattern.action.command.command;

import com.algorithm.demo.designpattern.action.command.Command;
import com.algorithm.demo.designpattern.action.command.handler.HotHandler;

public class HotCommand extends Command {
    private HotHandler handler = new HotHandler();

    @Override
    public String execute() {
        return handler.getHots();
    }
}
