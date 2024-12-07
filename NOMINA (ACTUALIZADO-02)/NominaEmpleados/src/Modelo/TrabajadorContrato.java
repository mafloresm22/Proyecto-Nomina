package Modelo;

public class TrabajadorContrato {
    private int idTrabajador;
    private String nombreCompleto;
    private int idContrato;
    private String duracionContrato;
    private int remuContrato;
    private String expContrato;
    private String estado;
    private int idpuesto;
    private String jubilacion;
    private int hijos;
    private String estadoPago;
    private String puesto;
    private String periodo;

    // Constructor vacío
    public TrabajadorContrato() {
    }

    // Getters y Setters
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getDuracionContrato() {
        return duracionContrato;
    }

    public void setDuracionContrato(String duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    public int getRemuContrato() {
        return remuContrato;
    }

    public void setRemuContrato(int remuContrato) {
        this.remuContrato = remuContrato;
    }

    public String getExpContrato() {
        return expContrato;
    }

    public void setExpContrato(String expContrato) {
        this.expContrato = expContrato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(int idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getJubilacion() {
        return jubilacion;
    }

    public void setJubilacion(String jubilacion) {
        this.jubilacion = jubilacion;
    }

    public int getHijos() {
        return hijos;
    }

    public void setHijos(int hijos) {
        this.hijos = hijos;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
