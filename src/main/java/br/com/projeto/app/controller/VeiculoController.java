package br.com.projeto.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.app.dto.MensagemErroDto;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.RespostaVeiculoDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/veiculo", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Veículos", description = "Serviços de veículos")
public class VeiculoController {

	@Autowired
	private VeiculoService service;
	
	@Operation(summary = "Obter veículos", description = "Lista veículos cadastrados")
	@ApiResponses(value = {
			@ApiResponse(description = "Veículos obtidos com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaVeiculoDto.class))),
			@ApiResponse(description = "Registros não encontrados", responseCode = "400", content = @Content(schema = @Schema(implementation = RespostaVeiculoDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@GetMapping
	public ResponseEntity<RespostaDto<VeiculoDto>> getVeiculos(
			@Parameter(description = "Número da página", required = true) @RequestParam(defaultValue = "1") Integer pagina){
		return ResponseEntity.ok().body(this.service.getVeiculos(pagina));
	}
	
	@Operation(summary = "Obter veículo por id", description = "Obtém um veículo pelo id")
	@ApiResponses(value = {
			@ApiResponse(description = "Registros encontrados com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaVeiculoDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@GetMapping("/{idVeiculo}")
	public ResponseEntity<RespostaDto<VeiculoDto>> getVeiculoById(@Parameter(name = "idVeiculo", required = true) @PathVariable Integer idVeiculo){
		return ResponseEntity.ok().body(this.service.getVeiculoById(idVeiculo));
	}
	
	@Operation(summary = "Cadastrar veículo", description = "Cadastra um novo veículo")
	@ApiResponses(value = {
			@ApiResponse(description = "Veículo criado com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaVeiculoDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@PostMapping
	public ResponseEntity<RespostaDto<VeiculoDto>> createVeiculo(@RequestBody VeiculoDto dto){
		return ResponseEntity.ok().body(this.service.createVeiculo(dto));
	}
}
