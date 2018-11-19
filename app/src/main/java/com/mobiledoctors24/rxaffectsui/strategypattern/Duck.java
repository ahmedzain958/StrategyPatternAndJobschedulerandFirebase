package com.mobiledoctors24.rxaffectsui.strategypattern;

public abstract class Duck {
    FlyBehavior flyBehavior;

    public Duck() {
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}
