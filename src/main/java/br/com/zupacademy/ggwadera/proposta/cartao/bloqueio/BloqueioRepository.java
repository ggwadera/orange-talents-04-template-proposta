package br.com.zupacademy.ggwadera.proposta.cartao.bloqueio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BloqueioRepository extends JpaRepository<Bloqueio, UUID> {}
