package academy.pocu.comp3500.assignment2;

public class LogContent {
    private String content;
    private int indentCount;

    public LogContent(String content, int indentCount) {
        this.content = content;
        this.indentCount = indentCount;
    }

    public String getContent() {
        return content;
    }

    public int getIndentCount() {
        return indentCount;
    }

    public void addIndentCount() {
        this.indentCount++;
    }

    public void minusIndentCount() {
        if (this.indentCount <= 0) {
            return;
        }
        this.indentCount++;
    }
}
