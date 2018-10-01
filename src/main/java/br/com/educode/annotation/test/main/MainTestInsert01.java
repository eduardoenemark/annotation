package br.com.educode.annotation.test.main;

import java.time.LocalDate;
import br.com.educode.annotation.model.Agendador;
import br.com.educode.annotation.service.AgendadorService;

/**
 *
 * @author eduardo
 */
public class MainTestInsert01 {

    public static void main(String[] args) throws Exception {

        Agendador agendador = new Agendador("task01", LocalDate.of(2021, 10, 1));

        AgendadorService agendadorService = new AgendadorService();
        agendadorService.insert(agendador);

        System.out.println(agendador);

    }

}
