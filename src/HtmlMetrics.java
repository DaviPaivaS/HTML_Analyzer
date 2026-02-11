import java.util.Map;

public class HtmlMetrics {

    private final int maxDepth;
    private final int totalTags;
    private final int uniqueTags;
    private final int leafNodes;
    private final double averageDepth;
    private final int totalTextLength;
    private final Map<String, Integer> tagFrequency;
    private final double complexityScore;

    public HtmlMetrics(int maxDepth,
                       int totalTags,
                       int uniqueTags,
                       int leafNodes,
                       double averageDepth,
                       int totalTextLength,
                       Map<String, Integer> tagFrequency,
                       double complexityScore) {

        this.maxDepth = maxDepth;
        this.totalTags = totalTags;
        this.uniqueTags = uniqueTags;
        this.leafNodes = leafNodes;
        this.averageDepth = averageDepth;
        this.totalTextLength = totalTextLength;
        this.tagFrequency = tagFrequency;
        this.complexityScore = complexityScore;
    }

    public int getMaxDepth() { return maxDepth; }
    public int getTotalTags() { return totalTags; }
    public int getUniqueTags() { return uniqueTags; }
    public int getLeafNodes() { return leafNodes; }
    public double getAverageDepth() { return averageDepth; }
    public int getTotalTextLength() { return totalTextLength; }
    public Map<String, Integer> getTagFrequency() { return tagFrequency; }
    public double getComplexityScore() { return complexityScore; }
}
