package br.com.projeto.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.app.model.Pneu;

@Repository
public interface PneuRepository extends JpaRepository<Pneu, Integer> {

}
