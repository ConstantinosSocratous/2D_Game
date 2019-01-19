package tilegame.utils;

import java.io.*;

public class FileLoader {

    public static String loadFileAsString(String path)  {
        try {
            ClassLoader classLoader = FileLoader.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);
            String data = readFromInputStream(inputStream);
            return data;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
        /*StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();*/
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
