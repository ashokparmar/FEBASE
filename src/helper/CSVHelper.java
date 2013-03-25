package helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVWriter;

public class CSVHelper {
	public static final String FIELD_TERMINATOR = ",";
	
	public static void writeIntoCSV(String filePath, String[] header, ArrayList<String[]> data) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(filePath),FIELD_TERMINATOR.charAt(0));
			if (header != null && header.length > 0) {
				writer.writeNext(header);
			}			
			for(String[] record : data) {
				writer.writeNext(record);
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println("IOException in writeIntoCSV");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
