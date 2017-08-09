package br.com.agilles.casadocodigoSpring.loja.dao;

import br.com.agilles.casadocodigoSpring.loja.models.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProdutoDao {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(Produto produto) {
        manager.persist(produto);
    }

    public List<Produto> listar() {
        return manager.createQuery("SELECT p from Produto p", Produto.class).getResultList();
    }


    public Produto find(int id) {
        return manager.createQuery("SELECT distinct(p) from Produto p join fetch p.precos where p.id = :id", Produto.class)
                .setParameter("id", id).getSingleResult();

    }
}
