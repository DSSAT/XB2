package DSSATRepository;

import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public abstract class DSSATRepositoryBase {
    protected String rootPath;
    
    public DSSATRepositoryBase(String rootPath){
        this.rootPath = rootPath;
    }
    public ArrayList<String> Parse() throws Exception {
        return new ArrayList<String>();
    }
}
