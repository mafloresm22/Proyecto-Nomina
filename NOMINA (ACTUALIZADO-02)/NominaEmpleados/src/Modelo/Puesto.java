/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author user
 */
public class Puesto {
    private int idPuesto;
    private String descripPuesto;
    private float remuPuesto;
    private String estadoPuesto;

    public Puesto(){
        
    }
    public Puesto(int idPuesto, String descripPuesto, float remuPuesto, String estadoPuesto) {
        this.idPuesto = idPuesto;
        this.descripPuesto = descripPuesto;
        this.remuPuesto = remuPuesto;
        this.estadoPuesto = estadoPuesto;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getDescripPuesto() {
        return descripPuesto;
    }

    public void setDescripPuesto(String descripPuesto) {
        this.descripPuesto = descripPuesto;
    }

    public float getRemuPuesto() {
        return remuPuesto;
    }

    public void setRemuPuesto(float remuPuesto) {
        this.remuPuesto = remuPuesto;
    }

    public String getEstadoPuesto() {
        return estadoPuesto;
    }

    public void setEstadoPuesto(String estadoPuesto) {
        this.estadoPuesto = estadoPuesto;
    }


}

