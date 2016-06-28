/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medico;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Emanuele
 */
@Stateless
public class ConsultaDAO<T> extends DAOGenerico<Consulta> implements Serializable{
    
    public ConsultaDAO() {
        super();
        super.setClassePersistente(Consulta.class);
    }
    
}
