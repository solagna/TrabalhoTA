package br.edu.ifsul.controle;

import br.edu.ifsul.dao.VendaDAO;
import br.edu.ifsul.dao.MedicamentoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;
import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleVenda")
@ViewScoped
public class ControleVenda implements Serializable {

    @EJB
    private VendaDAO<Venda> dao;
    private Venda objeto;

    @EJB
    private MedicamentoDAO<Medicamento> daoMedicamento;
    @EJB
    private PacienteDAO daoPaciente;

    private VendaItens item;
    private boolean novoItem = false;

    public ControleVenda() {
    }

    public void novoItem() {
        item = new VendaItens();
        novoItem = true;
    }

    public void alterarItem(int index) {
        item = objeto.getItens().get(index);
        novoItem = false;
    }

    public void salvarItem() {
        if (novoItem) {
            objeto.adicionarItem(item);
        }
        UtilMensagem.mensagemInformacao("Operação realizada cm sucesso.");
    }

    public void removerItem(int index) {
        objeto.removerItem(index);
        UtilMensagem.mensagemInformacao("Item removido com sucesso.");
    }

    public void calculaValorTotalItem() {
        if (item.getQuantidade() != null) {
            objeto.setValorTotal(item.getTotal() * item.getQuantidade());
        }
    }

    public String listar() {
        return "/privado/venda/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Venda();
        objeto.setData(Calendar.getInstance());
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                getDao().persist(objeto);
            } else {
                getDao().merge(objeto);
            }
            UtilMensagem.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro ao recuperar obejto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
              objeto = dao.getObjectById(id);
            getDao().remove(objeto);
            UtilMensagem.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            UtilMensagem.mensagemErro("Erro a remover objeto: " + e.getMessage());
        }
    }
 public void atualizaValorUnitarioItem() {
        if (item != null) {
            if (item.getMedicamento()!= null) {
                item.setTotal(item.getMedicamento().getPreco());
            }
        }
    }
    public VendaDAO getDao() {
        return dao;
    }

    public void setDao(VendaDAO dao) {
        this.dao = dao;
    }

    public Venda getObjeto() {
        return objeto;
    }

    public void setObjeto(Venda objeto) {
        this.objeto = objeto;
    }

    public MedicamentoDAO<Medicamento> getDaoMedicamento() {
        return daoMedicamento;
    }

    public void setDaoMedicamento(MedicamentoDAO<Medicamento> daoMedicamento) {
        this.daoMedicamento = daoMedicamento;
    }

    public VendaItens getItem() {
        return item;
    }

    public void setItem(VendaItens item) {
        this.item = item;
    }

    public boolean isNovoItem() {
        return novoItem;
    }

    public void setNovoItem(boolean novoItem) {
        this.novoItem = novoItem;
    }

    public PacienteDAO getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

}
