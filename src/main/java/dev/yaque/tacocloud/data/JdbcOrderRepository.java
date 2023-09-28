/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yaque.tacocloud.Order;
import dev.yaque.tacocloud.Taco;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yaque
 */
@Repository
public class JdbcOrderRepository implements OrderRepository {
    
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(JdbcOrderRepository.class);
    
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_tacos");
        this.objectMapper = new ObjectMapper();
    }
    
    

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        
        tacos.forEach(t -> saveTacoToOrder(t, orderId));
        return order;
    }
    
    private long saveOrderDetails(Order order){
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        log.info("values: " + values);
        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();
        return orderId;
    }
    
    public void saveTacoToOrder(Taco taco, long orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
    
}
