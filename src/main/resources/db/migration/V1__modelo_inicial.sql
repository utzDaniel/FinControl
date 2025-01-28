CREATE TABLE usuario (
    id INT PRIMARY KEY IDENTITY(1,1),
    nom VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    eml VARCHAR(100) NOT NULL UNIQUE,
    id_fml INT,
    CONSTRAINT FK_familia FOREIGN KEY (id_fml) REFERENCES familia(id)
);

CREATE TABLE salario (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_usr INT NOT NULL,
    vlr_liq DECIMAL(10, 2) NOT NULL,
    dat DATE NOT NULL,
    CONSTRAINT FK_usr_salario FOREIGN KEY (id_usr) REFERENCES usuario(id)
);

CREATE TABLE familia (
    id INT PRIMARY KEY IDENTITY(1,1),
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE dominio_beneficio (
    id INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE beneficio (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_usr INT,
    id_dmn_bnf INT,
    vlr DECIMAL(10, 2) NOT NULL,
    dat DATE NOT NULL,
    CONSTRAINT FK_usr_beneficio FOREIGN KEY (id_usr) REFERENCES usuario(id),
    CONSTRAINT FK_dmnbnf_beneficio FOREIGN KEY (id_dmn_bnf) REFERENCES dominio_beneficio(id)
);

CREATE TABLE dominio_despesa (
    id INT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE item (
    id INT PRIMARY KEY IDENTITY(1,1),
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE despesa (
    id INT PRIMARY KEY IDENTITY(1,1),
    nom VARCHAR(100) NOT NULL,
    dat_ref DATE NOT NULL,
    dat_vct DATE
);

CREATE TABLE detalhe_despesa (
    id_dps INT,
    id_itm INT,
    id_dom_dps INT NOT NULL,
    id_usr INT,
    id_fml INT,
    qtd INT NOT NULL,
    prc DECIMAL(10, 2) NOT NULL,
    dsc VARCHAR(255) NULL,
    PRIMARY KEY (id_dps, id_itm),
    CONSTRAINT FK_dps_detalhe_despesa FOREIGN KEY (id_dps) REFERENCES despesa(id),
    CONSTRAINT FK_itm_detalhe_despesa FOREIGN KEY (id_itm) REFERENCES item(id),
    CONSTRAINT FK_domdps_detalhe_despesa FOREIGN KEY (id_dom_dps) REFERENCES dominio_despesa(id),
    CONSTRAINT FK_usr_detalhe_despesa FOREIGN KEY (id_usr) REFERENCES usuario(id),
    CONSTRAINT FK_fml_detalhe_despesa FOREIGN KEY (id_fml) REFERENCES familia(id)
);

CREATE TABLE dominio_pagamento (
    id INT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE pagamento (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_dps INT NOT NULL,
    id_usr INT NOT NULL,
    dat DATE NOT NULL,
    id_dom_pgt INT,
    id_dmn_bnf INT,
    CONSTRAINT FK_usr_pagamento FOREIGN KEY (id_usr) REFERENCES usuario(id),
    CONSTRAINT FK_dompgt_pagamento FOREIGN KEY (id_dom_pgt) REFERENCES dominio_pagamento(id),
    CONSTRAINT FK_dmnbnf_pagamento FOREIGN KEY (id_dmn_bnf) REFERENCES dominio_beneficio(id)
);
