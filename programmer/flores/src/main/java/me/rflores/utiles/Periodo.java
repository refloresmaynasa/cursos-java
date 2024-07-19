package me.rflores.utiles;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public enum Periodo {
    Q1(new Month[]{Month.JANUARY, Month.FEBRUARY, Month.MARCH}),
    Q2(new Month[]{Month.APRIL, Month.MAY, Month.JUNE}),
    Q3(new Month[]{Month.JULY, Month.AUGUST, Month.SEPTEMBER}),
    Q4(new Month[]{Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER});

    private Month[] meses;

    private Periodo(Month[] meses) {
        this.meses = meses;
    }

    public List<Month> getMeses() {
        return Arrays.stream(meses).toList();
    }

    public static Periodo valueOf(Month month) {
        for (Periodo periodo : Periodo.values()) {
            if (periodo.getMeses().contains(month))
                return periodo;
        }
        throw new IllegalArgumentException("No se encontro un Periodo valido para el mes");
    }
}
