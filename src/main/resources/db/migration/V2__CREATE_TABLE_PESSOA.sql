CREATE TABLE pessoa (
        id UUID PRIMARY KEY,
        first_name VARCHAR(100) NOT NULL,
        last_name VARCHAR(100) NOT NULL,
        phone_number VARCHAR(20),
        document_number VARCHAR(20) UNIQUE,
        person_type VARCHAR(20) NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE,
        updated_at TIMESTAMP WITHOUT TIME ZONE,
        created_by UUID,
        updated_by UUID,

        CONSTRAINT fk_pessoa_usuario FOREIGN KEY (id) REFERENCES usuario(id) ON DELETE CASCADE,
        CONSTRAINT fk_pessoa_created_by FOREIGN KEY (created_by) REFERENCES usuario(id),
        CONSTRAINT fk_pessoa_updated_by FOREIGN KEY (updated_by) REFERENCES usuario(id)
);