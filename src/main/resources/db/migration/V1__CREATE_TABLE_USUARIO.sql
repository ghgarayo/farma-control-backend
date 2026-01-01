CREATE TABLE usuario (
     id UUID PRIMARY KEY,
     username VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     active BOOLEAN NOT NULL DEFAULT FALSE,
     activation_code VARCHAR(6) NOT NULL,
     role VARCHAR(50) NOT NULL,
     created_at TIMESTAMP WITHOUT TIME ZONE,
     created_by UUID,
     updated_at TIMESTAMP WITHOUT TIME ZONE,
     updated_by UUID,

     CONSTRAINT fk_usuario_created_by FOREIGN KEY (created_by) REFERENCES usuario(id),
     CONSTRAINT fk_usuario_updated_by FOREIGN KEY (updated_by) REFERENCES usuario(id)
);
