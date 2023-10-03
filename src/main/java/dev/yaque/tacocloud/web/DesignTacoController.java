/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.web;

import dev.yaque.tacocloud.Ingredient;
import dev.yaque.tacocloud.Ingredient.Type;
import dev.yaque.tacocloud.Order;
import dev.yaque.tacocloud.Taco;
import dev.yaque.tacocloud.data.IngredientRepository;
import dev.yaque.tacocloud.data.IngredientRepositoryJPA;
import dev.yaque.tacocloud.data.TacoRepository;
import dev.yaque.tacocloud.data.TacoRepositoryJPA;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author yaque
 */
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    
    private final IngredientRepositoryJPA ingredientRepo;
    private TacoRepositoryJPA designRepo;

    @Autowired
    public DesignTacoController(IngredientRepositoryJPA ingredientRepo, TacoRepositoryJPA designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }
    
    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }
    
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }
    
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values(); 
        for(Type type: types){ 
            model.addAttribute(type.toString().toLowerCase(), 
                    filterByType(ingredients, type)); 
        } 
    }
        
    @GetMapping
    public String showDesignForm(Model model){
        return "design";
    }
    
    @PostMapping
    public String processDesign(
            @Valid Taco design,
            Errors errors,
            @ModelAttribute Order order ) {
        
        log.info("errros="+errors);
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Processing design: " + design);
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";

    }
    
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
    
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
}
