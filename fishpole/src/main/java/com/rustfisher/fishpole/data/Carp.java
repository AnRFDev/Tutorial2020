package com.rustfisher.fishpole.data;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * 鲤鱼
 * 2020-6-19
 */
public class Carp {
    private String name;
    private int age;
    private float weight;
    private boolean alive;

    public Carp() {
    }

    public Carp(String name, int age, float weight, boolean alive) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.CHINA, "Carp {name: %s, age: %d, weight: %f, alive: %b}",
                name, age, weight, alive);
    }
}
