package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		produto = this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = this.em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class)
				.getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		// usando parametro nomeado (named param)
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNome2(String nome) {
		// usando parametro posicional
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, Produto.class)
				.setParameter(1, nome)
				.getResultList();
	}
	
//	public List<Produto> buscarPorNomeDaCategoria(String nome) {
//		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeDaCategoria";
//		return em.createQuery(jpql, Produto.class)
//				.setParameter("nomeDaCategoria", nome)
//				.getResultList();
//	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome) {// usar named query
		return em.createNamedQuery("Produto.buscarPorNomeDaCategoria", Produto.class)
				.setParameter("nomeDaCategoria", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorParametros(String nome, 
			BigDecimal preco, LocalDate dataCadastro) {
		
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
		
		if(nome != null && !nome.isEmpty()) {
			jpql += " AND p.nome = :nome ";
		}
		
		if (preco != null) {
			jpql += " AND p.preco = :preco ";
		}
		
		if (dataCadastro != null) {
			jpql += " AND p.dataCadastro = :dataCadastro ";
		}
		
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		if(nome != null && !nome.isEmpty()) {
			query.setParameter("nome", nome);
		}
		
		if (preco != null) {
			query.setParameter("preco", preco);
		}
		
		if (dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}
		
		return query.getResultList();
	}
}
