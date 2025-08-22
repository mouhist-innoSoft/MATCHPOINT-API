-- V1__base_schema.sql
-- Esquema inicial MINDFLOW - PostgreSQL

-- =========
-- Tipos
-- =========
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'consulta_status') THEN
    CREATE TYPE consulta_status AS ENUM ('AGENDADA','REALIZADA','CANCELADA');
  END IF;
END$$;

-- =========
-- Tabela: role
-- =========
CREATE TABLE IF NOT EXISTS role (
  id           BIGSERIAL PRIMARY KEY,
  name         VARCHAR(100) NOT NULL UNIQUE
);

-- =========
-- Tabela: usuario
-- =========
CREATE TABLE IF NOT EXISTS usuario (
  id                BIGSERIAL PRIMARY KEY,
  nome              VARCHAR(150) NOT NULL,
  email             VARCHAR(150) NOT NULL UNIQUE,
  senha             VARCHAR(255) NOT NULL,
  data_nascimento   DATE,
  cpf               VARCHAR(14) UNIQUE,   -- opcionalmente armazenar com máscara
  created_at        TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at        TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Índices úteis
CREATE INDEX IF NOT EXISTS idx_usuario_nome ON usuario (nome);
-- email e cpf já possuem UNIQUE

-- Trigger simples de updated_at (opcional, mas útil)
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'set_updated_at') THEN
    CREATE OR REPLACE FUNCTION set_updated_at()
    RETURNS TRIGGER AS $f$
    BEGIN
      NEW.updated_at := NOW();
      RETURN NEW;
    END
    $f$ LANGUAGE plpgsql;
  END IF;
END$$;

DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_usuario_updated_at') THEN
    CREATE TRIGGER trg_usuario_updated_at
    BEFORE UPDATE ON usuario
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();
  END IF;
END$$;

-- ===========================
-- Tabela de associação: tb_usuarios_roles
-- ===========================
CREATE TABLE IF NOT EXISTS tb_usuarios_roles (
  usuario_id   BIGINT NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
  role_id      BIGINT NOT NULL REFERENCES role(id)    ON DELETE CASCADE,
  PRIMARY KEY (usuario_id, role_id)
);

CREATE INDEX IF NOT EXISTS idx_ur_role ON tb_usuarios_roles (role_id);

-- =========
-- Tabela: psicologo (1:1 com usuario)
-- =========
CREATE TABLE IF NOT EXISTS psicologo (
  id            BIGSERIAL PRIMARY KEY,
  usuario_id    BIGINT NOT NULL UNIQUE REFERENCES usuario(id) ON DELETE CASCADE,
  crp           VARCHAR(30) UNIQUE,
  especialidade VARCHAR(150)
);

CREATE INDEX IF NOT EXISTS idx_psicologo_usuario ON psicologo (usuario_id);

-- =========
-- Tabela: paciente (N:1 com psicologo)
-- =========
CREATE TABLE IF NOT EXISTS paciente (
  id               BIGSERIAL PRIMARY KEY,
  nome             VARCHAR(150) NOT NULL,
  contato          VARCHAR(150),
  data_nascimento  DATE,
  psicologo_id     BIGINT NOT NULL REFERENCES psicologo(id) ON DELETE CASCADE,
  created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_paciente_psicologo ON paciente (psicologo_id);
CREATE INDEX IF NOT EXISTS idx_paciente_nome ON paciente (nome);

-- =========
-- Tabela: consulta (N:1 paciente, N:1 psicologo)
-- =========
CREATE TABLE IF NOT EXISTS consulta (
  id             BIGSERIAL PRIMARY KEY,
  paciente_id    BIGINT NOT NULL REFERENCES paciente(id)  ON DELETE CASCADE,
  psicologo_id   BIGINT NOT NULL REFERENCES psicologo(id) ON DELETE CASCADE,
  data_hora      TIMESTAMPTZ NOT NULL,
  status         consulta_status NOT NULL DEFAULT 'AGENDADA',
  observacoes    TEXT,
  created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_consulta_paciente  ON consulta (paciente_id);
CREATE INDEX IF NOT EXISTS idx_consulta_psicologo ON consulta (psicologo_id);
CREATE INDEX IF NOT EXISTS idx_consulta_datahora  ON consulta (data_hora);

-- =========
-- Tabela: sessao (1:1 com consulta)
-- =========
CREATE TABLE IF NOT EXISTS sessao (
  id                 BIGSERIAL PRIMARY KEY,
  consulta_id        BIGINT NOT NULL UNIQUE REFERENCES consulta(id) ON DELETE CASCADE,
  resumo_atendimento TEXT,
  observacoes        TEXT,
  data_registro      TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_sessao_consulta ON sessao (consulta_id);

-- =========
-- Seeds básicos (roles)
-- =========
INSERT INTO role (name) VALUES
  ('ROLE_ADMIN'),
  ('ROLE_PSICOLOGO'),
  ('ROLE_PACIENTE')
ON CONFLICT (name) DO NOTHING;

-- Criar um usuário admin inicial em uma V2 de migração,
-- vinculando à ROLE_ADMIN e criando um registro em psicologo se desejar.

-- INSERT INTO usuario (nome, email, senha, cpf, data_nascimento)
-- VALUES ('Administrador', 'admin@mindflow.app', '{HASH_BCRYPT}', NULL, NULL)
-- ON CONFLICT (email) DO NOTHING;
--
-- INSERT INTO tb_usuarios_roles (usuario_id, role_id)
-- SELECT u.id, r.id
-- FROM usuario u, role r
-- WHERE u.email = 'admin@mindflow.app' AND r.name = 'ROLE_ADMIN'
-- ON CONFLICT DO NOTHING;

-- FIM

