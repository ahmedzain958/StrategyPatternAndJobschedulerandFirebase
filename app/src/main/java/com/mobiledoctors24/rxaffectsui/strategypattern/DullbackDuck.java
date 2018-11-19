package com.mobiledoctors24.rxaffectsui.strategypattern;

public class DullbackDuck extends Duck {
    public DullbackDuck() {
        setFlyBehavior(new FlyWithWings());
    }

    void performFly() {
        flyBehavior.fly();
    }
}
