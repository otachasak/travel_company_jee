package cz.cvut.fel.jee.travel_company.view.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author vlada
 * @version: 31/01/14
 */
public abstract class BasicBB implements Serializable {
    protected Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

    public <T> List<String> convertToString(List<T> src, Map<String, T> map, ToString<T> toString) {
        if (src == null || map == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (T t : src) {
            String label = toString.toString(t);
            result.add(label);
            map.put(label, t);
        }
        return result;
    }

    protected interface ToString<T> {
        public String toString(T t);
    }
}
