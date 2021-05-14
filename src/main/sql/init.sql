-- https://stackoverflow.com/questions/18389124/simulate-create-database-if-not-exists-for-postgresql
SELECT 'CREATE DATABASE propostas'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'propostas')\gexec

-- Habilitar extensão para criptografia
\c propostas;
CREATE EXTENSION pgcrypto;