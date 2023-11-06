ALTER TABLE usuario_base
ADD CONSTRAINT email_unique_constraint UNIQUE (email);

ALTER TABLE usuario_base
ADD CONSTRAINT senha_unique_constraint UNIQUE (senha);
