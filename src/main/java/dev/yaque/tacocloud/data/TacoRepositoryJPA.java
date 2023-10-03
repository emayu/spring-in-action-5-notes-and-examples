/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.yaque.tacocloud.data;

import dev.yaque.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author yaque
 */
public interface TacoRepositoryJPA extends CrudRepository<Taco, Long>{
    
}
