create table producto (
    id bigint primary key auto_increment,
    nombre varchar(100) not null,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL
);
