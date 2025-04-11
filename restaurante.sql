create database restaurante;
use restaurante;

INSERT INTO Clientes (id, nombreCompleto, telefono, correoElectronico, fechaRegistro)
VALUES (1, 'Juan Pérez', '6621234567', 'juan.perez@example.com', '2024-04-10');
INSERT INTO Clientes (id, nombreCompleto, telefono, correoElectronico, fechaRegistro)
VALUES (2, 'Ana López', '6627654321', 'ana.lopez@example.com', '2024-04-05');
INSERT INTO ClienteFrecuente (id)
VALUES (2);

INSERT INTO Mesa (id, numero, capacidad)
VALUES (1, 5, 4);

INSERT INTO Comanda (id, folio, fechaHoraCreacion, totalVenta, estado, cliente_id, id_mesa)
VALUES (1, 'OB-20250408-001', '2024-04-10 13:45:00', 250.00, 'ABIERTO', 1, 1),
       (2, 'OB-20250408-002', '2024-04-10 14:15:00', 400.00, 'ABIERTO', 2, 1);

INSERT INTO ingredientes (id, nombre, stock, unidadMedida)
VALUES (1, 'Queso', 10.0, 'GRAMOS'),
       (2, 'Jamón', 5.0, 'GRAMOS'),
       (3, 'Pan', 20.0, 'PIEZAS');

INSERT INTO productos (id, nombre, precio, tipoProducto, estado)
VALUES (1, 'Sandwich de Jamón', 50.00, 'PLATILLO', true),
       (2, 'Agua Natural', 20.00, 'BEBIDA', true);

INSERT INTO producto_ingredientes (id, producto_id, ingrediente_id, cantidadRequerida)
VALUES (1, 1, 1, 30.0),  
       (2, 1, 2, 20.0),  
       (3, 1, 3, 2.0);  

INSERT INTO DetalleComanda (id, comanda_id, producto_id, cantidad, precioUnitario, notas)
VALUES (1, 1, 1, 1, 50.00, 'Sin mayonesa'),
       (2, 1, 2, 2, 20.00, 'Sin hielo'),
       (3, 2, 1, 2, 50.00, '');
