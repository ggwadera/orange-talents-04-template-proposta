package br.com.zupacademy.ggwadera.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

  boolean existsByDocumento(String documento);

  List<Proposta> findByIdCartaoIsNullAndEstadoIs(@NonNull EstadoProposta estado);
}
