package models.utils;

public class Verificadora {

	public static boolean verificaEstado(String estado) {
		final String[] estadosBrasileiros = new String[] {"AC","AL","AP","AM","BA","CE","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO","DF"};
		if(estado.length() == 2){
			for(String e :  estadosBrasileiros ){
				if (estado.toUpperCase().equals(e)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean verificaTipoUsuarioExists(int tipo) {
		if(tipo > 0 && tipo < 4){
			return true;
		}
		return false;
	}
	public static boolean verificaTipoServicoExists(int tipo) {
		if(tipo > 0 && tipo < 3){
			return true;
		}
		return false;
	}
}
