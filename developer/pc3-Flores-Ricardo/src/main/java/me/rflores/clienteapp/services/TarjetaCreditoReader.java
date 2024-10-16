package me.rflores.clienteapp.services;

import me.rflores.clienteapp.models.daos.TarjetaCreditoRepository;
import me.rflores.clienteapp.models.entities.TarjetaCredito;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarjetaCreditoReader implements ItemReader<TarjetaCredito> {

    private final TarjetaCreditoRepository tarjetaCreditoRepository;
    private List<TarjetaCredito> tarjetas;
    private int currentIndex = 0;

    public TarjetaCreditoReader(TarjetaCreditoRepository tarjetaCreditoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
    }

    @Override
    public TarjetaCredito read() {
        if (tarjetas == null) {
            tarjetas = tarjetaCreditoRepository.findAll();
        }

        TarjetaCredito tarjeta = null;
        if (currentIndex < tarjetas.size()) {
            tarjeta = tarjetas.get(currentIndex);
            currentIndex++;
        } else {
            currentIndex = 0;
            tarjetas = null;
        }
        return tarjeta;
    }
}
