package me.rflores.clienteapp.services;

import me.rflores.clienteapp.models.entities.TarjetaCredito;
import me.rflores.clienteapp.models.entities.TarjetaCreditoRiesgo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class TarjetaCreditoProcessor implements ItemProcessor<TarjetaCredito, TarjetaCreditoRiesgo> {

    @Override
    public TarjetaCreditoRiesgo process(TarjetaCredito tarjeta) {
        long daysBetween = ChronoUnit.DAYS.between(tarjeta.getUltimoPago(), LocalDate.now());
        String riesgo;

        if (daysBetween <= 30) {
            riesgo = "BAJO";
        } else if (daysBetween <= 90) {
            riesgo = "MEDIO";
        } else {
            riesgo = "ALTO";
        }

        TarjetaCreditoRiesgo tarjetaCreditoRiesgo = new TarjetaCreditoRiesgo();
        tarjetaCreditoRiesgo.setFecha(LocalDate.now());
        tarjetaCreditoRiesgo.setRiesgo(riesgo);
        tarjetaCreditoRiesgo.setTarjetaCredito(tarjeta);

        return tarjetaCreditoRiesgo;
    }
}
