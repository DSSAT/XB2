package DSSATServices;

import DSSATRepository.SimulationRepository;
import DSSATModel.CropModel;
import DSSATModel.CropModelList;
import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class SimulationService extends DSSATServiceBase {
    private final SimulationRepository simulationRepository;

    public SimulationService(String rootPath) {
        super(rootPath);

        this.simulationRepository = new SimulationRepository(rootPath);
    }

    @Override
    public void Parse() throws Exception {
        boolean isValid = true;

        try {
            ArrayList<String> cropModel = this.simulationRepository.Parse();

            CropModelList.Clear();

            CropModel emptyModel = new CropModel();
            emptyModel.ModelCode = "";
            emptyModel.Code = "";
            emptyModel.Description = "";
            CropModelList.AddNew(emptyModel);

            java.util.HashSet<String> addedModels = new java.util.HashSet<>();
            addedModels.add(emptyModel.ModelCode);

            for(int i = 0;i < cropModel.size();i++)
            {
                String tmp = cropModel.get(i);
                if (tmp.trim().isEmpty()) {
                    continue;
                }
                CropModel cModel = new CropModel();

                cModel.ModelCode = tmp.substring(0, 6).trim();
                
                if (addedModels.contains(cModel.ModelCode)) {
                    continue;
                }
                
                cModel.Code = ""; // Generic engine doesn't have a specific crop code
                try
                {
                    String desc = tmp.substring(14, tmp.length()).trim();
                    if (desc.contains("-")) {
                        cModel.Description = desc.substring(0, desc.indexOf("-")).trim();
                    } else {
                        cModel.Description = desc;
                    }
                }
                catch(Exception ex)
                {
                    cModel.Description = cModel.ModelCode;
                }
                
                addedModels.add(cModel.ModelCode);
                CropModelList.AddNew(cModel);
            }
        } catch (Exception ex) {
            throw ex;
        }

        if (!isValid) {
            throw new Exception("Crop Model parse failed");
        }
    }

    @Override
    public String getName() {
        return "Crop Model";
    }
}
