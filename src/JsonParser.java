import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonParser {
    private ObjectMapper mapper;
    private HashMap<String, ArrayList<Object>> fields;
    private ArrayList<File> fileArrayList;

    public JsonParser(File file) {
        mapper = new ObjectMapper();
        fields = new HashMap<>();
        fileArrayList = new ArrayList<>();
        search(file);

        for (File f : fileArrayList) {
            if (f.getName().contains(".json"))
                readFile(f);
        }
    }

    public void readFile(File file) {
        try {
            JsonNode jsonNode = mapper.readTree(file);
            Iterator<String> iterator = jsonNode.fieldNames();
            while (iterator.hasNext()) {
                String name = iterator.next();
                Object value = jsonNode.get(name);

                if (fields.containsKey(name) && ((JsonNode) value).isValueNode()) {
                    fields.get(name).add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (String key : fields.keySet()) {
            for (Object obj : fields.get(key)) {
                System.out.print(obj.toString());
            }
        }
    }

    private void search(File baseFile) {
        File[] fileList = baseFile.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isDirectory()) {
                    search(file);
                } else {
                    fileArrayList.add(file);
                }
            }
        }
    }
}
