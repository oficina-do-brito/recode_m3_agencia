package models.utils;

public class Verificadora {
	/**
	 * @param estado String
	 * @return true ou false boolean
	 * @OBS: Os 27 possiveis estados são: * Acre - AC, Alagoas - AL, Amapá - AP, Amazonas - AM, Bahia - BA, Ceará - CE, Espírito Santo - ES, Goiás - GO, Maranhão - MA,
	 * Mato Grosso - MT, Mato Grosso do Sul - MS, Minas Gerais - MG, Pará - PA,
	 * Paraíba - PB, Paraná - PR, Pernambuco - PE, Piauí - PI, Rio de Janeiro - RJ,
	 * Rio Grande do Norte - RN, Rio Grande do Sul - RS, Rondônia - RO,
	 * Roraima - RR, Santa Catarina - SC, São Paulo -SP, Sergipe - SE, Tocantins - TO, Distrito Federal - DF,
	 */
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
	

}
