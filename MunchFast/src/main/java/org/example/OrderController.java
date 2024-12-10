/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 22cri
 */
public class OrderController {
    private OrderDAO orderDAO;

    public OrderController() {
        orderDAO = new OrderDAO();
    }

    /**
     * Add new order
     *
     * @param order
     */
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    /**
     * Delete an order
     *
     * @param orderId
     * @return
     */
    public boolean deleteOrder(int orderId) {
        return orderDAO.deleteOrder(orderId);
    }

    /**
     * Retrieve an order by its id (Order id)
     *
     * @param orderId
     * @return Order
     */
    public Order getOrderById(int orderId) {
        return orderDAO.getOrdersById(orderId);
    }

    /**
     * Retrieve Order by Customer ID (can retrieve multiple orders)
     *
     * @param customerId
     * @return List<Order>
     */
    public List<Order> getOrdersByCustomer(int customerId) {
        return orderDAO.getOrdersByCustomer(customerId);
    }

    /**
     * Retrieve all orders
     *
     * @return
     */
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

}
