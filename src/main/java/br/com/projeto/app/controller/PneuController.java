package br.com.projeto.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.app.dto.DesvincularPneuVeiculoDto;
import br.com.projeto.app.dto.MensagemErroDto;
import br.com.projeto.app.dto.PneuDto;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.RespostaPneuDto;
import br.com.projeto.app.dto.RespostaVeiculoDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.dto.VincularPneuVeiculoDto;
import br.com.projeto.app.service.PneuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/pneu", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Pneus", description = "Serviços de pneus")
public class PneuController {

	@Autowired
	private PneuService service;
	
	@Operation(summary = "Lista pneus cadastrados" , description = "Lista pneus cadastrados")
	@ApiResponses(value = {
			@ApiResponse(description = "Pneus obtidos com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaPneuDto.class))),
			@ApiResponse(description = "Registros não encontrados", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaPneuDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@GetMapping
	public ResponseEntity<RespostaDto<PneuDto>> obterPneus(
			@Parameter(description = "Número da página", required = true) @RequestParam(defaultValue = "1") Integer pagina){
		return ResponseEntity.ok().body(service.obterPneus(pagina));
	}
	
	@Operation(summary = "Cadastrar pneu", description = "Cadastra um novo pneu")
	@ApiResponses(value = {
			@ApiResponse(description = "Pneu cadastrado com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaPneuDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@PostMapping("/cadastrar")
	public ResponseEntity<RespostaDto<PneuDto>> cadastrarPneu(@RequestBody PneuDto dto){
		return ResponseEntity.ok().body(this.service.cadastrarPneu(dto));
	}
	
	@Operation(summary = "Vincular pneu", description = "Vincula um pneu a um veículo")
	@ApiResponses(value = {
			@ApiResponse(description = "Pneu vinculado com sucesso!", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaVeiculoDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@PutMapping("/vincular")
	public ResponseEntity<RespostaDto<VeiculoDto>> vincularPneu(@RequestBody VincularPneuVeiculoDto dto){
		return ResponseEntity.ok().body(this.service.vincularPneu(dto));
	}
	
	@Operation(summary = "Desvincular pneu", description = "Desvincula um pneu de um veículo")
	@ApiResponses(value = {
			@ApiResponse(description = "Pneu desvinculado com sucesso!", responseCode = "200", content = @Content(schema = @Schema(implementation = RespostaPneuDto.class))),
			@ApiResponse(description = "Ocorreu um erro na solicitação, tente novamente mais tarde.", responseCode = "500", content = @Content(schema = @Schema(implementation = MensagemErroDto.class)))
	})
	@PutMapping("/desvincular")
	public ResponseEntity<RespostaDto<PneuDto>> desvincularPneu(@RequestBody DesvincularPneuVeiculoDto dto){
		return ResponseEntity.ok().body(this.service.desvincularPneu(dto));
	}
	
	
}
