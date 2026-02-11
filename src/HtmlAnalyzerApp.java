public class HtmlAnalyzerApp {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java HtmlAnalyzerApp <URL>");
            return;
        }

        try {
            String html = HtmlFetcher.fetch(args[0]);

            HtmlParser parser = new HtmlParser(html);
            HtmlMetrics metrics = parser.parse();

            printReport(metrics);

        } catch (Exception e) {
            System.out.println("URL connection error");
        }
    }

    private static void printReport(HtmlMetrics m) {

        System.out.println("===== HTML STRUCTURAL ANALYSIS =====");
        System.out.println("Max Depth: " + m.getMaxDepth());
        System.out.println("Total Tags: " + m.getTotalTags());
        System.out.println("Unique Tags: " + m.getUniqueTags());
        System.out.println("Leaf Nodes: " + m.getLeafNodes());
        System.out.printf("Average Depth: %.2f\n", m.getAverageDepth());
        System.out.println("Total Text Length: " + m.getTotalTextLength());
        System.out.printf("Complexity Score: %.2f\n", m.getComplexityScore());

        System.out.println("\nTag Frequency:");
        m.getTagFrequency().entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
