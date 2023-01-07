package com.example.localisation_pharmacie.utils;

import com.example.localisation_pharmacie.beans.Etat;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class EtatConverter implements AttributeConverter<Etat, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Etat etat) {
        return etat.getEtat();
    }

    @Override
    public Etat convertToEntityAttribute(Integer i) {
        return Stream.of(Etat.values())
                .filter(c -> c.getEtat()==i)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
