package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class Teste {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityMannger();
		
//		Categoria celulares = new Categoria("CELULARES");
//		celulares.setId(1l);
//		
//		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
//		celular.setId(1l);
//		
//		em.getTransaction().begin();
//		em.merge(celulares);
//		em.merge(celular);
//		
//		em.getTransaction().commit();		
//		em.close();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto produto = em.find(Produto.class, 1l);// managed
		
		em.getTransaction().begin();
		produto.setDescricao("Olá");
		
		em.clear();// detached

		produto.setDescricao("Xiaomi-4");
//		produto = em.merge(produto); // managed
		produtoDao.atualizar(produto);
		
		em.getTransaction().commit();
		em.close();
	}
}
