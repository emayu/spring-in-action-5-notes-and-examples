/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  yaque
 * Created: Sep 27, 2023
 */
delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type) 
                values ('FLTO', 'Flour Tortilla', '0');
insert into Ingredient (id, name, type) 
                values ('COTO', 'Corn Tortilla', '0');
-- insert into Ingredient (id, name, type) 
--                 values ('CHCW', 'Chesse crust wrap', '0');
insert into Ingredient (id, name, type) 
                values ('GRBF', 'Ground Beef', '1');
insert into Ingredient (id, name, type) 
                values ('CARN', 'Carnitas', '1');
insert into Ingredient (id, name, type) 
                values ('TMTO', 'Diced Tomatoes', '2');
insert into Ingredient (id, name, type) 
                values ('LETC', 'Lettuce', '2');
insert into Ingredient (id, name, type) 
                values ('CHED', 'Cheddar', '3');
insert into Ingredient (id, name, type) 
                values ('JACK', 'Monterrey Jack', '3');
insert into Ingredient (id, name, type) 
                values ('SLSA', 'Salsa', '4');
insert into Ingredient (id, name, type) 
                values ('SRCR', 'Sour Cream', '4');
  
insert into Users (id, city, fullname, password, phone_number, state, street, username, zip)
    values('1', 'city', 'test user name', '8d8b2c209e8b76d752faec2e41009f80b9bda99236b8091044c3b08ebae818e5edc789ad3cdbba03', 'phone', 'state', 'street', 'test', 'zip zip');