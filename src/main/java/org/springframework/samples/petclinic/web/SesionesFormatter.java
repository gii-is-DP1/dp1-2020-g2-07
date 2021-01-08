package org.springframework.samples.petclinic.web;
import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.stereotype.Component;

@Component
public class SesionesFormatter implements Formatter<Sesion> {
    private final HorarioService hs;

    @Autowired
    public SesionesFormatter(HorarioService hs) {
        this.hs = hs;
    }

    @Override
    public String print(Sesion sesionType, Locale locale) {
    	return sesionType.getHorario().getFecha() + ": From " + sesionType.getHoraInicio() + " to " + sesionType.getHoraFin();
    }

    @Override
    public Sesion parse(String text, Locale locale) throws ParseException {
        Collection<Horario> findHorario = this.hs.findAll();
        for (Horario h : findHorario) {
            if (h.getFecha().toString().equals(text.substring(0, text.indexOf(":")))) {
            	for(Sesion s : h.getSesiones()) {
            		if(s.getHoraInicio().toString().equals(text.substring(text.indexOf("m")+2, text.indexOf("t")-1))) {
            			return s;
            		}
            	}   
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}