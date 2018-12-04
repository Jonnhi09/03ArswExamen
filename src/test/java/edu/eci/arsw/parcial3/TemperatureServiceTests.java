/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.parcial3;

import edu.eci.arsw.parcial3.controller.TemperatureApiController;
import edu.eci.arsw.parcial3.model.Converter;
import edu.eci.arsw.parcial3.model.TipoUnidad;
import edu.eci.arsw.parcial3.service.TemperatureService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jonathan Prieto
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemperatureServiceTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TemperatureApiController temperatureApiController;

    @Autowired
    private TemperatureService temperatureService;

    private double numCelcius;
    private double numCelcius2;
    private double numFahrenheit;
    private double numFahrenheit2;

    public TemperatureServiceTests() {
    }

    @Before
    public void setUp() {
        numCelcius = 10d;
        numCelcius2 = 22d;
        numFahrenheit = 32d;
        numFahrenheit2 = 50d;
    }

    /**
     * Pruebas unitarias service. CelsiusToFahrenheit.
     */
    @Test
    public void convertionUnitTestCelsiusToFahrenheit() {
        Assert.assertEquals(" ", 50d, temperatureService.convert(TipoUnidad.CF, numCelcius).getNumConverted());
        Assert.assertEquals(" ", 71.6d, temperatureService.convert(TipoUnidad.CF, numCelcius2).getNumConverted());
    }

    /**
     * Pruebas unitarias service. FahrenheitToCelsius.
     */
    @Test
    public void convertionUnitTestFahrenheitToCelsius() {
        Assert.assertEquals(" ", 0d, temperatureService.convert(TipoUnidad.FC, numFahrenheit).getNumConverted());
        Assert.assertEquals(" ", 10d, temperatureService.convert(TipoUnidad.FC, numFahrenheit2).getNumConverted());
    }

    /**
     * Pruebas unitarias concurrentes Api.
     *
     * @throws Exception
     */
    @Test
    public void convertions() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (double i = 10; i < 10; i += 10) {
            TemperatureServiceRunnable convertions = new TemperatureServiceRunnable("CF", i);
            es.execute(convertions);
        }
        try {
            es.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(TemperatureServiceTests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Clase TemperatureServiceRunnable implementa Runnable. Hace conversiones
     * de manera concurrente consumiendo el Api.
     */
    public class TemperatureServiceRunnable implements Runnable {

        private String unidad;
        private double num;

        public TemperatureServiceRunnable(String unidad, double num) {
            this.unidad = unidad;
            this.num = num;
        }

        @Override
        public void run() {
            temperatureApiController.convert(unidad, num);
        }

    }
}
