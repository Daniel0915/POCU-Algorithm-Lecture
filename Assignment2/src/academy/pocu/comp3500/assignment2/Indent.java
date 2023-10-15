package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;
import java.util.Iterator;

public final class Indent {
    private int indentCount;
    private ArrayList<LogContent> logs;

    public void discard() {
        Iterator<LogContent> it = logs.iterator();
        while (it.hasNext()) {
            if (it.next().getIndentCount() == indentCount) {
                it.remove();
            }
        }
    }

    public void addIndentCount() {
        this.indentCount++;
    }

    public void minusIndentCount() {
        if (this.indentCount <= 0) {
            return;
        }
        this.indentCount--;
    }

    public int getIndentCount() {
        return indentCount;
    }

    public void setLogs(ArrayList<LogContent> logs) {
        this.logs = logs;
    }
}