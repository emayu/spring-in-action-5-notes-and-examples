/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.yaque.tacocloud.data;

import dev.yaque.tacocloud.Order;

/**
 *
 * @author yaque
 */
public interface OrderRepository {
    Order save(Order order);
}
