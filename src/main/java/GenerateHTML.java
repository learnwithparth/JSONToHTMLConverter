import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenerateHTML {
    public static void main(String args[]) throws IOException, ParseException {
        File fileList[] = new File("jsonFiles").listFiles();
        System.out.println("Total " + fileList.length + " are available");
        for (File file : fileList) {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
            System.out.println("Converting " + file.getName());
            System.out.println(jsonObject.get("title"));
            File htmlTemplateFile = new File("temp.html");
            String htmlString = FileUtils.readFileToString(htmlTemplateFile);
            String title = (String) jsonObject.get("title");
            String common = (String) jsonObject.get("Common name");
            String mainImage = (String) jsonObject.get("main_image");
            String botanical = (String) jsonObject.get("Botanical Name");
            String vernacular = (String) jsonObject.get("Vernacular name");
            String family = (String) jsonObject.get("Family");
            String Class = (String) jsonObject.get("Class");
            String parts = (String) jsonObject.get("Parts of plant used");
            String chemical = (String) jsonObject.get("Chemical Constituents");
            String therapeutic = (String) jsonObject.get("Therapeutic uses");
            htmlString = htmlString.replace("$title", (String) jsonObject.get("title"));
            htmlString = htmlString.replace("$main_image", (String) jsonObject.get("main_image"));
            htmlString = htmlString.replace("$Common", (String) jsonObject.get("Common name"));
            htmlString = htmlString.replace("$Botanical", (String) jsonObject.get("Botanical Name"));
            htmlString = htmlString.replace("$Vernacular", (String) jsonObject.get("Vernacular name"));
            htmlString = htmlString.replace("$Family", (String) jsonObject.get("Family"));
            htmlString = htmlString.replace("$Classe", (String) jsonObject.get("Class"));
            htmlString = htmlString.replace("$Parts", (String) jsonObject.get("Parts of plant used"));
            htmlString = htmlString.replace("$Chemical", (String) jsonObject.get("Chemical Constituents"));
            htmlString = htmlString.replace("$Therapeutic", (String) jsonObject.get("Therapeutic uses"));
            String fileNameWithExt = file.getName();
            File newHtmlFile = new File("htmlFiles/" + fileNameWithExt.substring(0, fileNameWithExt.lastIndexOf(".")) + ".html");
            //File newHtmlFile = new File("htmlFiles/206.html");
            FileUtils.writeStringToFile(newHtmlFile, htmlString);
        }

    }
}
