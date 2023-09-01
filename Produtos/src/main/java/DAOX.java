import java.sql.*;

public class DAOX {
	private Connection conexao;
	
	public DAOX() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Produtos";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "12345678";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirProduto(Produto produto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto (numserie, nome, quantidade, isdisponivelEstoque) "
					       + "VALUES ("+produto.getNumSerie()+ ", '" + produto.getnome() + "', "  
					       + produto.getquantidade() + ", '" + produto.isdisponivelEstoque() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
		
		
	}
	
	public boolean atualizarProduto(Produto produto) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE produto SET nome = '" + produto.getnome() + "', quantidade = '"  
				       + produto.getquantidade() + "', isdisponivelEstoque = '" + produto.isdisponivelEstoque() + "'"
					   + " WHERE numserie = " + produto.getNumSerie();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirCaneta(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE numserie = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Produto[] getCanetas() {
		Produto[] canetas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produto");		
	         if(rs.next()){
	             rs.last();
	             canetas = new Produto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                canetas[i] = new Produto(rs.getInt("numserie"), rs.getString("nome"), 
	                		                  rs.getDouble("quantidade"), rs.getBoolean("isdisponivelEstoque"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return canetas;
	}

	
	public Produto[] getCanetasisdisponivelEstoques() {
		Produto[] canetas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produto WHERE produto.isdisponivelEstoque LIKE 'true'");		
	         if(rs.next()){
	             rs.last();
	             canetas = new Produto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                canetas[i] = new Produto(rs.getInt("codigo"), rs.getString("nome"), 
      		                  rs.getDouble("quantidade"), rs.getBoolean("isdisponivelEstoque"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return canetas;
	}
}