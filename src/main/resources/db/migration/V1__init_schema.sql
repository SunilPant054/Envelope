CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE tenant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE category(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tenant_id UUID NOT NULL REFERENCES tenant(id),
    name VARCHAR(100) NOT NULL,
    weight INT NOT NULL,
    is_essential BOOLEAN NOT NULL DEFAULT false
);