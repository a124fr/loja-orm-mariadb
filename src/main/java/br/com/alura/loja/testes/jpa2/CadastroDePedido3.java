package br.com.alura.loja.testes.jpa2;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido3 {

	public static void main(String[] args) {
//		popularBancoDeDados();
		
		EntityManager em = JPAUtil.getEntityMannger();
		ProdutoDao produtoDao = new ProdutoDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
//		Pedido pedido = em.find(Pedido.class, 1l);
		Pedido pedido = pedidoDao.bucarPedidoComCliente(1l);
//		System.out.println(pedido.getData());
//		System.out.println(pedido.getItensPedido().size());
		
		em.close();
		
		System.out.println(pedido.getCliente().getNome());
		
	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Macbook", "Mackbook pro retina", new BigDecimal("14000"), celulares);
		
		Cliente cliente = new Cliente("Rodrigo" ,"123456");
		
		Pedido pedido = new Pedido(cliente);				
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));
				
		EntityManager em = JPAUtil.getEntityMannger();
		CategoriaDao categoriaDao = new CategoriaDao(em);		
		ProdutoDao produtoDao = new ProdutoDao(em);				
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin();// in??cio
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);		
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);		
		
		clienteDao.cadastrar(cliente);
		
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		
		em.getTransaction().commit();
		em.close(); // fim		 
	}
}
