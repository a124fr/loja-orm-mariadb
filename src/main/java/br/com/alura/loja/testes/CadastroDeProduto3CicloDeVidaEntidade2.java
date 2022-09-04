package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto3CicloDeVidaEntidade2 {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES 2"); // entidade é transient
		
		EntityManager em =  JPAUtil.getEntityMannger();
		em.getTransaction().begin();
		
		em.persist(celulares);// entidade é managed, é o principal estado que entidade pode estar.
		celulares.setNome("XPTO");
		
//		em.flush();// sincroniza com o banco as entidades sem comitar.
		em.getTransaction().commit();
//		em.close();// fechar a conexão.
		em.clear();// limpa as entidades gerenciadas do entitymanager.
		
		
		
		celulares.setNome("1234");//entidade é detached
	}
}
