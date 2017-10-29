package com.company.philosophers;

public class Main {

    public static void main(String[] args) {
        int philosophersCount = 5;
        int eatTime = 500;
        int thinkTime = 500;
        Philosopher[] philosophers = new Philosopher[philosophersCount];
        Forks forks = new Forks(philosophersCount);

        philosophers[0] = new Philosopher("Philosopher#0", eatTime, thinkTime, forks, 0, philosophersCount - 1);
        for (int i = 1; i < philosophersCount; i++) {
            //не хватает пробелов между операторами
            //Логическая ошибка: Последний и первый философ не имеют общей вилки
            philosophers[i] = new Philosopher("Philosopher#"+i,eatTime, thinkTime, forks, i, i - 1);
            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }

}
