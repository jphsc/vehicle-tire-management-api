package br.com.projeto.app.domain.enums;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.NoSuchElementException;

import br.com.projeto.app.util.Constantes;

public abstract class Enums {
	
	public static <E extends Enum<E>> E getEnum(Class<E> enumClass, Integer id, String tipoEnum) {
		return Arrays.stream(enumClass.getEnumConstants())
			.filter(e -> {
				try {
					Method getId = e.getClass().getMethod("getId");
					return id.equals(getId.invoke(e));
				} catch (Exception e2) {
					return false;
				}
			})
			.findFirst()
			.orElseThrow(() -> new NoSuchElementException(String.format(Constantes.MGS_ENUMERADOR_INVALIDO, tipoEnum)));
	}
	
	public enum SituacaoVeiculo {
		
		ATIVO(1, "Ativo"),
		INATIVO(2, "Inativo");
		
		private Integer id;
		private String descricao;
		
		private SituacaoVeiculo(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}
	
	public enum StatusPneu {
		
		EM_USO(3, "Em uso"),
		DISPONIVEL(4, "Disponível");
		
		private Integer id;
		private String descricao;
		
		private StatusPneu(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}

	public enum TipoOperacao {
		
		CRIAR(5),
		VINCULAR(6),
		DESVINCULAR(7),
		OBTER(8);
		
		private Integer id;
		
		private TipoOperacao(Integer id) {
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}
	
	public enum PosicoesPneu {
		
		DE(9, "Dianteiro Esquerdo"),
		DD(10, "Dianteiro Direito"),
		T1EI(11, "Traseiro 1 Esquerdo Interno"),
		T1EE(12, "Traseiro 1 Esquerdo Externo"),
		T1DI(13, "Traseiro 1 Direito Interno"),
		T1DE(14, "Traseiro 1 Direito Externo"),
		T2EI(15, "Traseiro 2 Esquerdo Interno"),
		T2EE(16, "Traseiro 2 Esquerdo Externo"),
		T2DI(17, "Traseiro 2 Direito Interno"),
		T2DE(18, "Traseiro 2 Direito Externo");
		
		private Integer id;
		private String descricao;
		
		private PosicoesPneu(Integer id, String descricao) {
			this.id = id;
			this.descricao = descricao;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}
}
