package com.htmlParser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.parameters.P;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleHtmlParser {

    public static void main(String[] args) {
        ArrayList<CustomNode> nodes = new ArrayList<>();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        File file = new File("html.html");
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter(Pattern.compile("(?=<)|(?<=>)"));
            Pattern patternCloseTag = Pattern.compile("</(\\w+).*>");
            Pattern patternOpenTag = Pattern.compile("<(\\w+)(.*?)(/?)>");
            Pattern patternAttributes = Pattern.compile("(\\w+)(?:='(.*?)'|=\"(.*?)\")");
            while (scanner.hasNext()) {
                String word = scanner.next();
                Matcher openMatcher = patternOpenTag.matcher(word);
                if (openMatcher.matches()) {
                    Matcher attributesMAtcher = patternAttributes.matcher(openMatcher.group(2));
                    Map<String, String> mapAttributes = new HashMap<>();
                    while (attributesMAtcher.find()) {
                        String val = Optional.ofNullable(attributesMAtcher.group(2)).orElse(attributesMAtcher.group(3));
                        mapAttributes.put(attributesMAtcher.group(1), Optional.ofNullable(val).orElse(""));
                    }
                    CustomNode customNode = new CustomNode(openMatcher.group(1), mapAttributes, openMatcher.group(3).equals("/"), true);
                    nodes.add(customNode);
                } else {
                    Matcher closeMatcher = patternCloseTag.matcher(word);
                    if (closeMatcher.matches()) {
                        CustomNode customNode = new CustomNode(closeMatcher.group(1), new HashMap<>(), false, false);
                        nodes.add(customNode);
                    }
                }
            }
            handleHtml(nodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void handleHtml(ArrayList<CustomNode> arrayList) {
        ArrayList<CustomNode> listNoShortTags = arrayList.stream().filter(customNode -> !customNode.isShortTag()).collect(Collectors.toCollection(ArrayList::new));
        CustomNode resultTag = listNoShortTags.stream()
                .filter(element -> !searchCustomNodeById(element.getAttributes()).equals(Optional.empty())).findAny().orElse(null);
        System.out.println("-----------------" + resultTag);
    }

    public static Optional<Map.Entry<String, String>> searchCustomNodeById(Map<String, String> mapOfAttributes) {
        return mapOfAttributes.entrySet().stream().filter(SimpleHtmlParser::filterKey).findFirst();

    }

    static boolean filterKey(Map.Entry<String, String> key) {
        if (key.getKey().equals("id")) {
            return key.getValue().equals("side-menu");
        } else {
            return false;
        }
    }

}
