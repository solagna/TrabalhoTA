/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Telefone;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Emanuele
 */
@ManagedBean(name = "controlePaciente")
@ViewScoped
public class ControlePaciente implements Serializable {

    @EJB
    private PacienteDAO<Paciente> dao;
    private Paciente objeto;

    private Telefone telefone;
    private Boolean novoTelefone;

    public ControlePaciente() {
    }

    public String listar() {

        return "/privado/paciente/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Paciente();

    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);

            } else {
                dao.merge(objeto);
            }
            UtilMensagem.mensagemInformacao("Objeto persistido com sucesso");

        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao persistir" + e.getMessage());
        }

    }

    public void novoTelefone() {
        this.telefone = new Telefone();
        this.novoTelefone = true;
    }

    public void alteraTelefone(int index) {
        this.telefone = objeto.getTelefones().get(index);
        this.novoTelefone = false;
    }

    public void salvarTelefone() {
        if (novoTelefone) {
            objeto.adicionarTelefone(telefone);
        }
        UtilMensagem.mensagemInformacao("Operaçao realizada com sucesso!");
    }

    public void removerTelefone(int index) {
        objeto.removerTelefone(index);
        UtilMensagem.mensagemInformacao("Operação realizada com sucesso!");
    }

    public void editar(Integer id) {
        try {
            objeto = getDao().getObjectById(id);

        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }

    }

    public void remover(Integer id) {
        try {
            objeto = getDao().getObjectById(id);
            dao.remove(objeto);
            UtilMensagem.mensagemInformacao("Objeto removido com sucesso!");

        } catch (Exception e) {

            UtilMensagem.mensagemErro("Erro ao remover o objeto " + e.getMessage());
        }
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

    public PacienteDAO<Paciente> getDao() {
        return dao;
    }

    public void setDao(PacienteDAO<Paciente> dao) {
        this.dao = dao;
    }

}
