package br.com.projeto.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.app.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

	@Query("SELECT v FROM Veiculo v LEFT JOIN FETCH v.pneus p WHERE v.id = :id ORDER BY p.posicaoPneu ASC")
	public Optional<Veiculo> findVeiculoByIdWithPneus(@Param("id") Integer id);
}
