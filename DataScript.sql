-- Inserts
INSERT INTO Trabajador 
(nombresTrabajador, apePaternoTrabajador, apeMaternoTrabajador, dniTrabajador, sexoTrabajador, direccTrabajador, cellTrabajador, emailTrabajador,nacimientoTrabajador) 
VALUES 
('Juan Manuel', 'Perez', 'Lopez', '89781245', 'Masculino', 'Av. Los Olivos 123', '987654321', 'juan.perez@gmail.com','02/08/2003'),
('Ana Paola', 'Ramires', 'Quispe', '56458525', 'Femenino', 'Av. Los Girasoles 659', '985236554', 'ana.ramires@gmail.com','02/08/2003'),
('Maria Flor', 'Cotrina', 'Nuñes', '65663422', 'Femenino', 'Av. Principal 1002', '984522477', 'maria.cotrina@gmail.com','02/08/2003'),
('Luis Alexander', 'Mendoza', 'Rojas', '25524789', 'Masculino', 'Av. Los Cedros 458', '987451440', 'luis.mendoza@gmail.com','02/08/2003');
select * from trabajador;
select * from contrato;
select * from pago;

INSERT INTO Puesto
(descripPuesto, remuPuesto, horasContrato)
VALUES
('Vigilante', 1400, 240),
('Programador', 2500, 192),
('Gerente', 3000, 240),
('Limpieza', 1400, 240),
('Jefe', 4000, 200);

INSERT INTO Contrato
(idTrabajadorContrato, idPuestoContrato, idContrato, duracionContrato, expContrato, remuContrato, jubiContrato, numHijosContrato)
VALUES
(1,1,1,'Indeterminado','2024-11-24',1200,'AFP',2),
(2,3,4,'Temporada','2024-11-30',3000,'ONP',0),
(3,2,3,'Proyecto','2024-11-30',2200,'AFP',1);

INSERT INTO Periodo
VALUES
('202410','Periodo de Octubre del 2024'),
('202411','Periodo de Noviembre del 2024');

INSERT INTO Pago
(idPagoContrato,horasTrabajadas)
VALUES
(1,200),
(3,190),
(4,150);

INSERT INTO Pago
(idPagoContrato,horasTrabajadas)
VALUES
(1,210),
(3,180),
(4,160);

UPDATE `nomina`.`pago` SET `periodoPago` = '202410', `fechaPago` = '2024-10-20', `pagoMensual` = '1200', `pagoHoraExtra` = '45', `bonificacionHijos` = '200', `totalBonificacion` = '200', `descuentoSalud` = '48', `descuentoPension` = '120', `totalDescuento` = '168', `pagoTotal` = '1277', `estadoPago` = 'Pagado' WHERE (`idPago` = '4');
UPDATE `nomina`.`pago` SET `periodoPago` = '202410', `fechaPago` = '2024-10-15', `pagoMensual` = '2062.5', `bonificacionHijos` = '100', `totalBonificacion` = '100', `descuentoSalud` = '82.5', `descuentoPension` = '206.25', `totalDescuento` = '288.75', `pagoTotal` = '1873.75', `estadoPago` = 'Pagado' WHERE (`idPago` = '5');
UPDATE `nomina`.`pago` SET `periodoPago` = '202410', `fechaPago` = '2024-10-20', `pagoMensual` = '2500', `descuentoSalud` = '100', `descuentoPension` = '325', `totalDescuento` = '425', `pagoTotal` = '2075', `estadoPago` = 'Pagado' WHERE (`idPago` = '6');


SELECT * FROM pago;
SELECT * FROM trabajador;
SELECT * FROM contrato;