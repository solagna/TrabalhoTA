package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class PessoaDAO<T> extends DAOGenerico<Pessoa> implements Serializable {

    public PessoaDAO() {
        super();
        super.setClassePersistente(Pessoa.class);
        super.setOrdem("nome");
    }

    public boolean login(String email, String nome) {
        Query query = super.getEm().createQuery("from Pessoa where upper(email) = "
                + " :email and upper(nome) = :nome");
        query.setParameter("email", email.toUpperCase());
        query.setParameter("nome", nome.toUpperCase());
        if (!query.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Pessoa localizaPorEmail(String email) {
        Query query = super.getEm().createQuery("from Pessoa where upper(email) = "
                + ":email");
        query.setParameter("email", email.toUpperCase());
        Pessoa obj = (Pessoa) query.getSingleResult();
        obj.getTelefones().size();

        return obj;
    }

}
