package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.stereotype.Component;

@Component
public class SalaTypeFormatter implements Formatter<Sala> {
    private final SalaService salaService;

    @Autowired
    public SalaTypeFormatter(SalaService salaService) {
        this.salaService = salaService;
    }


    @Override
    public String print(Sala salaType, Locale locale) {
        // TODO Auto-generated method stub
        return salaType.getName() + " (" + salaType.getAforo() + ") ";
    }

    @Override
    public Sala parse(String text, Locale locale) throws ParseException {
        Collection<Sala> findSalas = this.salaService.findAll();
        for (Sala sala : findSalas) {
            if (sala.getName().equals(text.substring(0, text.indexOf("(")-1))) {
                return sala;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }



}
