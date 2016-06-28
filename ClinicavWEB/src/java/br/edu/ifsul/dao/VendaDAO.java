package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaID;
import br.edu.ifsul.modelo.Venda;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class VendaDAO<T> extends DAOGenerico<Venda> implements Serializable {
 
    public VendaDAO() {
        super();
        super.setClassePersistente(Venda.class);
    }
    
    
    public Venda getObjectById(VendaID id) throws Exception {
             
        Venda obj = (Venda) super.getEm().find(super.getClassePersistente(), id);
        // inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getItens().size();

        return obj;
    }    
    
}