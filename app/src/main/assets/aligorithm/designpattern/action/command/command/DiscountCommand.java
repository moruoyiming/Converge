package com.algorithm.demo.designpattern.action.command.command;

import com.algorithm.demo.designpattern.action.command.Command;
import com.algorithm.demo.designpattern.action.command.handler.DiscountHandler;

public class DiscountCommand extends Command {

    private DiscountHandler handler = new DiscountHandler();

    @Override
    public String execute() {
        return handler.getDiscounts();
    }
}
