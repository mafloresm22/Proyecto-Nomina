package Modelo;

import java.util.Date;

public class Pago {
    private int idPago;
    private int idPagoContrato;
    private String periodoPago;
    private Date fechaPago;
    private int horasTrabajadas;
    private double pagoMensual;
    private double pagoHorasExtra;
    private double bonificacionHijos;
    private double totalBonificacion;
    private double descuentoSalud;
    private double descuentoPension;
    private double totalDescuento;
    private double pagoTotal;
    private String estadoPago;

    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdPagoContrato() {
        return idPagoContrato;
    }

    public void setIdPagoContrato(int idPagoContrato) {
        this.idPagoContrato = idPagoContrato;
    }

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public double getPagoHorasExtra() {
        return pagoHorasExtra;
    }

    public void setPagoHorasExtra(double pagoHorasExtra) {
        this.pagoHorasExtra = pagoHorasExtra;
    }

    public double getBonificacionHijos() {
        return bonificacionHijos;
    }

    public void setBonificacionHijos(double bonificacionHijos) {
        this.bonificacionHijos = bonificacionHijos;
    }

    public double getTotalBonificacion() {
        return totalBonificacion;
    }

    public void setTotalBonificacion(double totalBonificacion) {
        this.totalBonificacion = totalBonificacion;
    }

    public double getDescuentoSalud() {
        return descuentoSalud;
    }

    public void setDescuentoSalud(double descuentoSalud) {
        this.descuentoSalud = descuentoSalud;
    }

    public double getDescuentoPension() {
        return descuentoPension;
    }

    public void setDescuentoPension(double descuentoPension) {
        this.descuentoPension = descuentoPension;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
