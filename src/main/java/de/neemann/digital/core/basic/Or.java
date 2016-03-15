package de.neemann.digital.core.basic;

import de.neemann.digital.core.NodeException;
import de.neemann.digital.core.ObservableValue;
import de.neemann.digital.core.Part;
import de.neemann.digital.core.PartFactory;

import java.util.ArrayList;

/**
 * @author hneemann
 */
public class Or extends Function {

    public Or(int bits) {
        super(bits);
    }

    public static PartFactory createFactory(int bits, int inputs) {
        return new FanInFactory(inputs) {
            @Override
            public Part create() {
                return new Or(bits);
            }
        };
    }

    @Override
    protected int calculate(ArrayList<ObservableValue> inputs) throws NodeException {
        int f = 0;
        for (ObservableValue i : inputs) {
            f |= i.getValue();
        }
        return f;
    }
}
