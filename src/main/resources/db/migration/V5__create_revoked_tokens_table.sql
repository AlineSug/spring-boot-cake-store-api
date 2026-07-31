    CREATE TABLE revoked_tokens (
        token_id VARCHAR(36) NOT NULL,
        expiration DATETIME NOT NULL,
        PRIMARY KEY (token_id)
    );

    CREATE INDEX idx_revoked_tokens_expiration ON revoked_tokens (expiration);