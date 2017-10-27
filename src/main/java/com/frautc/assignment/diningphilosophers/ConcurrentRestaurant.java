package com.frautc.assignment.diningphilosophers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ConcurrentRestaurant {

    public static void main(String[] args) throws Exception {

        Diner[] diners = new Diner[5];
        Object[] forks = new Object[diners.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        Logger logger = Logger.getLogger(ConcurrentRestaurant.class);
        BasicConfigurator.configure();

        for (int i = 0; i < diners.length; i++) {

            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == diners.length - 1) {
                diners[i] = new Diner(rightFork, leftFork); // The last philosopher picks up the right fork first
            } else {
                diners[i] = new Diner(leftFork, rightFork);
            }

            Thread t = new Thread(diners[i], "Diner" + (i + 1));
            t.start();
        }
    }
}
