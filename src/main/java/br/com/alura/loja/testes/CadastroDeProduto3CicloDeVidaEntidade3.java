package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto3CicloDeVidaEntidade3 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES 2"); // entidade é transient
		
		EntityManager em =  JPAUtil.getEntityMannger();
		em.getTransaction().begin();
						
		Categoria categoria = em.find(Categoria.class, 3l);
		em.remove(categoria);		
		
		em.getTransaction().commit();
		em.close();
	}
}
