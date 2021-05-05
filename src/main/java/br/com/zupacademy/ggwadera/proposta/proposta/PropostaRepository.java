package br.com.zupacademy.ggwadera.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
  boolean existsByDocumento(String documento);
}
