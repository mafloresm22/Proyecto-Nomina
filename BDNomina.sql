CREATE SCHEMA IF NOT EXISTS Nomina DEFAULT CHARACTER SET utf8 ;
USE Nomina;

CREATE TABLE IF NOT EXISTS Trabajador (
  idTrabajador INT NOT NULL AUTO_INCREMENT,
  nombresTrabajador VARCHAR(100) NOT NULL,
  apePaternoTrabajador VARCHAR(45) NOT NULL,
  apeMaternoTrabajador VARCHAR(45) NOT NULL,
  dniTrabajador VARCHAR(8) NOT NULL,
  sexoTrabajador VARCHAR(9) NOT NULL,
  direccTrabajador VARCHAR(100) NOT NULL,
  cellTrabajador VARCHAR(9) NOT NULL,
  emailTrabajador VARCHAR(100) NOT NULL,
  nacimientoTrabajador VARCHAR(10) not null,
  estadoTrabajador VARCHAR(13) DEFAULT 'Habilitado',
  PRIMARY KEY (idTrabajador),
  UNIQUE INDEX dniTrabajador_UNIQUE (dniTrabajador ASC)
  );

CREATE TABLE IF NOT EXISTS Puesto (
  idPuesto INT NOT NULL AUTO_INCREMENT,
  descripPuesto VARCHAR(45) NOT NULL,
  remuPuesto INT NOT NULL,
  horasContrato int not null,
  estadoPuesto VARCHAR(13) NOT NULL DEFAULT 'Habilitado',
  PRIMARY KEY (idPuesto)
  );

CREATE TABLE IF NOT EXISTS Contrato (
  idContrato INT NOT NULL AUTO_INCREMENT,
  idTrabajadorContrato INT NOT NULL,
  idPuestoContrato INT NOT NULL,
  duracionContrato VARCHAR(13) NOT NULL,
  expContrato date NOT NULL,
  remuContrato INT NOT NULL,
  jubiContrato VARCHAR(3) NOT NULL,
  numHijosContrato INT NOT NULL DEFAULT 0,
  estadoContrato VARCHAR(13) NOT NULL DEFAULT 'Habilitado',
  estadoPagado VARCHAR(9) NOT NULL DEFAULT 'No Pagado',
  PRIMARY KEY (idContrato),
  INDEX `fk_Contrato_Trabajador_idx` (idTrabajadorContrato ASC) VISIBLE,
  INDEX `fk_Contrato_Puesto_idx` (idPuestoContrato ASC) VISIBLE,
  CONSTRAINT `fk_Contrato_Trabajador`
    FOREIGN KEY (idTrabajadorContrato)
    REFERENCES Trabajador (idTrabajador)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Puesto`
    FOREIGN KEY (idPuestoContrato)
    REFERENCES Puesto (idPuesto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS Periodo(
   idperiodo VARCHAR(6) NOT NULL,
   periodoDescrip varchar(50) not null,
   primary key (idperiodo));
    
CREATE TABLE IF NOT EXISTS Pago (
  idPago INT NOT NULL auto_increment,
  idPagoContrato INT NOT NULL,
  periodoPago varchar(6),
  fechaPago date null,
  horasTrabajadas int null,
  pagoMensual decimal(8,2) null default 0,
  pagoHoraExtra decimal(8,2) null default 0,
  bonificacionHijos decimal(8,2) null default 0,
  totalBonificacion decimal(8,2) null default 0,
  descuentoSalud decimal(8,2) null default 0,
  descuentoPension decimal(8,2) null default 0,
  totalDescuento decimal(8,2) null default 0,
  pagoTotal decimal(8,2) null default 0,
  estadoPago VARCHAR(9) NOT NULL DEFAULT 'No Pagado',
  primary key (idPago),
  constraint fk_Pago_Contrato
	foreign key (idPagoContrato)
    references Contrato (idContrato)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    constraint fk_Pago_Periodo
	foreign key (periodoPago)
    references Periodo (idperiodo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
-- Con esto habilitamos los eventos
SET GLOBAL event_scheduler = ON;

-- Este evento es para que cada dia a la fecha de expContrato se le sume 1 dia mas cuando
-- cumple con las condiciones de que este habilitado y que sea Indeterminado
DELIMITER $$

CREATE EVENT IF NOT EXISTS aumentar_fecha_expContrato
ON SCHEDULE EVERY 1 DAY
STARTS '2024-10-20 23:25:00'
DO
BEGIN
    UPDATE Nomina.Contrato
    SET expContrato = DATE_ADD(expContrato, INTERVAL 1 DAY)
    WHERE duracionContrato = 'Indeterminado' AND estadoContrato = 'Habilitado';
END $$

DELIMITER ;

-- Este evento es cambiar el estado del Contrato cuando expContrato sea menor a
-- la fecha actual
DELIMITER $$

CREATE EVENT IF NOT EXISTS actualizar_estado_contrato
ON SCHEDULE EVERY 1 DAY
STARTS '2024-10-20 23:25:00'
DO
BEGIN
    UPDATE Nomina.Contrato
    SET estadoContrato = 'Terminado'
    WHERE expContrato < CURDATE() AND estadoContrato = 'Habilitado';
END $$

DELIMITER ;

-- Este evento es cambiar el estado del Pago en el contrato cada mes
DELIMITER $$

CREATE EVENT IF NOT EXISTS reset_estadoPagado
ON SCHEDULE EVERY 1 MONTH
STARTS '2023-11-22 00:00:00'
DO
BEGIN
	UPDATE Contrato SET estadoPagado = 'No Pagado'
	WHERE estadoContrato = 'Habilitado';
END $$

DELIMITER ;

-- Trigger necesario para los periodos de pagos
DELIMITER $$

CREATE TRIGGER periodo_pago
BEFORE INSERT ON Pago
FOR EACH ROW
BEGIN
  SET NEW.periodoPago = DATE_FORMAT(CURDATE(), '%Y%m');
END$$

DELIMITER ; 

