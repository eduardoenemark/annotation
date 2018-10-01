/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.educode.annotation.service;

import br.com.educode.annotation.annotation.AnnotationException;
import br.com.educode.annotation.model.Agendador;
import static java.lang.System.out;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author u843433
 */
public class AgendadorServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public AgendadorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class AgendadorService.
     */
    @Test
    public void testInsertDataInvalida() throws Exception {
        thrown.expect(AnnotationException.class);

        Agendador agendador = new Agendador("task02", LocalDate.of(1980, 2, 1));
        AgendadorService agendadorService = new AgendadorService();
        agendadorService.insert(agendador);
    }

    /**
     * Test of insert method, of class AgendadorService.
     */
    @Test
    public void testDelete() throws Exception {
        thrown.expect(AnnotationException.class);

        Agendador agendador = new Agendador("task02", LocalDate.of(1980, 2, 1));
        AgendadorService agendadorService = new AgendadorService();
        agendadorService.insert(agendador);
    }
}
