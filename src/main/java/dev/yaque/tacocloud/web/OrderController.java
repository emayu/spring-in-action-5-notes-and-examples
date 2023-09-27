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



/**
 *
 * @author yaque
 */
@Controller 
@RequestMapping("/orders")
public class OrderController {
    
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    
    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }
    
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        log.info("errros="+errors);
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted:" + order);
        return "redirect:/";
    }
}
