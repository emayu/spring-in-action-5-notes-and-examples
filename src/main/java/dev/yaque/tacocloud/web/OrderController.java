/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.yaque.tacocloud.Order;
import dev.yaque.tacocloud.data.OrderRepository;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;




/**
 *
 * @author yaque
 */
@Controller 
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    
    @GetMapping("/current")
    public String orderForm(Model model){
        if(model.getAttribute("order") == null){
            return "redirect:/design";
        }
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
            SessionStatus sessionStatus) {
        log.info("errros="+errors);
        if(errors.hasErrors()){
            return "orderForm";
        }
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
