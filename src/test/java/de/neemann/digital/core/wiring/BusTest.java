package de.neemann.digital.core.wiring;

import de.neemann.digital.TestExecuter;
import de.neemann.digital.core.BurnException;
import de.neemann.digital.core.Model;
import de.neemann.digital.core.ObservableValue;
import de.neemann.digital.core.basic.FanIn;
import junit.framework.TestCase;

/**
 * @author hneemann
 */
public class BusTest extends TestCase {

    public void testBus() throws Exception {
        Model model = new Model();
        ObservableValue a = new ObservableValue("a", 1, true);
        ObservableValue b = new ObservableValue("b", 1);
        FanIn out = model.add(new Bus(1));
        out.setInputs(a, b);


        TestExecuter te = new TestExecuter(model).setInputs(a, b).setOutputs(out.getOutputs());
        te.check(0, 0, 0);
        te.check(0, 1, 1);
        a.setHighZ(false);
        b.setHighZ(true);
        te.check(0, 1, 0);
        te.check(1, 0, 1);
        a.setHighZ(false);
        b.setHighZ(false);
        try {
            te.check(0, 0, 0);
            assertTrue(false);
        } catch (BurnException e) {
            assertTrue(true);
        }
    }

    public void testBusHighZ() throws Exception {
        Model model = new Model();
        ObservableValue a = new ObservableValue("a", 1, true);
        ObservableValue b = new ObservableValue("b", 1, true);
        FanIn out = model.add(new Bus(1));
        out.setInputs(a, b);

        TestExecuter te = new TestExecuter(model).setInputs(a, b).setOutputs(out.getOutputs());
        assertTrue(out.getOutput().isHighZ());
    }
}