package pricipal.utils;

public class Utils {
	public void exibirMenuPrincipal() {
		System.out.println("Escolha [1] para acessar menu usuario: ");
		System.out.println("Escolha [2] Logar  no sistema");
		System.out.println("Escolha [3] para acessar viagens: ");
		System.out.println("Escolha [0] para sair. ");
		System.out.printf("Eai major, qual opção você escolheu? %s", escreverColorido(Colors.YELLOW,"(número):"));
	}

	public void exibirMenuUsuario() {
		System.out.printf("%n");
		System.out.println("[1] para cadastrar usuario");
		System.out.println("[2] para Atualizar usuario");
		System.out.println("[0] para voltar ao menu principal...");
		System.out.printf("Eai major, qual opção você escolheu? %s", escreverColorido(Colors.YELLOW,"(número):"));
	}

	public void exibirMenuCliente() {
		System.out.printf("%n");
		System.out.println("[1] para Atualizar cliente");
		System.out.println("[2] para deletar cliente");
		System.out.println("[3] para mostrar todos os clientes");
		System.out.println("[4] para Adicionar Pacote Viagem ao seu carrinho");
		System.out.println("[5] para efetuar pagamento dos Pacotes Viagem");
		System.out.println("[0] para voltar ao menu principal...");
		System.out.printf("Eai major, qual opção você escolheu? %s", escreverColorido(Colors.YELLOW,"(número):"));
	}

	public void exibirMenuFornecedor() {
		System.out.printf("%n");
		System.out.println("[1] para Atualizar fornecedor");
		System.out.println("[2] para fornecer Passagem");
		System.out.println("[3] para fornecer Hospedagem");
		System.out.println("[4] para deletar forenecor");
		System.out.println("[5] para mostrar todos os fornecedores");
		System.out.println("[0] para voltar ao menu principal.");
		System.out.printf("Eai major, qual opção você escolheu? %s", escreverColorido(Colors.YELLOW,"(número):"));
	}
	
	public void exibirMenuAdmin() {
		System.out.printf("%n");
		System.out.println("[1] para atualizar Administrador");
		System.out.println("[2] para ver todos os Administradores");
		System.out.println("[3] para montar um pacote viagem");
		System.out.println("[4] para ver todos os usuarios");
		System.out.println("[5] para deletar Administrador");
		System.out.println("[0] para voltar ao menu principal...");
		System.out.printf("Eai major, qual opção você escolheu? %s", escreverColorido(Colors.YELLOW,"(número):"));
	}

	public void exibirMenuViagem() {
		System.out.println("[1] para listar Os Pacotes de Viagem");
		System.out.println("[0] para voltar ao menu principal...");
		System.out.printf("Eai major, qual opção você escolheu? %s", escreverColorido(Colors.YELLOW,"(número):"));
	}
	public static String escreverColorido(Colors cor,String texto){
		return (cor.getColor()+ texto + Colors.RESET.getColor());
	}
}


























