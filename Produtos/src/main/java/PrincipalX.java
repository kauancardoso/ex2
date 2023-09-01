import java.util.Scanner;


public class PrincipalX {
	public static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		DAOX dao = new DAOX();
		dao.conectar();
		
		int x = 0;
		do {
			System.out.printf("1- Listar\n2- Inserir\n 3- Excluir\n 4- Atualizar\n5- Sair\n Opcao?  ");
			x = sc.nextInt();
			switch(x) {
			case 1:
				//Mostrar Produtos
				System.out.println("==== Mostrar produtos === ");
				Produtos[] produto = dao.getProdutos();
				if(produto != null) {
					for(int i = 0; i < produto.length; i++) {
					System.out.println(produto[i].toString());
					}
				}
				else {
					System.out.println("Nao existe produto.");
					}
				break;
			case 2:
			{
				//Inserir um elemento na tabela
				System.out.println("codigo: ");
				int cod = sc.nextInt(); 
				System.out.println("nome: ");
				String nome = sc.next();
				System.out.println("quantidade: ");
				double qnt = MyIO.readDouble(); 
				System.out.println("disponivel em estoque? ");
				boolean estoque = MyIO.readBoolean(); 
				Produto produto = new Caneta(cod, nome, qnt, estoque);
				if(dao.inserirCaneta(produto) == true) {
					System.out.println("Inserção com sucesso -> " + produto.toString());
				}
				break;
			}
			case 3:
				//Excluir produto
				System.out.println("codigo: ");
				int cod1 = sc.nextInt();
				dao.excluirCaneta(cod1);
				break;
			case 4:
			{
				//Inserir um elemento na tabela
				System.out.println("codigo: ");
				int cod = sc.nextInt(); 
				System.out.println("nome: ");
				String nome = sc.next();
				System.out.println("quantidade: ");
				double qnt = MyIO.readDouble(); 
				System.out.println("disponivel em estoque? ");
				boolean estoque = MyIO.readBoolean(); 
				Produto produto = new Caneta(cod, nome, qnt, estoque);
				if(dao.atualizarCaneta(produto) == true) {
					System.out.println("Inserção com sucesso -> " + produto.toString());
				}
				break;
			}
			case 5:

				break;
			default:
				System.out.println("ERRO: Valor Invalido!");
				break;
			}
		}while(x!=5);

		dao.close();
	}
}