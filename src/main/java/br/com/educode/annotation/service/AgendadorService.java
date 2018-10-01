package br.com.educode.annotation.service;

import java.util.ArrayList;
import java.util.List;
import br.com.educode.annotation.annotation.AnnotationProcessor;
import br.com.educode.annotation.annotation.OperationEnum;
import br.com.educode.annotation.model.Agendador;

/**
 *
 * @author eduardo
 */
public class AgendadorService {

    public Agendador insert(Agendador agendador) throws Exception {
        AnnotationProcessor.processor(agendador, OperationEnum.INSERT);
        this.getAgendadores().add(agendador);
        return agendador;
    }

    public Agendador update(Agendador agendador) throws Exception {
        AnnotationProcessor.processor(agendador, OperationEnum.UPDATE);
        this.getAgendadores().remove(agendador);
        this.getAgendadores().add(agendador);
        return agendador;
    }

    public Agendador delete(Agendador agendador) throws Exception {
        AnnotationProcessor.processor(agendador, OperationEnum.DELETE);
        this.getAgendadores().remove(agendador);
        return agendador;
    }

    public List<Agendador> getAgendadores() {
        return this.agendadores;
    }

    private List<Agendador> agendadores = new ArrayList<Agendador>();
}
