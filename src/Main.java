
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

/** *
 * @author tasneem
 * Java application for standardOntology Project for automation.
 * Aim of this application is to automate the process of creating 
 * ontology for Standards of Industry4.0
 * It includes:
 *      1. Read excel file with all standards and their properties
 *      2. Automatically add the the instances of the standards 
 *         in the turtle file.
 * For problem with libraries: change the file path 
 */
public class Main 
{    
    public static void main (String [] args)
    {
        //to turn off log4j(API) warnings
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        
        //create file path for all files
        CreateFilePath cfp = new CreateFilePath();
        String excelFilePath = cfp.createFilePath("inputTTR.xls");
        String turtleFilePath = cfp.createFilePath("ttlFileTTR.ttl");
        String outputTurtleFilePath = cfp.createFilePath("ttlFileTTR.ttl");
        ReadExcel  r = new ReadExcel(excelFilePath);
        try
        {
           //Scanner key = new Scanner(System.in);  
           String[][] excelSheet;
           for(int i = 3; i >= 0; i--)
           {
               System.out.println("Running excel sheet number : " + i);
               excelSheet = r.read(i);
               WriteTurtleFile w = new WriteTurtleFile(turtleFilePath, excelSheet, outputTurtleFilePath);
               w.createInstance();
           }           
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
