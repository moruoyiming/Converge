package com.example.hotfix.note.thread.theory.aqs.templatepattern;

/**
 * 类说明：抽象蛋糕模型
 */
public abstract class AbstractCake {
    protected abstract void shape();/*造型*/
    protected abstract void apply();/*涂抹*/
    protected abstract void brake();/*烤面包*/

    /*做个蛋糕 */
    public final void run(){
        this.shape();
        this.apply();
        this.brake();
    }

}
