package org.example;

import javax.swing.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DriverTask implements Runnable {

    private static final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(1);
    private static boolean isAvailable = true;

    public static void takeOrder(Order order) {
        try {
            if (isAvailable) {
                orderQueue.put(order); //place order in queue
                isAvailable = false;
                JOptionPane.showMessageDialog(null, "Order " + order.getOrderId() + " is being processed by Driver.");

            } else {
                JOptionPane.showMessageDialog(null, "Driver is currently busy.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                //take the next order from queue/if empty blocks
                Order order = orderQueue.take(); //blocks until available
                processOrder(order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processOrder(Order order) {
        try {
            JOptionPane.showMessageDialog(null, "Driver delivering " + order.getOrderId());
            Thread.sleep(60000);
            JOptionPane.showMessageDialog(null, "Driver delivered " + order.getOrderId());
            isAvailable = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
