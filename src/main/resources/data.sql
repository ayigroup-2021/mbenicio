
INSERT INTO Detalle (id, descripcion) VALUES
    (1, 'Azucar tipo AAA'),
    (2, 'Aceite sin gno.'),
    (3, 'Lavandina con extra aditivos.');

INSERT INTO Proveedor (id, nombre) VALUES
    (1, 'Martinez S.A'),
    (2, 'Yumbo S.R.L.'),
    (3, 'Galeno Hnos.');

INSERT INTO Producto (id, nombre, proveedor_id, detalle_id) VALUES
    (1, 'Azucar', 3, 1),
    (2, 'Aceite', 3, 2),
    (3, 'Lavandina', 2, 3);

    


