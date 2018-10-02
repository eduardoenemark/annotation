package br.com.educode.annotation.service;

import br.com.educode.annotation.annotation.AnnotationProcessor;
import br.com.educode.annotation.annotation.OperationEnum;
import br.com.educode.annotation.annotation.logic.AnnotationLogic;
import br.com.educode.annotation.annotation.logic.impl.DateNotLessThanTodayLogic;
import br.com.educode.annotation.annotation.logic.impl.RandomNumberLogic;
import java.util.ArrayList;
import java.util.List;
import br.com.educode.annotation.model.Agendador;

/**
 *
 * @author eduardo
 */
public class AgendadorService {

    private AnnotationProcessor annotationProcessor;

    public AgendadorService() {
        AnnotationLogic[] annotationLogics = new AnnotationLogic[] {
            new DateNotLessThanTodayLogic(),
            new RandomNumberLogic()
        };
        annotationProcessor = new AnnotationProcessor(annotationLogics);
    }

    public Agendador insert(Agendador agendador) throws Exception {
        annotationProcessor.processor(agendador, OperationEnum.INSERT);
        this.getAgendadores().add(agendador);
        return agendador;
    }

    public Agendador update(Agendador agendador) throws Exception {
        annotationProcessor.processor(agendador, OperationEnum.UPDATE);
        this.getAgendadores().remove(agendador);
        this.getAgendadores().add(agendador);
        return agendador;
    }

    public Agendador delete(Agendador agendador) throws Exception {
        annotationProcessor.processor(agendador, OperationEnum.DELETE);
        this.getAgendadores().remove(agendador);
        return agendador;
    }

    public List<Agendador> getAgendadores() {
        return this.agendadores;
    }

    private List<Agendador> agendadores = new ArrayList<Agendador>();
}
