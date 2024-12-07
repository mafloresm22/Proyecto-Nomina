-- Procedimientos Almacenados Usuario 1

DELIMITER $$
CREATE PROCEDURE sp_listarTrabajadores()
BEGIN
    SELECT * FROM Trabajador;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_crearTrabajador(
    IN p_nombres VARCHAR(100),
    IN p_apePaterno VARCHAR(45),
    IN p_apeMaterno VARCHAR(45),
    IN p_dni VARCHAR(8),
    IN p_sexo VARCHAR(45),
    IN p_direcc VARCHAR(100),
    IN p_cell VARCHAR(45),
    IN p_email VARCHAR(100),
    IN p_nacimiento VARCHAR(100)
)
BEGIN
    INSERT INTO Trabajador (nombresTrabajador, apePaternoTrabajador, apeMaternoTrabajador, dniTrabajador, sexoTrabajador, direccTrabajador, cellTrabajador, emailTrabajador,nacimientoTrabajador)
    VALUES (p_nombres, p_apePaterno, p_apeMaterno, p_dni, p_sexo, p_direcc, p_cell, p_email, p_nacimiento);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_actualizarTrabajador(
    IN p_id INT,
    IN p_nombres VARCHAR(100),
    IN p_apePaterno VARCHAR(45),
    IN p_apeMaterno VARCHAR(45),
    IN p_dni VARCHAR(8),
    IN p_sexo VARCHAR(45),
    IN p_direcc VARCHAR(100),
    IN p_cell VARCHAR(45),
    IN p_email VARCHAR(100),
    IN p_nacimiento VARCHAR(100)
)
BEGIN
    UPDATE Trabajador 
    SET nombresTrabajador = p_nombres,
        apePaternoTrabajador = p_apePaterno,
        apeMaternoTrabajador = p_apeMaterno,
        dniTrabajador = p_dni,
        sexoTrabajador = p_sexo,
        direccTrabajador = p_direcc,
        cellTrabajador = p_cell,
        emailTrabajador = p_email,
        nacimientoTrabajador =p_nacimiento
    WHERE idTrabajador = p_id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarTrabajador(IN p_id INT)
BEGIN
    UPDATE Trabajador
    SET estadoTrabajador = 'Deshabilitado'
    WHERE idTrabajador = p_id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarTrabajadorPorID(IN id INT)
BEGIN
    SELECT idTrabajador, nombresTrabajador, apePaternoTrabajador, apeMaternoTrabajador, dniTrabajador, sexoTrabajador, direccTrabajador, cellTrabajador, emailTrabajador, estadoTrabajador,nacimientoTrabajador
    FROM Trabajador 
    WHERE idTrabajador = id;
END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_buscarTrabajadorPorNombreApellido(IN search VARCHAR(100))
BEGIN
    SELECT idTrabajador, nombresTrabajador, apePaternoTrabajador, apeMaternoTrabajador, dniTrabajador, sexoTrabajador, direccTrabajador, cellTrabajador, emailTrabajador, estadoTrabajador , nacimientoTrabajador
    FROM Trabajador
    WHERE nombresTrabajador LIKE CONCAT('%', search, '%')
       OR apePaternoTrabajador LIKE CONCAT('%', search, '%')
       OR apeMaternoTrabajador LIKE CONCAT('%', search, '%');
END $$

DELIMITER ;

-- Procedimientos Almacenados Usuario 2

-- Listar Trabajadores con Contrato Habilitado --
DELIMITER $$
CREATE PROCEDURE sp_listarTrabajadoresConContrato(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT 
        t.idTrabajador,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        c.idContrato,
        c.duracionContrato,
        c.remuContrato,
        c.expContrato,
        c.estadoContrato
    FROM Trabajador t
    JOIN Contrato c ON t.idTrabajador = c.idTrabajadorContrato
    WHERE 
        (t.nombresTrabajador LIKE CONCAT('%', p_busqueda, '%') 
        OR t.apePaternoTrabajador LIKE CONCAT('%', p_busqueda, '%') 
        OR t.apeMaternoTrabajador LIKE CONCAT('%', p_busqueda, '%'))
        AND c.estadoContrato = 'Habilitado';
END $$
DELIMITER ;

-- Listar Trabajadores sin Contrato o Contrato Deshabilitado --
DELIMITER //
CREATE PROCEDURE sp_listarTrabajadoresSinContrato(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT 
        t.idTrabajador,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        IFNULL(c.idContrato, 0) AS idContrato,
        c.duracionContrato,
        c.remuContrato,
        c.expContrato,
        IFNULL(c.estadoContrato, 'Sin Contrato') AS estadoContrato
    FROM 
        Trabajador t
    LEFT JOIN 
        Contrato c ON t.idTrabajador = c.idTrabajadorContrato
    WHERE 
        (t.nombresTrabajador LIKE CONCAT('%', p_busqueda, '%') 
        OR t.apePaternoTrabajador LIKE CONCAT('%', p_busqueda, '%') 
        OR t.apeMaternoTrabajador LIKE CONCAT('%', p_busqueda, '%'))
        AND (c.idContrato IS NULL OR c.estadoContrato <> 'Deshabilitado')
        AND t.estadoTrabajador <> 'Deshabilitado'; -- Agregada esta condición
END //
DELIMITER ;


-- Crear Nuevo Contrato --
DELIMITER //
CREATE PROCEDURE sp_crearNuevoContrato(
    IN p_idTrabajador INT,
    IN p_idPuesto INT,
    IN p_duracionContrato VARCHAR(45),
    IN p_expContrato DATE,
    IN p_remuContrato INT,
    IN p_jubiContrato VARCHAR(45),
    IN p_numHijosContrato INT
)
BEGIN
    INSERT INTO Contrato 
    (idTrabajadorContrato, idPuestoContrato, duracionContrato, expContrato, remuContrato, jubiContrato, numHijosContrato)
    VALUES 
    (p_idTrabajador, p_idPuesto, p_duracionContrato, p_expContrato, p_remuContrato, p_jubiContrato, p_numHijosContrato);
END //
DELIMITER ;

-- Editar Contrato segun idTrabajador --
DELIMITER //
CREATE PROCEDURE sp_editarContratoPorTrabajador(
    IN p_idTrabajador INT,
    IN p_idPuesto INT,
    IN p_duracionContrato VARCHAR(45),
    IN p_expContrato DATE,
    IN p_remuContrato INT,
    IN p_jubiContrato VARCHAR(45),
    IN p_numHijosContrato INT
)
BEGIN
    UPDATE Contrato
    SET 
        idPuestoContrato = p_idPuesto,
        duracionContrato = p_duracionContrato,
        expContrato = p_expContrato,
        remuContrato = p_remuContrato,
        jubiContrato = p_jubiContrato,
        numHijosContrato = p_numHijosContrato
    WHERE 
        idTrabajadorContrato = p_idTrabajador;
END //
DELIMITER ;

-- Procedimiento para Finalizar Contrato ---
DELIMITER //
CREATE PROCEDURE sp_finalizarContrato(
    IN p_idContrato INT
)
BEGIN
    -- Modificar la fecha de expiración a una fecha anterior al día actual
    UPDATE Contrato
    SET 
--        expContrato = CURDATE() - INTERVAL 1 DAY, -- Cambiar la fecha de expiración
        estadoContrato = 'Deshabilitado'         -- Cambiar el estado del contrato a deshabilitado
    WHERE 
        idContrato = p_idContrato;
END //
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_listarPuestos()
BEGIN
    SELECT 
        idPuesto, 
        descripPuesto, 
        remuPuesto, 
        estadoPuesto
    FROM 
        Puesto
        where estadoPuesto = 'Habilitado';
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_obtenerRemuneracion(IN p_idPuesto INT)
BEGIN
    SELECT remuPuesto 
    FROM Puesto 
    WHERE idPuesto = p_idPuesto;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_obtenerDatosTrabajadorContrato(IN idTrabajador INT)
BEGIN
    SELECT t.idTrabajador, c.idContrato,c.idPuestoContrato,t.nombresTrabajador ,c.duracionContrato,c.jubiContrato, c.remuContrato, c.expContrato, c.estadoContrato ,c.numHijosContrato
    FROM trabajador t
    JOIN contrato c ON t.idTrabajador = c.idTrabajadorContrato
    JOIN puesto p on p.idPuesto = c.idPuestoContrato
    WHERE t.idTrabajador = idTrabajador;
END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_editarContrato(
    IN p_idTrabajador INT,
    IN p_idContrato VARCHAR(45),
    IN p_duracionContrato VARCHAR(45),
    IN p_remuContrato DOUBLE,
    IN p_expContrato DATE,
    IN p_estado VARCHAR(45),
    IN p_idPuesto INT,
    IN p_jubilacion VARCHAR(45),
    IN p_numHijos INT
)
BEGIN
    DECLARE v_contrato_exists INT;
    
    -- Verificar si existe el contrato
    SELECT COUNT(*) INTO v_contrato_exists
    FROM Contrato 
    WHERE idTrabajadorContrato = p_idTrabajador;
    
    IF v_contrato_exists > 0 THEN
        -- Actualizar el contrato existente
        UPDATE Contrato
        SET 
            duracionContrato = p_duracionContrato,
            remuContrato = p_remuContrato,
            expContrato = p_expContrato,
            estadoContrato = p_estado,
            idPuestoContrato = p_idPuesto,
            jubiContrato = p_jubilacion,
            numHijosContrato = p_numHijos
        WHERE idTrabajadorContrato = p_idTrabajador;
        
        -- Verificar si la actualización fue exitosa
        IF ROW_COUNT() > 0 THEN
            -- Devolver mensaje de éxito
            SELECT 'Contrato actualizado exitosamente' AS mensaje, 1 AS resultado;
        ELSE
            -- No se afectó ninguna fila
            SELECT 'No se realizaron cambios en el contrato' AS mensaje, 0 AS resultado;
        END IF;
    ELSE
        -- Devolver mensaje de error si no se encuentra el contrato
        SELECT 'No se encontró el contrato para el trabajador especificado' AS mensaje, 0 AS resultado;
    END IF;
END $$

DELIMITER ;

-- PROCESAR PAGO
-- Listar Trabajadores con Contrato Habilitado y Puesto --
DELIMITER $$
CREATE PROCEDURE sp_listarTrabajadoresConContratoPago()
BEGIN
    SELECT 
        c.idContrato,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        p.descripPuesto,
        c.estadoContrato,
        c.estadoPagado,
        pa.periodoPago
    FROM Contrato c
    JOIN Trabajador t ON c.idTrabajadorContrato = t.idTrabajador
    JOIN Puesto p ON c.idPuestoContrato = p.idPuesto
    join pago pa on c.idContrato = pa.idPagoContrato;
END $$
DELIMITER ;

-- Listar Trabajadores con Contrato Habilitado y Puesto (según periodo)--
DELIMITER $$
CREATE PROCEDURE sp_listarTrabajadoresConContratoPagoPeriodo(in pa_periodo varchar(6))
BEGIN
    SELECT 
        c.idContrato,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        p.descripPuesto,
        c.estadoContrato,
        c.estadoPagado,
        pa.periodoPago
    FROM Contrato c
    JOIN Trabajador t ON c.idTrabajadorContrato = t.idTrabajador
    JOIN Puesto p ON c.idPuestoContrato = p.idPuesto
    join pago pa on c.idContrato = pa.idPagoContrato
    where pa.periodoPago like pa_periodo;
END $$
DELIMITER ;


-- BUSCAR TRABAJADOR CON CONTRATO PAGO --
DELIMITER $$
CREATE PROCEDURE sp_buscarTrabajadoresConContratoPago(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT 
        t.idTrabajador,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        c.idContrato,
        c.duracionContrato,
        c.remuContrato,
        c.expContrato,
        c.estadoContrato,
        p.descripPuesto,  
        c.estadoPagado,
        pa.periodoPago  
    FROM Trabajador t
    JOIN Contrato c ON t.idTrabajador = c.idTrabajadorContrato
    JOIN Puesto p ON c.idPuestoContrato = p.idPuesto
    join pago pa on c.idContrato = pa.idPagoContrato
    WHERE 
        (t.nombresTrabajador LIKE CONCAT('%', p_busqueda, '%') 
        OR t.apePaternoTrabajador LIKE CONCAT('%', p_busqueda, '%') 
        OR t.apeMaternoTrabajador LIKE CONCAT('%', p_busqueda, '%'))
        AND c.estadoContrato = 'Habilitado';
END $$
DELIMITER ;

-- Procedimiento para Verificar Estado de Pago --
DELIMITER $$
CREATE PROCEDURE sp_verificarEstadoPago(
    IN p_idContrato INT
)
BEGIN
    SELECT estadoPagado
    FROM Contrato
    WHERE idContrato = p_idContrato;
END $$
DELIMITER ;

-- Procedimiento para obtener datos del trabajador de Pago --
DELIMITER $$
CREATE PROCEDURE sp_obtenerDatosTrabajadorPorContratoPago(IN p_idContrato INT)
BEGIN
    SELECT 
        t.idTrabajador,
        CONCAT(t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador, ' ', t.nombresTrabajador) AS nombreCompleto,
        c.idContrato
    FROM Trabajador t
    JOIN Contrato c ON t.idTrabajador = c.idTrabajadorContrato
    WHERE c.idContrato = p_idContrato;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_obtenerIdContratoPorTrabajador(IN p_idTrabajador INT)
BEGIN
    SELECT idContrato
    FROM Contrato
    WHERE idTrabajadorContrato = p_idTrabajador
    LIMIT 1;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_procesarPago(
    IN p_idContrato INT,
    in p_periodo varchar(6),
    IN p_pagoMensual DECIMAL(10, 2),
    IN p_pagoHorasExtra DECIMAL(10, 2),
    IN p_bonificacionHijos DECIMAL(10, 2),
    IN p_totalBonificacion DECIMAL(10, 2),
    IN p_descuentoSalud DECIMAL(10, 2),
    IN p_descuentoPension DECIMAL(10, 2),
    IN p_totalDescuento DECIMAL(10, 2),
    IN p_pagoTotal DECIMAL(10, 2)
)
BEGIN
    UPDATE Pago 
    SET pagoMensual = p_pagoMensual, 
        pagoHoraExtra = p_pagoHorasExtra, 
        bonificacionHijos = p_bonificacionHijos, 
        totalBonificacion = p_totalBonificacion, 
        descuentoSalud = p_descuentoSalud, 
        descuentoPension = p_descuentoPension, 
        totalDescuento = p_totalDescuento, 
        pagoTotal = p_pagoTotal, 
        estadoPago = 'Pagado', 
        fechaPago = CURDATE()
    WHERE idPagoContrato = p_idContrato and periodoPago like p_periodo;

    UPDATE Contrato
    SET estadoPagado = 'Pagado'
    WHERE idContrato = p_idContrato;
END $$
DELIMITER ;





DELIMITER $$
CREATE PROCEDURE sp_mostrarDetallePago(
    IN p_idContrato INT,
    in p_periodo varchar(6)
)
BEGIN
    SELECT 
        p.idPago,
        p.idPagoContrato,
        p.periodoPago,
        p.fechaPago,
        p.horasTrabajadas,
        p.pagoMensual,
        p.pagoHoraExtra,
        p.bonificacionHijos,
        p.totalBonificacion,
        p.descuentoSalud,
        p.descuentoPension,
        p.totalDescuento,
        p.pagoTotal,
        p.estadoPago
    FROM Pago p
    WHERE p.idPagoContrato = p_idContrato and p.periodoPago like p_periodo;
END $$
DELIMITER ;



DELIMITER $$
CREATE PROCEDURE sp_obtenerDatosTrabajadorPagos(
    IN p_idContrato INT
)
BEGIN
    SELECT 
        t.idTrabajador,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        c.remuContrato,
        c.jubiContrato,
        c.numHijosContrato
    FROM Trabajador t
    JOIN Contrato c ON t.idTrabajador = c.idTrabajadorContrato
    WHERE c.idContrato = p_idContrato;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_registrarPago(
    IN p_idContrato INT
)
BEGIN
    INSERT INTO Pago (idPagoContrato, horasTrabajadas, estadoPago)
    VALUES (p_idContrato, 0, 'No Pagado');
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_listarPeriodos()
BEGIN
    SELECT idperiodo FROM Periodo;
END $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_actualizarEstadoContrato(
    IN p_idContrato INT
)
BEGIN
    UPDATE Contrato
    SET estadoPagado = 'Pagado'
    WHERE idContrato = p_idContrato;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_obtenerDatosTrabajadorPorContratoProcesoPago(
    IN p_idContrato INT
)
BEGIN
    SELECT 
        t.idTrabajador,
        CONCAT(t.nombresTrabajador, ' ', t.apePaternoTrabajador, ' ', t.apeMaternoTrabajador) AS nombreCompleto,
        c.remuContrato,
        c.jubiContrato,
        c.numHijosContrato
    FROM Contrato c
    JOIN Trabajador t ON c.idTrabajadorContrato = t.idTrabajador
    WHERE c.idContrato = p_idContrato;
END $$
DELIMITER ;

