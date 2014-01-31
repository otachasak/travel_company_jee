package cz.cvut.fel.jee.travel_company.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author vlada
 * @version: 31/01/14
 */
public abstract class BasicService implements Serializable {
    protected Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    protected <ORIGINAL, DTO> List<DTO> originalToDTos(Class<ORIGINAL> originalClazz,
                                                       Class<DTO> dtoClazz,
                                                       List<ORIGINAL> originals) {
        List<DTO> dtos = new ArrayList<>();
        try {
            for (ORIGINAL original : originals) {
                DTO dto = dtoClazz.getDeclaredConstructor(originalClazz).newInstance(original);
                dtos.add(dto);
            }
        } catch (Exception e) {
            return null;
        }

        return dtos;
    }
}
