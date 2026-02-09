package br.com.projeto.app.util;

public abstract class Constantes {

	public static final Integer NRO_MAXIMO_REG_PAGINACAO = 20;
	public static final Integer NRO_MINIMO_REGISTROS = 1;
	public static final Integer COD_ERRO_INEXISTENTE = 100;
	public static final Integer COD_ERRO_VALIDACAO = 200;
	public static final Integer COD_ERRO_INTERNO = 300;
	public static final Integer COD_ERRO_SERVIDOR_INTERNO = 400;	

	public static final String MSG_ERRO_GERAL = "Ocorreu um erro na solicitação, tente novamente mais tarde.";
	public static final String MSG_REGISTROS_ENCONTRADOS = "Registros encontrados com sucesso.";
	public static final String MSG_REGISTROS_NAO_ENCONTRADOS = "Os registros solicitados não foram encontrados.";
	public static final String MSG_ERRO_PNEU_VINCULADO_ESSE_VEICULO = "O pneu já está vinculado a este veículo";
	public static final String MSG_ERRO_PNEU_VINCULADO_OUTRO_VEICULO = "O pneu está vinculado a outro veículo";
	public static final String MSG_ERRO_POSICAO_PNEU_VINCULADO_TEM_OUTRO_PNEU = "O veículo já possui a posição ocupada por outro pneu";
	public static final String MSG_SUCESSO_PNEU_VINCULADO = "Pneu vinculado com sucesso!";
	public static final String MSG_SUCESSO_PNEU_DESVINCULADO = "Pneu desvinculado com sucesso!";
	public static final String MSG_ERRO_VEICULO_NAO_ENCONTRADO = "Veiculo não encontrado";
	public static final String MSG_ERRO_PNEU_NAO_ENCONTRADO = "Pneu não encontrado";
	public static final String MGS_ENUMERADOR_INVALIDO = "%s informado(a) é inválido(a)";
	public static final String MSG_VEICULO_CRIACO_SUCESSO = "Veículo criado com sucesso";
	public static final String MSG_ERRO_CAMPO_OBRIGATORIO_FALTANTE = "Há campos obrigatórios que não foram informados";
	public static final String MSG_ERRO_RESOURCE_NOT_FOUND = "O caminho solicitado é inválido, verifique a url acessada";
	public static final String MSG_ERRO_PLACA_EXISTENTE = "A placa informada já está cadastrada no sistema";
	public static final String MSG_ERRO_BODY_REQUEST = "O corpo da requisição está com erro, verifique e corrija";
	public static final String MSG_ERRO_REQUEST_PARAM = "O parâmetro informado é inválido, verique o parâmetro e o valor informado";
	public static final String MSG_SUCESSO_PNEU_CADASTRADO = "Pneu cadastrado com sucesso!";
	public static final String DESC_ENUM_POSICAO_PNEU = "Posição do pneu";
	public static final String DESC_ENUM_STATUS_VEICULO = "Status do veículo";
}
