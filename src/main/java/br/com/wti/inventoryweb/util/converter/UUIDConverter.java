package br.com.wti.inventoryweb.util.converter;

import com.google.common.collect.Maps;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

/**
 * @author Washington Antunes for wTI on 01/05/2024
 */
@FacesConverter(value = "uuidConverter")
@Slf4j
public class UUIDConverter implements Converter {

    private static Map<Object, String> entities = Maps.newHashMap();

    @Override
    public String getAsString(final FacesContext context, final UIComponent component, final Object entity) {
        synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put(entity, uuid);
                return uuid;
            } else {
                String uuidExistente = entities.get(entity);
                entities.remove(entity);
                entities.put(entity, uuidExistente);
                return uuidExistente;
            }
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
        log.debug("Converter {}, tamanho {}", this, entities.size());
        for (Map.Entry<Object, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
