/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parcial3.model;

/**
 *
 * @author Jonathan Prieto
 */
public class Converter {

    private double numBase;
    private double numConverted;
    private TipoUnidad unidad;

    public Converter() {
    }

    public Converter(TipoUnidad unidad, double numConverter) {
        this.unidad = unidad;
        this.numBase = numConverter;
    }

    public void doConvert() {
        if (unidad.equals(TipoUnidad.CF)) {
            numConverted = (numBase * 9 / 5) + 32;
        } else if (unidad.equals(TipoUnidad.FC)) {
            numConverted = (numBase - 32) * 5 / 9;
        }
    }

    public double getNumBase() {
        return numBase;
    }

    public double getNumConverted() {
        return numConverted;
    }

    public TipoUnidad getUnidad() {
        return unidad;
    }

    @Override
    public String toString() {
        return "Converter{" + "Número base=" + numBase + ", Número convertido=" + numConverted + ", Unidad=" + unidad + '}';
    }

}
