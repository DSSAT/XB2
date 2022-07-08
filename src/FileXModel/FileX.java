package FileXModel;

/**
 *
 * @author Jazzy
 */
public class FileX {
    public static GeneralInformation general;
    public static FieldList fieldList;
    public static InitialConditionList initialList;
    public static SoilAnalysisList soilAnalysis;
    public static EnvironmentalList environmentals;
    public static CultivarList cultivars;
    public static PlantingList plantings;
    public static IrrigationList irrigations;
    public static FertilizerList fertilizerList;
    public static OrganicList organicList;
    public static TillageList tillageList;
    public static HarvestList harvestList;
    public static ChemicalList chemicalList;
    public static SimulationList simulationList;
    public static TreatmentList treaments;
    private static boolean opened = false;
    private static String fileName;
    
    public static String GetAbsoluteFileName(){
        return fileName;
    }
    
    public static void SetAbsoluteFileName(String fileName){
        FileX.fileName = fileName;
    }

    public static boolean GetStatus() {
        return opened;
    }

    public static void NewFileX() {
        general = new GeneralInformation();
        fieldList = new FieldList();
        initialList = new InitialConditionList();
        soilAnalysis = new SoilAnalysisList();
        environmentals = new EnvironmentalList();
        cultivars = new CultivarList();
        plantings = new PlantingList();
        irrigations = new IrrigationList();
        fertilizerList = new FertilizerList();
        organicList = new OrganicList();
        tillageList = new TillageList();
        harvestList = new HarvestList();
        chemicalList = new ChemicalList();
        simulationList = new SimulationList();
        treaments = new TreatmentList();

        fileName = null;
        opened = true;
    }

    public static void CloseFile() {
        general = null;
        fieldList = null;
        initialList = null;
        soilAnalysis = null;
        environmentals = null;
        cultivars = null;
        plantings = null;
        irrigations = null;
        fertilizerList = null;
        organicList = null;
        tillageList = null;
        harvestList = null;
        chemicalList = null;
        simulationList = null;
        treaments = null;

        fileName = null;
        opened = false;
    }
}
