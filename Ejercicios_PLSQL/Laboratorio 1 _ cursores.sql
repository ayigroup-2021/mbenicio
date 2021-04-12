/*Laboratorio 1 - Cursores*/
/*1: Cursor con clientes que hayan realizado pedidos con fecha menor a la recibida
por parametro. Muestra nombre del cliente y fecha de pedido.*/
SET SERVEROUTPUT ON
DECLARE
	db_nombre clientes.nombrecliente%TYPE;
	db_fechapedido pedidos.fechapedido%TYPE;

	CURSOR c_clientes_pedido_fecha(v_fecha pedidos.fechapedido%TYPE) IS
		SELECT c.nombrecliente, p.fechapedido
		FROM clientes c INNER JOIN pedidos p ON c.codigocliente = p.codigocliente
		WHERE p.fechapedido < v_fecha;
        
/*Uso del cursor anterior*/
BEGIN
	OPEN c_clientes_pedido_fecha(to_date('22/12/08','DD/MM/RR'));
	LOOP 
		FETCH c_clientes_pedido_fecha INTO db_nombre,db_fechapedido;
        DBMS_OUTPUT.PUT_LINE(db_nombre || ' - '|| db_fechapedido);
		EXIT WHEN c_clientes_pedido_fecha%NOTFOUND;
    END LOOP;
	CLOSE c_clientes_pedido_fecha;
    
END;

/*2: Cursor con clientes que hayan realizado pagos que superen el promedio.
Muestra nombre del cliente y pago realizado.*/

SET SERVEROUTPUT ON
DECLARE
    db_codigo clientes.codigocliente%TYPE;
	db_nombre clientes.nombrecliente%TYPE;
	db_pago pagos.cantidad%TYPE;
       
    CURSOR c_clientes_pago_promedio IS
		SELECT c.codigocliente,c.nombrecliente, SUM(p.cantidad)
		FROM clientes c INNER JOIN pagos p ON c.codigocliente = p.codigocliente 
 		WHERE p.cantidad < (SELECT AVG (cantidad) FROM pagos )
        GROUP BY c.codigocliente, c.nombrecliente;

/*Uso del cursor anterior*/
BEGIN
	OPEN c_clientes_pago_promedio;
	LOOP 
		FETCH c_clientes_pago_promedio INTO db_codigo,db_nombre,db_pago;
        DBMS_OUTPUT.PUT_LINE(db_nombre || ' - '|| db_pago);
		EXIT WHEN c_clientes_pago_promedio%NOTFOUND; -- me repite el ultimo registro?
    END LOOP;
	CLOSE c_clientes_pago_promedio;
    
END;

/*Select para prueba.*/
/*
SELECT AVG (cantidad)  FROM pagos

SELECT CODIGOCLIENTE, NOMBRECLIENTE FROM CLIENTES
SELECT c.codigocliente,c.nombrecliente, SUM(p.cantidad)
		FROM clientes c INNER JOIN pagos p ON c.codigocliente = p.codigocliente
        WHERE p.cantidad < (SELECT AVG (cantidad) FROM pagos )
        GROUP BY c.codigocliente, c.nombrecliente;
*/
   
/*3: Cursor que muestre nombre de los empleados y
el pais del  codigo de oficina que coincida con el codigo pasado por parametro.*/
DECLARE
	db_nombre empleados.nombre%TYPE;
	db_oficina oficinas.codigooficina%TYPE;
    db_puesto empleados.puesto%TYPE;
    db_codigopostal oficinas.codigopostal%TYPE;

	CURSOR c_empleados_oficina(v_oficina oficinas.codigooficina%TYPE) IS
		SELECT e.nombre, o.codigooficina, e.puesto, o.codigopostal
		FROM empleados e INNER JOIN oficinas o ON e.codigooficina = o.codigooficina
		WHERE o.codigooficina = v_oficina;

/*Uso del cursor anterior*/
BEGIN
	OPEN c_empleados_oficina('BOS-USA');
	LOOP 
		FETCH c_empleados_oficina INTO db_nombre,db_oficina,db_puesto,db_codigopostal;
        DBMS_OUTPUT.PUT_LINE(db_nombre || ' - '|| db_oficina || ' - ' || db_puesto || ' - ' db_codigopostal);
	   	EXIT WHEN c_empleados_oficina%NOTFOUND;
    END LOOP;
	CLOSE c_empleados_oficina;
    
END;

/*Select para prueba.*/
/*SELECT codigooficina FROM oficinas*/ 