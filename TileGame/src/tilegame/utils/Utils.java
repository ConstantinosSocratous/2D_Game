package tilegame.utils;

import tilegame.gfx.ImageLoader;

import javax.annotation.Resource;
import java.io.*;
import java.util.stream.Collectors;

public class Utils {
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();

		try{
			InputStream is = Utils.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");

			br.close();
			is.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return builder.toString();
	}
	
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

}
