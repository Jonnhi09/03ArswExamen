/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parcial3.service;

import edu.eci.arsw.parcial3.model.Converter;
import edu.eci.arsw.parcial3.model.TipoUnidad;

/**
 *
 * @author 2103258
 */
public interface TemperatureService {

    public Converter convert(TipoUnidad tipoUnidad, double num);
}
