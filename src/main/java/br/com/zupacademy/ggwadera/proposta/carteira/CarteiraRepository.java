package br.com.zupacademy.ggwadera.proposta.carteira;

import br.com.zupacademy.ggwadera.proposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {
  boolean existsByTipoAndCartao(TipoCarteira tipo, Cartao cartao);
}
