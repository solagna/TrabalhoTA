package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medicamento;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleMedicamento")
@SessionScoped
public class ControleMedicamento implements Serializable {

    @EJB
    private MedicamentoDAO<Medicamento> dao;
    private Medicamento objeto;


    public ControleMedicamento() {
        
    }

    public String listar() {

        return "/privado/medicamento/listar?faces-redirect=true";
    }

   

    public void novo() {
        objeto = new Medicamento();

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

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);

        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }

    }

    public void remover(Integer id) {
        try {
            //objeto = dao.getObjectById(id);
            dao.remove(objeto);
            UtilMensagem.mensagemInformacao("Objeto removido com sucesso!");

        } catch (Exception e) {

            UtilMensagem.mensagemErro("Erro ao remover o objeto " + e.getMessage());
        }
    }

    public MedicamentoDAO getDao() {
        return dao;
    }

    public void setDao(MedicamentoDAO dao) {
        this.dao = dao;
    }

    public Medicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(Medicamento objeto) {
        this.objeto = objeto;
    }

    
}
