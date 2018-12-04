/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parcial3.controller;

import edu.eci.arsw.parcial3.model.TipoUnidad;
import edu.eci.arsw.parcial3.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jonathna Prieto
 */
@RestController
@RequestMapping("/temperature")
public class TemperatureApiController {

    @Autowired
    private TemperatureService ts;

    /**
     *
     * @param unidad
     * @param num
     * @return
     */
    @GetMapping("/{unidad}/{num}")
    public ResponseEntity<?> convert(@PathVariable("unidad") String unidad, @PathVariable("num") double num) {
        try {
            if ("cf".equals(unidad.toLowerCase())) {
                return new ResponseEntity<>(ts.convert(TipoUnidad.CF, num), HttpStatus.ACCEPTED);
            } else if ("fc".equals(unidad.toLowerCase())) {
                return new ResponseEntity<>(ts.convert(TipoUnidad.FC, num), HttpStatus.ACCEPTED);
            } else {
                throw new Exception("Unidades de conversión invalidas.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("La unidad" + unidad, HttpStatus.NOT_FOUND);
        }
    }
}
