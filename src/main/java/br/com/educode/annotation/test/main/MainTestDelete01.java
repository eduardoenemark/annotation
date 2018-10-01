package br.com.educode.annotation.test.main;

import java.time.LocalDate;
import br.com.educode.annotation.model.Agendador;
import br.com.educode.annotation.service.AgendadorService;

/**
 *
 * @author eduardo
 */
public class MainTestDelete01 {

    public static void main(String[] args) throws Exception {

        Agendador agendador = new Agendador("task99", LocalDate.of(1980, 2, 1));

        AgendadorService agendadorService = new AgendadorService();
        agendadorService.delete(agendador);

        System.out.println(agendador);
    }

}
