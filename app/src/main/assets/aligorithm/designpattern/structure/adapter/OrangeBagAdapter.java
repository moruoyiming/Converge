package com.algorithm.demo.designpattern.structure.adapter;

import com.algorithm.demo.designpattern.entity.bag.AppleBag;
import com.algorithm.demo.designpattern.entity.bag.OrangeBag;

/**
 * 桔子包装适配器
 */
public class OrangeBagAdapter extends OrangeBag {
    private AppleBag appleBag;

    public OrangeBagAdapter(AppleBag appleBag){
        this.appleBag = appleBag;
    }

    @Override
    public void pack() {
        appleBag.pack();
    }
}
