import java.util.*;

public class HtmlParser {

    private final String html;

    public HtmlParser(String html) {
        this.html = html;
    }

    public HtmlMetrics parse() {

        Stack<String> stack = new Stack<>();
        Map<String, Integer> tagFrequency = new HashMap<>();

        int currentDepth = 0;
        int maxDepth = 0;
        int totalTags = 0;
        int leafNodes = 0;
        int totalDepthSum = 0;
        int totalTextLength = 0;

        boolean insideTag = false;
        boolean insideScript = false;
        boolean insideStyle = false;

        StringBuilder tagBuffer = new StringBuilder();
        boolean lastWasOpeningTag = false;

        for (int i = 0; i < html.length(); i++) {
            char c = html.charAt(i);

            if (c == '<') {
                insideTag = true;
                tagBuffer.setLength(0);
            }

            if (insideTag) {
                tagBuffer.append(c);
            }

            if (c == '>') {
                insideTag = false;

                String fullTag = tagBuffer.toString();

                if (fullTag.startsWith("<!--") || fullTag.startsWith("<!")) {
                    continue;
                }

                if (isOpeningTag(fullTag)) {

                    String tagName = extractTagName(fullTag);

                    if (tagName.equals("script")) {
                        insideScript = true;
                        continue;
                    }

                    if (tagName.equals("style")) {
                        insideStyle = true;
                        continue;
                    }

                    if (!insideScript && !insideStyle) {
                        stack.push(tagName);
                        currentDepth++;
                        totalTags++;
                        totalDepthSum += currentDepth;

                        tagFrequency.put(tagName,
                                tagFrequency.getOrDefault(tagName, 0) + 1);

                        if (currentDepth > maxDepth) {
                            maxDepth = currentDepth;
                        }

                        lastWasOpeningTag = true;
                    }

                } else if (isClosingTag(fullTag)) {

                    String tagName = extractTagName(fullTag);

                    if (tagName.equals("script")) {
                        insideScript = false;
                        continue;
                    }

                    if (tagName.equals("style")) {
                        insideStyle = false;
                        continue;
                    }

                    if (!stack.isEmpty() && !insideScript && !insideStyle) {
                        stack.pop();
                        currentDepth--;

                        if (lastWasOpeningTag) {
                            leafNodes++;
                        }
                    }

                    lastWasOpeningTag = false;
                }

            } else if (!insideTag && !insideScript && !insideStyle) {
                if (!Character.isWhitespace(c)) {
                    totalTextLength++;
                }
            }
        }

        int uniqueTags = tagFrequency.size();
        double averageDepth = totalTags == 0 ? 0 :
                (double) totalDepthSum / totalTags;

        double complexityScore =
                (maxDepth * Math.log(totalTags + 1))
                        + (uniqueTags * 0.5);

        return new HtmlMetrics(
                maxDepth,
                totalTags,
                uniqueTags,
                leafNodes,
                averageDepth,
                totalTextLength,
                tagFrequency,
                complexityScore
        );
    }

    private boolean isOpeningTag(String tag) {
        return tag.matches("<[^/!][^>]*>");
    }

    private boolean isClosingTag(String tag) {
        return tag.matches("</[^>]+>");
    }

    private String extractTagName(String tag) {
        tag = tag.replaceAll("[</>]", "").trim();
        String[] parts = tag.split("\\s+");
        return parts[0].toLowerCase();
    }
}
