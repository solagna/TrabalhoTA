package br.edu.ifsul.testes;


import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TesteListarMedicamentosConsulta {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TesteListarMedicamentosConsulta() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("persistencia");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        
        boolean exception = false;
        try {
                        
            Consulta c = em.find(Consulta.class, 2);
            
            for (Medicamento ci : c.getReceita()){
                System.out.println("Medicamento: "+ci.getNome());
            }
        }catch (Exception e){
            // se gerar exceção 
            exception = true;
            e.printStackTrace();
        }
        // compara se não ocorreu erro
        Assert.assertEquals(false, exception);
    }
    
}
