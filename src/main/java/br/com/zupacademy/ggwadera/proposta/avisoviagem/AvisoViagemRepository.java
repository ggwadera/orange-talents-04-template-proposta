package br.com.zupacademy.ggwadera.proposta.avisoviagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, UUID> {}
