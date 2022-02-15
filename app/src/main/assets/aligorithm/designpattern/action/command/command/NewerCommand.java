package com.algorithm.demo.designpattern.action.command.command;

import com.algorithm.demo.designpattern.action.command.Command;
import com.algorithm.demo.designpattern.action.command.handler.NewerHandler;

public class NewerCommand extends Command {
    private NewerHandler handler = new NewerHandler();

    @Override
    public String execute() {
        return handler.getNewers();
    }
}
