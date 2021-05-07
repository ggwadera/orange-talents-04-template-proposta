package br.com.zupacademy.ggwadera.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

  boolean existsByDocumento(String documento);

  List<Proposta> findByIdCartaoIsNullAndEstadoIs(@NonNull EstadoProposta estado);
}
