package me.rflores.clienteapp.services;

import me.rflores.clienteapp.models.daos.TarjetaCreditoRiesgoRepository;
import me.rflores.clienteapp.models.entities.TarjetaCreditoRiesgo;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarjetaCreditoWriter implements ItemWriter<TarjetaCreditoRiesgo> {

    private final TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository;

    public TarjetaCreditoWriter(TarjetaCreditoRiesgoRepository tarjetaCreditoRiesgoRepository) {
        this.tarjetaCreditoRiesgoRepository = tarjetaCreditoRiesgoRepository;
    }

    @Override
    public void write(Chunk<? extends TarjetaCreditoRiesgo> chunk) throws Exception {
        tarjetaCreditoRiesgoRepository.saveAll(chunk);
    }
}
