/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emanuele
 */
@Stateless
public class PacienteDAO<T> extends DAOGenerico<Paciente>implements Serializable{

    public PacienteDAO() {
        super();
         super.setClassePersistente(Paciente.class);
    }
     public Paciente getObjectById(Integer id) throws Exception {
        Paciente obj = (Paciente) super.getEm().find(super.getClassePersistente(), id);
        // inicializando a coleção de objetos para não ocorrer lazy exception
        obj.getTelefones().size();
        return obj;
    }
}
