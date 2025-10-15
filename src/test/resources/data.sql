-- TABLA: ciudades
INSERT INTO ciudades (nombre) VALUES ('Medellin');
INSERT INTO ciudades (nombre) VALUES ('Bogota');
INSERT INTO ciudades (nombre) VALUES ('Cali');
INSERT INTO ciudades (nombre) VALUES ( 'Barranquilla');
INSERT INTO ciudades (nombre) VALUES ( 'Cartagena');

-- TABLA: categorias
INSERT INTO categorias (nombre) VALUES ('Alimentos');
INSERT INTO categorias (nombre) VALUES ( 'Electronica');
INSERT INTO categorias (nombre) VALUES ( 'Ropa');
INSERT INTO categorias (nombre) VALUES ( 'Juguetes');
INSERT INTO categorias (nombre) VALUES ( 'Libros');

-- TABLA: marcas
INSERT INTO marcas (nombre) VALUES ('Sony');
INSERT INTO marcas (nombre) VALUES ( 'Samsung');
INSERT INTO marcas (nombre) VALUES ( 'Apple');
INSERT INTO marcas (nombre) VALUES ( 'LG');
INSERT INTO marcas (nombre) VALUES ( 'Adidas');

-- TABLA: unidades_de_medida
INSERT INTO unidades_de_medida (nombre) VALUES ('Unidad');
INSERT INTO unidades_de_medida (nombre) VALUES ( 'Caja');
INSERT INTO unidades_de_medida (nombre) VALUES ( 'Litro');
INSERT INTO unidades_de_medida (nombre) VALUES ( 'Metro');
INSERT INTO unidades_de_medida (nombre) VALUES ( 'Kilogramo');

-- TABLA: impuestos
INSERT INTO impuestos (nombre, porcentaje) VALUES ('IVA 19%', 19.0);
INSERT INTO impuestos (nombre, porcentaje) VALUES ('IVA 5%', 5.0);
INSERT INTO impuestos (nombre, porcentaje) VALUES ('Exento', 0.0);
INSERT INTO impuestos (nombre, porcentaje) VALUES ('IVA 10%', 10.0);
INSERT INTO impuestos (nombre, porcentaje) VALUES ('IVA 15%', 15.0);

-- TABLA: usuarios
INSERT INTO usuarios (nombre, email, telefono, password) VALUES ('Andres Ortega', 'andres@gmail.com', '3104876482', '12345');
INSERT INTO usuarios (nombre, email, telefono, password) VALUES ('Tatiana Lopez', 'tatiana@hotmail.com', '3024652305', '12345');
INSERT INTO usuarios (nombre, email, telefono, password) VALUES ('Carlos Perez', 'carlos@hotmail.com', '3159542312', '12345');
INSERT INTO usuarios (nombre, email, telefono, password) VALUES ( 'Maria Ruiz', 'maria@gmail.com', '3024658703', '12345');
INSERT INTO usuarios (nombre, email, telefono, password) VALUES ( 'Juan Gomez', 'juan@hotmail.com', '3176509342', '12345');

-- TABLA: proveedores
INSERT INTO proveedores (nombre, id_ciudad, direccion, email, estado) VALUES ('Proveedor A', 1, 'Calle 10 #20-30', 'proveedora@gmail.com', TRUE);
INSERT INTO proveedores (nombre, id_ciudad, direccion, email, estado) VALUES ('Proveedor B', 2, 'Carrera 45 #12-50', 'proveedorb@hotmail.com', TRUE);
INSERT INTO proveedores (nombre, id_ciudad, direccion, email, estado) VALUES ('Proveedor C', 3, 'Avenida 5 #15-10', 'proveedorc@gmail.com', TRUE);
INSERT INTO proveedores (nombre, id_ciudad, direccion, email, estado) VALUES ( 'Proveedor D', 4, 'Calle 80 #40-60', 'proveedord@hotmail.com', FALSE);
INSERT INTO proveedores (nombre, id_ciudad, direccion, email, estado) VALUES ( 'Proveedor E', 5, 'Carrera 8 #22-11', 'proveedore@hotmail.com', TRUE);

-- TABLA: telefonos
INSERT INTO telefonos (numero) VALUES ('3111111111');
INSERT INTO telefonos (numero) VALUES ( '3122222222');
INSERT INTO telefonos (numero) VALUES ( '3133333333');
INSERT INTO telefonos (numero) VALUES ( '3144444444');
INSERT INTO telefonos (numero) VALUES ( '3155555555');

-- TABLA: proveedores_telefonos
INSERT INTO proveedores_telefonos (id_proveedor, id_telefono) VALUES (1, 1);
INSERT INTO proveedores_telefonos (id_proveedor, id_telefono) VALUES (2, 2);
INSERT INTO proveedores_telefonos (id_proveedor, id_telefono) VALUES (3, 3);
INSERT INTO proveedores_telefonos (id_proveedor, id_telefono) VALUES (4, 4);
INSERT INTO proveedores_telefonos (id_proveedor, id_telefono) VALUES (5, 5);

-- TABLA: productos
INSERT INTO productos (nombre, id_categoria, id_marca, id_unidad_medida, cantidad_unidades_medida, id_impuesto, precio, stock, estado)
VALUES ('Televisor', 2, 1, 1, 1, 1, 2500000, 10, TRUE);
INSERT INTO productos (nombre, id_categoria, id_marca, id_unidad_medida, cantidad_unidades_medida, id_impuesto, precio, stock, estado)
VALUES ('Camiseta', 3, 5, 1, 1, 3, 80000, 50, TRUE);
INSERT INTO productos (nombre, id_categoria, id_marca, id_unidad_medida, cantidad_unidades_medida, id_impuesto, precio, stock, estado)
VALUES ('Celular', 2, 3, 1, 1, 1, 3500000, 15, TRUE);
INSERT INTO productos (nombre, id_categoria, id_marca, id_unidad_medida, cantidad_unidades_medida, id_impuesto, precio, stock, estado)
VALUES ('Libro', 5, 4, 1, 1, 2, 60000, 30, TRUE);
INSERT INTO productos (nombre, id_categoria, id_marca, id_unidad_medida, cantidad_unidades_medida, id_impuesto, precio, stock, estado)
VALUES ('Balon', 4, 5, 1, 1, 2, 40000, 20, TRUE);

-- TABLA: compras
INSERT INTO compras (fecha, num_factura, id_proveedor, id_usuario)
VALUES ('2025-10-01', 1001, 1, 1);
INSERT INTO compras (fecha, num_factura, id_proveedor, id_usuario)
VALUES ('2025-10-02', 1002, 2, 2);
INSERT INTO compras (fecha, num_factura, id_proveedor, id_usuario)
VALUES ('2025-10-03', 1003, 3, 3);
INSERT INTO compras (fecha, num_factura, id_proveedor, id_usuario)
VALUES ( '2025-10-04', 1004, 4, 4);
INSERT INTO compras (fecha, num_factura, id_proveedor, id_usuario)
VALUES ( '2025-10-05', 1005, 5, 5);

-- TABLA: detalle_compra
INSERT INTO detalle_compra (id_produto, cantidad)
VALUES ( 1, 2);
INSERT INTO detalle_compra (id_produto, cantidad)
VALUES (2, 5);
INSERT INTO detalle_compra (id_produto, cantidad)
VALUES (3, 1);
INSERT INTO detalle_compra (id_produto, cantidad)
VALUES (4, 3);
INSERT INTO detalle_compra (id_produto, cantidad)
VALUES (5, 4);