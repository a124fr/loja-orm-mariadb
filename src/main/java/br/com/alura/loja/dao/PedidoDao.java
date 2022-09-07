package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void atualizar(Pedido pedido) {
		this.em.merge(pedido);
	}
	
	public void remover(Pedido pedido) {
		pedido = this.em.merge(pedido);
		this.em.remove(pedido);
	}
	
	public Pedido buscarPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}
	
	public Pedido bucarPedidoComCliente(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
		return this.em.createQuery(jpql, Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<Object[]> relatorioDeVendas() {
		String jpql =  
				" SELECT  produto.nome, "
				+ "  SUM(item.quantidade), "
				+ "  MAX(pedido.data) "
				+ " FROM Pedido pedido "
				+ " JOIN pedido.itensPedido item "
				+ " JOIN item.produto produto "
				+ " GROUP BY produto.nome "
				+ " ORDER BY item.quantidade DESC";
		
		return this.em.createQuery(jpql, Object[].class)
				.getResultList();		
	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas2() {
		String jpql =  
				" SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, "
				+ "  SUM(item.quantidade), "
				+ "  MAX(pedido.data)) "
				+ " FROM Pedido pedido "
				+ " JOIN pedido.itensPedido item "
				+ " JOIN item.produto produto "
				+ " GROUP BY produto.nome "
				+ " ORDER BY item.quantidade DESC";
		
		return this.em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();		
	}
	
	
}
