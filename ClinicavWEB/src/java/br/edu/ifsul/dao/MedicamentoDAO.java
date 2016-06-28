/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Medicamento;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Emanuele
 */
@Stateless
public class MedicamentoDAO<T> extends DAOGenerico<Medicamento> implements Serializable {

   public MedicamentoDAO() {
        super();
        super.setClassePersistente(Medicamento.class);
        super.setOrdem("nome");
    }
    
}
