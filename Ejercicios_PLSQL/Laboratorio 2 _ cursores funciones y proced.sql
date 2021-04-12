/*Laboratorio 2 - Cursores, funciones y procedimientos*/
/*1:Crear un cursor que muestre todos los clientes que no hayan realizado pagos.
implementar for*/

SET SERVEROUTPUT ON
DECLARE
  CURSOR c_clientes_sin_pagos IS
    SELECT nombrecliente 
    FROM clientes c
    WHERE NOT EXISTS(SELECT codigocliente FROM pagos WHERE codigocliente = c.codigocliente);
BEGIN
 
  FOR REGISTRO IN c_clientes_sin_pagos LOOP /* for numérico */
   
    DBMS_OUTPUT.PUT_LINE(REGISTRO.nombrecliente);
   
  END LOOP;
 
END;


/*2:Crear una función que devuelva la suma de pagos que realizó un cliente dado.
pasar por param el codigo. considerar cuando no se encuentre registros.*/

CREATE OR REPLACE FUNCTION f_pagos_cliente(v_codigocliente clientes.codigocliente%TYPE)
RETURN NUMBER
AS
  v_sumapagos pagos.cantidad%TYPE := 0; /*Declaraciones locales*/
BEGIN
   
  SELECT SUM(cantidad) INTO v_sumapagos
  FROM PAGOS
  WHERE codigocliente = v_codigocliente;
 
  IF v_sumapagos IS NULL THEN /* es null*/
    RAISE NO_DATA_FOUND; /*raise*/
  ELSE
    RETURN v_sumapagos;
  END IF;

EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN -1;
 
END;

 /*Uso de la función anterior*/
DECLARE
  v_codigocliente clientes.codigocliente%TYPE := &codigo;
  v_suma pagos.cantidad%TYPE;
BEGIN
  v_suma := f_pagos_cliente(v_codigocliente);
  IF v_suma = -1 THEN
    DBMS_OUTPUT.PUT_LINE('El cliente no existe');
  ELSE
    DBMS_OUTPUT.PUT_LINE('La suma de pagos es ' || v_suma);
  END IF;
   
END;

SELECT codigocliente, cantidad FROM pagos WHERE codigocliente=5
SELECT codigocliente  FROM pagos


/*3:Realizar un método o procedimiento que muestre el total en euros de un pedido.
codigo pasado por parametro.*/

CREATE OR REPLACE PROCEDURE p_total_pedido(v_codigopedido pedidos.codigopedido%TYPE)
AS /*dif. is*/
  v_total number(8) := 0;
BEGIN
  
  SELECT SUM(dp.cantidad * dp.preciounidad) INTO v_total
  FROM pedidos p, detallepedidos dp
  WHERE p.codigopedido = dp.codigopedido AND p.codigopedido = v_codigopedido;
 
  DBMS_OUTPUT.PUT_LINE('El pedido total es ' || v_total);
 
END;


 /*Uso del procedimiento anterior*/
DECLARE
  v_codigopedido pedidos.codigopedido%TYPE := &codigo;
BEGIN
  p_total_pedido(v_codigopedido);
END;

SELECT codigopedido,cantidad, preciounidad FROM detallepedidos WHERE codigopedido=26

/*4: Realizar un procedimiento que muestre el total en euros de un pedido. 
codigo pasado por param. considerar caso de que no se encuentren registros.
pasar un segundo param que va a ser el limite y si supera ese limite lanzamos 
una excepcion propia y devolveremos un 0.
*/

CREATE OR REPLACE PROCEDURE p_total_pedido_limite
             (v_codigopedido pedidos.codigopedido%TYPE, v_limite number)
AS
  v_total number(8) := 0;
  limite_superado EXCEPTION;/*Declara una excepción*/
BEGIN
   
  SELECT SUM(dp.cantidad * dp.preciounidad) INTO v_total
  FROM pedidos p, detallepedidos dp
  WHERE p.codigopedido = dp.codigopedido AND p.codigopedido = v_codigopedido;
   
  IF V_TOTAL IS NULL THEN
    RAISE NO_DATA_FOUND; /*Excepcion predefinida por oracle*/
  ELSE
    IF V_TOTAL > V_LIMITE  THEN
      RAISE limite_superado;/*Provoca la excepción*/
    ELSE
      DBMS_OUTPUT.PUT_LINE('El pedido total es ' || v_total);
    
    END IF;
  END IF;
   
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('No existe el pedido');
  WHEN limite_superado THEN 
    DBMS_OUTPUT.PUT_LINE('Límite superado ' || 0);
  END;


/*Uso del procedimiento anterior*/
DECLARE
  v_codigopedido pedidos.codigopedido%TYPE := &codigo; /*&*/
  v_limite number(8) := &limite;
BEGIN
  p_total_pedido_limite(v_codigopedido , v_limite);
 
END;
