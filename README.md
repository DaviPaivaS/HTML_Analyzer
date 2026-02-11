# HTML Analyzer
Ferramenta em Java que realiza análise estrutural de páginas HTML a partir de uma URL. Calcula métricas de profundidade, frequência de tags, nós folha e um score de complexidade estrutural, aplicando parsing com pilha e princípios de design modular.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

A Java-based HTML structural analysis tool that performs depth and complexity analysis on any public HTML page via URL.

This project was developed to explore structural metrics of HTML documents and generate analytical insights such as depth, tag distribution, structural complexity, and content density.

---

## 🚀 Features

The analyzer computes:

- Maximum depth of the HTML structure
- Total number of tags
- Number of unique tags
- Total leaf nodes
- Average tag depth
- Total text nodes found
- Tag frequency distribution
- Structural complexity score

---

## 📊 Metrics Explained

### Max Depth
Maximum nesting level found in the document.

### Total Tags
Total number of opening tags processed.

### Unique Tags
Number of different HTML tags detected.

### Leaf Nodes
Tags that do not contain other tags inside.

### Average Depth
Average nesting level across all tags.

### Total Text Nodes
Amount of non-whitespace text found between tags.

### Tag Frequency
How many times each tag appears.

### Complexity Score
A calculated structural complexity metric based on: (maxDepth * log(totalTags + 1)) + (uniqueTags * 0.5)


---

## 🛠️ How It Works

1. Fetches HTML from a given URL using `HttpURLConnection`
2. Parses the document character-by-character
3. Uses a stack to validate structure and calculate depth
4. Collects structural metrics
5. Generates a structured analytical report

---

## ▶️ How to Run

### Compile

```bash
javac *.java

Execute
java HtmlAnalyzerApp https://example.com
```
### Example output
===== HTML STRUCTURAL ANALYSIS =====
Max Depth: 12
Total Tags: 143
Unique Tags: 18
Leaf Nodes: 47
Average Depth: 6.23
Total Text Nodes: 47
Complexity Score: 198.45

Tag Frequency:
div: 34
a: 22
span: 18
...
