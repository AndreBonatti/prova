package br.com.rede.qualis.prova.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rede.qualis.prova.entities.Procedimento;
import br.com.rede.qualis.prova.repository.ProcedimentoRepository;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/autorizador")
@Api(tags = "Autorizador", description = "API autorizador de procedimentos")
public class AutorizadorController {

	@Autowired
	ProcedimentoRepository procedimentoRepository;

	@RequestMapping(value = "/cadastro/procedimento/{procedimento}/idade/{idade}/sexo/{sexo}/autoriza/{autoriza}", method = RequestMethod.POST)
	public ResponseEntity<Procedimento> create(@PathVariable(value = "procedimento") int procedimento,
			@PathVariable(value = "idade") int idade, @PathVariable(value = "sexo") char sexo,
			@PathVariable(value = "autoriza") Boolean autoriza) {

		try {
			// Consulta para não duplicar no banco  
			Integer isExist = procedimentoRepository.isExistProcedimento(procedimento, idade, sexo, autoriza);
			if (isExist == 0) {
				Procedimento proced = new Procedimento();
				proced.setProcedimento(procedimento);
				proced.setIdade(idade);
				proced.setSexo(sexo);
				proced.setPermitido(autoriza);
				procedimentoRepository.save(proced);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/procedimento/{procedimento}/idade/{idade}/sexo/{sexo}", method = RequestMethod.GET)
	public ResponseEntity get(@PathVariable(value = "procedimento") int procedimento,
			@PathVariable(value = "idade") int idade, @PathVariable(value = "sexo") char sexo) {

		Integer isExist = procedimentoRepository.findAutorizaProcedimento(procedimento, idade, sexo);
		Boolean result = (isExist > 0) ? Boolean.TRUE : Boolean.FALSE;
		Map<String, String> map = new HashMap<String, String>();
		map.put("data", result.toString());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
