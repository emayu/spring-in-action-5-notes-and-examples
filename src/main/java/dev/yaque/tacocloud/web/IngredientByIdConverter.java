package dev.yaque.tacocloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import dev.yaque.tacocloud.Ingredient;
import dev.yaque.tacocloud.data.IngredientRepository;
import dev.yaque.tacocloud.data.IngredientRepositoryJPA;
import java.util.Optional;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yaque
 */
@Component
public class IngredientByIdConverter implements  Converter<String, Ingredient>{
    
    private IngredientRepositoryJPA ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepositoryJPA ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        return optionalIngredient.isPresent() ? optionalIngredient.get() : null;
    }
    
    
}
