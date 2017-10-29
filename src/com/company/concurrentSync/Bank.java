package com.company.concurrentSync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    //В будущем стоит изучить необходимость использования volatile для подобных переменных
    //В реальном продакшене без volatile это не будет работать
    private int moneyAmount;

    //поле должно быть final
    //Рекомендуется все final поля (aka. конфигурацию-сервисы) объявлять выше не final,
    //отделяя таким образом поля состояния от полей от конфигурации
    private Lock lock = new ReentrantLock();

    public Bank(int initialMoney) {
        moneyAmount = initialMoney;
    }

    //synchronized здесь не нужен.
    //Этот метод нарушает принцип "Open/Closed" из SOLID
    public synchronized Lock getLock() {
        return lock;
    }

    public void getMoney(int money) {
        //Нарушение code style: несколько операторов на одной строке + нужно добавить {}
        if (!hasMoney(money)) throw new NoMoneyException("Bank has no money: " + money);
        //Нарушение code style: несколько операторов на одной строке + нужно добавить {}
        else moneyAmount -= money;
        //Нарушение JCC - строка более 120 символов
        System.out.format("thread '%s' got %d money. (money left in bank: %d)%n", Thread.currentThread().getName(), money, moneyAmount);
    }

    public boolean hasMoney(int amount) {
        return moneyAmount >= amount;
    }

    //Этот класс либо должен быть объявлен как static либо вынесен ввверх
    public class NoMoneyException extends RuntimeException {
        public NoMoneyException() {
            super();
        }

        public NoMoneyException(String s) {
            super(s);
        }
    }
}
