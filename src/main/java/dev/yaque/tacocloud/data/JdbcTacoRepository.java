/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.data;

import dev.yaque.tacocloud.Ingredient;
import dev.yaque.tacocloud.Taco;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yaque
 */
@Repository
public class JdbcTacoRepository implements TacoRepository {

    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(JdbcTacoRepository.class);
    
    private JdbcTemplate jdbc;
    
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long newTacoId = saveTacoInfo(taco);
        taco.setId(newTacoId);
        taco.getIngredients().forEach( i -> saveIngredientToTaco(i, newTacoId));
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreateAt(new Date());
        var pscf = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values ( ?, ?) ",
                Types.VARCHAR,
                Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(taco.getName(),
                        new Timestamp(taco.getCreateAt().getTime())));
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        log.info("performing update");
        jdbc.update(psc, keyHolder);
        log.info(keyHolder.toString());
        Number num =  keyHolder.getKey();
        log.info("result:" + num);
        return num.longValue();
        
    }
    
    private void saveIngredientToTaco(Ingredient i, long tacoId){
        jdbc.update("insert into Taco_Ingredients(taco, ingredient) values( ?, ?)",
                tacoId,
                i.getId());
    }

}
