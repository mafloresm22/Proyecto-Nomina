package modelo;

public class Contrato {
    private int idContrato;
    private String duracionContrato;
    private int remuContrato;
    private String expContrato;
    private int idTrabajador;
   

    // Constructor con parámetros

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

    public double getRemuContrato() {
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

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }


}
