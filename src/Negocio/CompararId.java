
package Negocio;

import java.util.Comparator;

/**
 *
 * @author BRANDON OÑA
 */
public class CompararId implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
        int aux = Integer.parseInt((String) t);
        int aux1= Integer.parseInt((String) t1);
        return Integer.valueOf(aux).compareTo(aux1);
    }
}
