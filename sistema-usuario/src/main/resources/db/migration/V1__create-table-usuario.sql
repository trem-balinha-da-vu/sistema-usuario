CREATE TABLE IF NOT EXISTS tipo_usuario_base(
	id BIGSERIAL NOT NULL,
	nome VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS usuario_base(
	id BIGSERIAL NOT NULL,
	idade INT NOT NULL,
	primeiro_nome VARCHAR(100) NOT NULL,
	sobrenome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	senha VARCHAR(100) NOT NULL UNIQUE,
	tipo_usuario_base_id INT,
	criado_em TIMESTAMP NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT tipo_usuario_base_fk FOREIGN KEY (tipo_usuario_base_id) REFERENCES tipo_usuario_base (id)
);
