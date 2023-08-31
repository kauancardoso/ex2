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
				//Mostrar canetas
				System.out.println("==== Mostrar canetas === ");
				Caneta[] canetas = dao.getCanetas();
				for(int i = 0; i < canetas.length; i++) {
					System.out.println(canetas[i].toString());
				}
				break;
			case 2:
				//Inserir um elemento na tabela
				System.out.println("codigo: ");
				int cod = sc.nextInt(); 
				System.out.println("cor: ");
				String color = sc.next();
				System.out.println("ponta: ");
				double pont = sc.nextDouble(); 
				System.out.println("tampada? ");
				boolean tampa = sc.nextBoolean(); 
				Caneta caneta = new Caneta(cod, color, pont, tampa);
				if(dao.inserirCaneta(caneta) == true) {
					System.out.println("Inserção com sucesso -> " + caneta.toString());
				}
				break;
			case 3:
				//Excluir caneta
				System.out.println("codigo: ");
				int cod1 = sc.nextInt();
				dao.excluirCaneta(cod1);
				break;
			case 4:
				//Atualizar caneta
				//caneta.setCor("nova senha");
				//dao.atualizarCaneta(caneta);

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