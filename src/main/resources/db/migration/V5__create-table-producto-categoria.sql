CREATE TABLE productos_categorias (
    producto_id bigint,
    categoria_id INT,
    PRIMARY KEY (producto_id, categoria_id),
    FOREIGN KEY (producto_id) REFERENCES producto(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);
