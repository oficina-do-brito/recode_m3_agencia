package pricipal;

public class Utils {
	public Utils() {}

	public void exibirMenuPrincipal() {
		System.out.println("Escolha 1 para acessar menu usuario: ");
		System.out.println("Escolha 2 para acessar menu cliente: ");
		System.out.println("Escolha 3 para acessar menu fornecedor: ");
		System.out.println("Escolha 4 para acessar menu de Pacotes de Viagens: ");
		System.out.println("Escolha 0 para sair...: ");
		System.out.print("Eai major, qual opção você escolheu? ");
	}

	public void exibirMenuUsuario() {
		System.out.printf("%n");
		System.out.println("1 para cadastrar usuario");
		System.out.println("2 para Atualizar usuario");
		System.out.println("3 para deletar usuario");
		System.out.println("4 para ver todos os usuarios");
		System.out.println("0 para voltar ao menu principal...");
		System.out.print("Eai major, qual opção você escolheu? ");
	}

	public void exibirMenuCliente() {
		System.out.printf("%n");
		System.out.println("1 para Atualizar cliente");
		System.out.println("2 para deletar cliente");
		System.out.println("3 para mostrar todos os clientes");
		System.out.println("4 para comprar um Pacote Viagem");
		System.out.println("5 para efetuar pagamento dos Pacotes Viagem");
		System.out.println("0 para voltar ao menu principal...");
		System.out.print("Eai major, qual opção você escolheu? ");
	}

	public void exibirMenuFornecedor() {
		System.out.printf("%n");
		System.out.println("1 para Atualizar fornecedor");
		System.out.println("2 para fornecer Passagem");
		System.out.println("3 para fornecer Hospedagem");
		System.out.println("4 para deletar forenecor");
		System.out.println("5 para mostrar todos os fornecedores");
		System.out.println("0 para voltar ao menu principal...");
		System.out.print("Eai major, qual opção você escolheu? ");
	}

	public void exibirMenuViagem() {
		System.out.println("1 para listar Os Pacotes de Viagem");
		System.out.println("0 para voltar ao menu principal...");
		System.out.print("Eai major, qual opção você escolheu? ");
	}
}

























