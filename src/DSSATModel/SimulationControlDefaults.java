package DSSATModel;

import FileXModel.Simulation;
import java.util.HashMap;

/**
 *
 * @author Jazzy
 */
public class SimulationControlDefaults {
    
    private static final HashMap<ExperimentType, Simulation> simulations = new HashMap<ExperimentType, Simulation>() {};
    static {
        for (ExperimentType exp : ExperimentType.values()) {
            simulations.put(exp, new Simulation());
        }
    }
    
    public static Simulation Get(ExperimentType experimentType){
        return simulations.get(experimentType);
    }
    
    public static void Update(ExperimentType experimentType, Simulation sim){
        for(ExperimentType exp : ExperimentType.values()){
            if(exp == experimentType){
                simulations.put(exp, sim);
            }
        }
    }
    
}
