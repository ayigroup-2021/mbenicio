/* Laboratorio 3

Trigger
-------
After Update, en tabla clientes: Evaluar si el cliente posee pagos anteriores.
Imprimir: este cliente posee o no pagos.

Excepciones
-----------
Agregar excepciones al triger anterior.
*/

SET SERVEROUTPUT ON;

/*Función usada para determinar si el cliente tiene pagos*/
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


/*Trigger*/
CREATE OR REPLACE TRIGGER t_cliente_pagos
AFTER UPDATE ON clientes
FOR EACH ROW
DECLARE
    posee_pagos NUMBER; 
BEGIN
    /*Llama a la función anterior f_pagos_cliente*/
    posee_pagos := f_pagos_cliente(:OLD.codigocliente);
    
    IF (posee_pagos != -1 )
    THEN
        DBMS_OUTPUT.PUT_LINE('El cliente ' || :OLD.codigocliente  || 
            ' posee pagos anteriores.');
        
    ELSE
        RAISE NO_DATA_FOUND;
    END IF;
    
/*Excepción*/
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('El cliente no posee pagos anteriores.');
END;