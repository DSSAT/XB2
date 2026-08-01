package FileXService;

import DSSATModel.WstaType;
import FileXModel.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jazzy
 */
public class FileXService {
    public static void OpenFileX(File fileName) {
        FileX.NewFileX();
        
        GeneralService.Read(fileName);
        TreatmentService.Read(fileName);
        CultivarService.Read(fileName);
        FieldService.Read(fileName);
        SoilAnalysisService.Read(fileName);
        InitialConditionService.Read(fileName);
        PlantingDetailService.Read(fileName);
        IrrigationService.Read(fileName);
        FertilizerService.Read(fileName);
        ResidueService.Read(fileName);
        ChemicalApplicationService.Read(fileName);
        TillageService.Read(fileName);
        EnvironmentService.Read(fileName);
        HarvestService.Read(fileName);
        FileX.simulationList = SimulationControlService.Read(fileName.getAbsolutePath());
        FileX.SetAbsoluteFileName(fileName.getAbsolutePath());
        
        if(FileX.simulationList != null && FileX.simulationList.GetSize() > 0){
            Simulation s = (Simulation)FileX.simulationList.GetAtIndex(0);
            switch (s.WTHER) {
                case "M":
                    FileX.wstaType = WstaType.WTH;
                    break;
                case "G":
                case "W":
                    FileX.wstaType = WstaType.WTG;
                    break;
                case "S":
                    FileX.wstaType = WstaType.CLI;
                    break;
            }
        }
        
        FileX.isFileOpenned = true;
    }
    
    /**
     * Writes to a temp file first, then replaces the target.
     * On failure the original file is left unchanged.
     * @return true if save succeeded
     */
    public static boolean SaveFile(File file) {
        File tempFile = new File(file.getAbsolutePath() + ".tmp");
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter(tempFile));

            GeneralService.Extract(pw);
            TreatmentService.Extract(pw);
            CultivarService.Extract(pw);
            FieldService.Extract(pw);
            SoilAnalysisService.Extract(pw);
            InitialConditionService.Extract(pw);
            PlantingDetailService.Extract(pw);
            IrrigationService.Extract(pw);
            FertilizerService.Extract(pw);
            ResidueService.Extract(pw);
            TillageService.Extract(pw);
            EnvironmentService.Extract(pw);
            HarvestService.Extract(pw);
            ChemicalApplicationService.Extract(pw);
            SimulationControlService.Extract(pw);

            pw.flush();
            if (pw.checkError()) {
                throw new IOException("Error writing file content");
            }
            pw.close();
            pw = null;

            try {
                replaceFile(tempFile, file);
            } catch (IOException replaceFailed) {
                throw replaceFailed;
            }

            FileX.isFileOpenned = false;
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FileXService.class.getName()).log(Level.SEVERE, "Save failed: " + file.getAbsolutePath(), ex);
            if (tempFile.exists() && !tempFile.delete()) {
                tempFile.deleteOnExit();
            }
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private static void replaceFile(File tempFile, File file) throws IOException {
        try {
            Files.move(tempFile.toPath(), file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
            return;
        } catch (IOException atomicFailed) {
            // Fall through to non-atomic replace.
        }

        try {
            Files.move(tempFile.toPath(), file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            return;
        } catch (IOException moveFailed) {
            // Windows may keep read handles open after OpenFileX; overwrite contents instead.
            try (FileOutputStream out = new FileOutputStream(file)) {
                Files.copy(tempFile.toPath(), out);
            } finally {
                tempFile.delete();
            }
        }
    }
}
