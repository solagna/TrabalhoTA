/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medicamento;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Emanuele
 */
@ManagedBean(name = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {

    @EJB
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    @EJB
    private PacienteDAO daoPaciente;
    @EJB
    private MedicoDAO daoMedico;
    @EJB
    private MedicamentoDAO daoMedicamento;
    private Medicamento medicamento;
   
    public String listar() {
        return "/privado/consulta/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Consulta();
    }

     public void adicionarMedicamento() {
        if (medicamento != null) {
            if (!objeto.getReceita().contains(medicamento)) {
                objeto.getReceita().add(medicamento);
                UtilMensagem.mensagemInformacao("Medicamento adicionado com sucesso!");
            } else {
                UtilMensagem.mensagemErro("Esse medicamento já existente na lista!");
            }
        }
    }

    public void removerMedicamento(int idx) {
        Medicamento m = objeto.getReceita().get(idx);
        objeto.getReceita().remove(m);
        UtilMensagem.mensagemInformacao("Medicamento removido");
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);

            }
            UtilMensagem.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            UtilMensagem.mensagemErro(e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);

        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMensagem.mensagemInformacao("Objeto removido com sucesso!");

        } catch (Exception e) {

            UtilMensagem.mensagemErro("Erro ao remover o objeto " + e.getMessage());
        }
    }

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public PacienteDAO getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public MedicoDAO getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO daoMedico) {
        this.daoMedico = daoMedico;
    }

    public MedicamentoDAO getDaoMedicamento() {
        return daoMedicamento;
    }

    public void setDaoMedicamento(MedicamentoDAO daoMedicamento) {
        this.daoMedicamento = daoMedicamento;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

}
