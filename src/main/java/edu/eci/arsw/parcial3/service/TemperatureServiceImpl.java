/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parcial3.service;

import edu.eci.arsw.parcial3.model.Converter;
import edu.eci.arsw.parcial3.model.TipoUnidad;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Prieto
 */
@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Override
    public Converter convert(TipoUnidad tipoUnidad, double num) {
        Converter converter = new Converter(tipoUnidad, num);
        converter.doConvert();
        return converter;
    }

}
