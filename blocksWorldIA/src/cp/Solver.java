package cp;
import modelling.Variable;
import java.util.Map;

/*
 * Interface Solver pour resoudre les problèmes de CSP
 */

public interface Solver {

   Map<Variable, Object> solve();
}