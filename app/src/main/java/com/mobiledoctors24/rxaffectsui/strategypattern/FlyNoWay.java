package com.mobiledoctors24.rxaffectsui.strategypattern;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly with wings");
    }
}
