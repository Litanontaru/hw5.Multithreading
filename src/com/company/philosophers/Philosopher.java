package com.company.philosophers;

public class Philosopher implements Runnable {

    private String name;
    private int eatTime;
    private int thinkTime;
    private final Forks forks;
    private int forkIndex1;
    private int forkIndex2;

    public Philosopher(String name, int eatTime, int thinkTime, Forks forks, int forkIndex1, int forkIndex2) {
        this.name=name;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
        this.forks = forks;
        this.forkIndex1 = forkIndex1;
        this.forkIndex2 = forkIndex2;
    }

    public void eat() throws InterruptedException {
        System.out.println(name + " eats...");
        Thread.sleep(eatTime);
    }

    public void think() throws InterruptedException {
        System.out.println(name + " thinks...");
        Thread.sleep(thinkTime);
    }

    @Override
    public void run() {
        while (true) {
            boolean forksFree = false;
            synchronized (forks) {
                if (forks.isForksPairFree(forkIndex1, forkIndex2)) {
                    forks.takeForksPair(forkIndex1, forkIndex2);
                    forksFree = true;
                }
            }
            try {
                if (forksFree) {
                    eat();
                    forks.releaseForksPair(forkIndex1, forkIndex2);
                }
                think();
            } catch (InterruptedException e){
                System.out.println("Someone interrupted philospher!!! - " + e.getMessage());
            }
        }
    }
}