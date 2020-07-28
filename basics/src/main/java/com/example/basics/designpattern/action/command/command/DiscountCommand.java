package com.example.basics.designpattern.action.command.command;

import com.example.basics.designpattern.action.command.Command;
import com.example.basics.designpattern.action.command.handler.DiscountHandler;

public class DiscountCommand extends Command {

    private DiscountHandler handler = new DiscountHandler();

    @Override
    public String execute() {
        return handler.getDiscounts();
    }
}
