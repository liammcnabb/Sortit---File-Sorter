import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dingle on 04/06/2015.
 */
public class Match {
    private String title;
    private List<File> matchedFiles;
    private boolean sortCheck;

    public Match(String title, File matchedFile)
    {
        matchedFiles = new ArrayList<File>();
        setTitle(title);
        addFile(matchedFile);
        setSortCheck(true);
    }

    public void addFile(File newMatch)
    {
        matchedFiles.add(newMatch);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<File> getMatchedFiles() {
        return matchedFiles;
    }

    public void setMatchedFiles(List<File> matchedFiles) {
        this.matchedFiles = matchedFiles;
    }

    public boolean isSortCheck() {
        return sortCheck;
    }

    public void setSortCheck(boolean sortCheck) {
        this.sortCheck = sortCheck;
    }
}
