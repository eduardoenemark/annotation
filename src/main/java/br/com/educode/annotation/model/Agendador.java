package br.com.educode.annotation.model;

import java.time.LocalDate;
import br.com.educode.annotation.annotation.DateNotLessThanToday;
import br.com.educode.annotation.annotation.UpperCase;
import br.com.educode.annotation.annotation.RandomNumber;

/**
 *
 * @author eduardo
 */
public class Agendador {

    public Agendador() {
        this(null, null);
    }

    @SuppressWarnings("")
    public Agendador(String nome, LocalDate dataExecucao) {
        this.setNome(nome);
        this.setDataExecucao(dataExecucao);
    }

    @RandomNumber(bound = 999, update = false, delete = false)
    private Long id;

    @UpperCase(delete = false)
    private String nome;

    @DateNotLessThanToday(throwException = true, delete = false)
    private LocalDate dataExecucao;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[ id ]: ");
        builder.append(this.getId());
        builder.append("\n");

        builder.append("[ nome ]: ");
        builder.append(this.getNome());
        builder.append("\n");

        builder.append("[ dataExecucao ]: ");
        builder.append(this.getDataExecucao());
        builder.append("\n");

        return builder.toString();
    }

    @Override
    public int hashCode() {
        return (int) (this.getId() / 1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataExecucao() {
        return this.dataExecucao;
    }

    public void setDataExecucao(LocalDate dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

}
