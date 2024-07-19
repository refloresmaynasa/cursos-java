package me.rflores.servicios;

import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Evento;
import me.rflores.utiles.Periodo;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public interface EventoServicio {
    public List<Evento> listar();

    public List<Evento> listarPorTitulo(boolean desendente);

    public List<Evento> listarPorFecha(boolean desendente);

    public List<Evento> listarPorFechas(LocalDate fechaInicial, LocalDate fechaFinal);

    public void grabar(Evento evento);

    public void actualizar(Evento evento);

    public void eliminar(int id);

    public Evento buscar(int id);

    /***
     * Metodo que busca el evento con mayor capacidad (OP: con menor capacidad)
     * @param conMenorCapacidad Para buscar el evento con menor capacidad
     * @return
     */
    public Evento buscarPorCapacidad(boolean conMenorCapacidad) throws Exception;

    public boolean agregarAsistente(Evento evento, Asistente asistente);

    public void informacionCostos();

    public void informacionFechas();

    public void informacionPorPeriodo(Periodo periodo, int anio);

    public void iniciarCopiasDeSeguridad();
}
