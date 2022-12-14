package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto2 {

	public static void main(String[] args) {
		EntityManager em =  JPAUtil.getEntityMannger();
//		Categoria celulares = new Categoria("CELULARES");
//		Produto celular = new Produto("Xiaomi Redmi 2", "Muito legal teste", new BigDecimal("800"), celulares);
		
		Produto celular = em.find(Produto.class, 1l);
		celular.setDescricao("Muito legal 3");
		celular.setNome("xiaomi Redmi 3");
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
//		celulares.setId(1l);
//		celular.setId(1l);
		produtoDao.atualizar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
