package com.mobiledoctors24.rxaffectsui.strategypattern;

public class DuckSimpleApp {
    public static void main(String[] args) {
        Duck dullbackDuck =new DullbackDuck();
        ((DullbackDuck) dullbackDuck).performFly();
        dullbackDuck.setFlyBehavior(new  FlyNoWay());
        ((DullbackDuck) dullbackDuck).performFly();
    }
}
