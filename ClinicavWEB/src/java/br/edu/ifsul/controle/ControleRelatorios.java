/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Emanuele
 */
@ManagedBean(name = "controleRelatorios")
@SessionScoped
public class ControleRelatorios implements Serializable {

    @EJB
    private MedicamentoDAO<Medicamento> daoMedicamento;
    @EJB
    private ConsultaDAO<Consulta> daoConsulta;
    
    public ControleRelatorios() {
    }

    public void imprimeRelatorioMedicamentos() {
        HashMap parametros = new HashMap();

        UtilRelatorios.imprimeRelatorio("Medicamentos",
                parametros, daoMedicamento.getListaTodos());
    }
     public void imprimeRelatorioConsultas() {
        HashMap parametros = new HashMap();

        UtilRelatorios.imprimeRelatorio("RelatorioMedicamentosJAVABEANS",
                parametros, daoConsulta.getListaTodos());
    }
    

    public MedicamentoDAO<Medicamento> getDaoMedicamento() {
        return daoMedicamento;
    }

    public void setDaoMedicamento(MedicamentoDAO<Medicamento> daoMedicamento) {
        this.daoMedicamento = daoMedicamento;
    }

  
}
