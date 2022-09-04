package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto3CicloDeVidaEntidade4 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES 2"); // entidade é transient
		
		EntityManager em =  JPAUtil.getEntityMannger();
		em.getTransaction().begin();
		
		em.persist(celulares);
		celulares.setNome("XPTO2");
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.setNome("1234");
		em.flush();
		
		em.clear();
		
		celulares = em.merge(celulares);
		em.remove(celulares);
		em.flush();
		em.close();
	}
}
