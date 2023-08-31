import java.sql.*;

public class DAOX {
	private Connection conexao;
	
	public DAOX() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "X";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "PostgreSQL 15";
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
	
	public boolean inserirCaneta(Caneta caneta) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO caneta (numSerie, cor, tamPonta, tampada) "
					       + "VALUES ("+caneta.getNumSerie()+ ", '" + caneta.getCor() + "', '"  
					       + caneta.getTamPonta() + "', '" + caneta.isTampada() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCaneta(Caneta caneta) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE caneta SET cor = '" + caneta.getCor() + "', ponta = '"  
				       + caneta.getTamPonta() + "', tampada = '" + caneta.isTampada() + "'"
					   + " WHERE codigo = " + caneta.getNumSerie();
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
			st.executeUpdate("DELETE FROM caneta WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Caneta[] getCanetas() {
		Caneta[] canetas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM caneta");		
	         if(rs.next()){
	             rs.last();
	             canetas = new Caneta[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                canetas[i] = new Caneta(rs.getInt("codigo"), rs.getString("cor"), 
	                		                  rs.getDouble("tamPonta"), rs.getBoolean("tampada"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return canetas;
	}

	
	public Caneta[] getCanetasTampadas() {
		Caneta[] canetas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM caneta WHERE caneta.tampada LIKE 'true'");		
	         if(rs.next()){
	             rs.last();
	             canetas = new Caneta[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                canetas[i] = new Caneta(rs.getInt("codigo"), rs.getString("cor"), 
      		                  rs.getDouble("tamPonta"), rs.getBoolean("tampada"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return canetas;
	}
}