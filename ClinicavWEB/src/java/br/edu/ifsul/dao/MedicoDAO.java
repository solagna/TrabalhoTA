/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Medico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Emanuele
 */
@Stateless
public class MedicoDAO<T> extends DAOGenerico<Medico> implements Serializable {

    public MedicoDAO() {
        super();
        super.setClassePersistente(Medico.class);
    }

    @Override
    public Medico getObjectById(Integer id) throws Exception {
        Medico obj = (Medico) super.getEm().find(super.getClassePersistente(), id);
        // inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getTelefones().size();
        return obj;
    }

}
