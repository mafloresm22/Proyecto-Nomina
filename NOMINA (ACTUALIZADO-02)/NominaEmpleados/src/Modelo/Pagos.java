package Modelo;

public class Pagos {
    
    public double calcularPagoPorHora(double remuneracionBasica) {
        return remuneracionBasica / 192;
    }
    
    public double calcularPagoMensual(int horasTrabajadas, double remuneracionBasica) {
        double pagoHora = calcularPagoPorHora(remuneracionBasica);
        if (horasTrabajadas >= 192) {
            return remuneracionBasica;
        } else {
            return horasTrabajadas * pagoHora;
        }
    }
    
    public double calcularPagoHorasExtra(int horasTrabajadas, double remuneracionBasica) {
        double pagoHora = calcularPagoPorHora(remuneracionBasica);
        if (horasTrabajadas > 192) {
            int horasExtras = horasTrabajadas - 192;
            if (horasExtras >= 40) {
                return horasExtras * (pagoHora * 0.30);
            } else {
                return horasExtras * (pagoHora * 0.40);
            }
        }
        return 0;
    }

    public double calcularBonificacionPorHijos(int numeroDeHijos) {
        return numeroDeHijos * 100;
    }

    public double calcularDescuentoSeguroSalud(int horasTrabajadas, double remuneracionBasica) {
        double pagoMensual = calcularPagoMensual(horasTrabajadas, remuneracionBasica);
        double tasaSeguroSalud = 0.04; 
        return pagoMensual * tasaSeguroSalud;
    }

    public double calcularDescuentoPension(String tipoJubilacion, int horasTrabajadas, double remuneracionBasica) {
        if (tipoJubilacion == null) {
            return 0;
        }

        double pagoMensual = calcularPagoMensual(horasTrabajadas, remuneracionBasica);
        double descuentoPension = 0;
        switch(tipoJubilacion) {
            case "AFP":
                descuentoPension = pagoMensual * 0.10; 
                break;
            case "ONP":
                descuentoPension = pagoMensual * 0.13; 
                break;
            default:
                break;
        }
        return descuentoPension;
    }

    public double calcularSueldoNeto(String tipoJubilacion, int horasTrabajadas, double remuneracionBasica, int numeroDeHijos) {
        double pagoMensual = calcularPagoMensual(horasTrabajadas, remuneracionBasica);
        double pagoHorasExtra = calcularPagoHorasExtra(horasTrabajadas, remuneracionBasica);
        double bonificacionEscolaridad = calcularBonificacionPorHijos(numeroDeHijos);
        double descuentoPension = calcularDescuentoPension(tipoJubilacion, horasTrabajadas, remuneracionBasica);
        double descuentoSeguroSalud = calcularDescuentoSeguroSalud(horasTrabajadas, remuneracionBasica);
        double totalIngresos = pagoMensual + pagoHorasExtra + bonificacionEscolaridad;
        double totalDescuentos = descuentoPension + descuentoSeguroSalud;
        return totalIngresos - totalDescuentos;
    }
}
