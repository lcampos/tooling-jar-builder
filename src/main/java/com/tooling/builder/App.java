package com.tooling.builder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.sforce.ws.tools.*;

import org.stringtemplate.v4.STGroupDir;
/**
 * Build Tooling API jar
 *
 */ 
public class App {
	
	public static final String STANDALONE_JAR = "standalone-jar";
	 
    public static void main( String[] args ) throws Exception {
        
        File destJar = new File("./target/tooling.jar");
        /*if (destJar.exists()) {
            if (!destJar.delete()) { throw new ToolsException(String.format(
                    "Output Jar file exists and cannot be deleted: %s", destJar.getAbsolutePath())); }
        }*/
        //boolean standAlone = Boolean.parseBoolean(System.getProperty(STANDALONE_JAR, "false"));
        
        System.out.println("--- destJar = " + destJar.getPath());
        String wsdlUrl = "./src/main/resources/tooling-39.wsdl";
        
        //wsdlc.run(wsdlUrl, "./target/tooling.jar", "", true, new STGroupDir(wsdlc.TEMPLATE_DIR, '$', '$'), "./target", true);
        //" usage: java com.sforce.ws.tools.wsdlc -nc <wsdl-file> <jar-file> <dest-dir>"

        wsdlc.main(new String[]{wsdlUrl, "./target/tooling.jar", "./target"});
    } 
   
    private static void generate() throws Exception {
    	System.out.println( "--- Test Tooling API app, generate ---" );

        wsdlc wsc = new wsdlc("", new STGroupDir(wsdlc.TEMPLATE_DIR, '$', '$'));
        File destDirectory = new File("./newWSDL");
        destDirectory.mkdirs();
        
        System.out.println("--- destDirectory = " + destDirectory.getPath());
        String wsdlUrl = "./src/main/resources/tooling-39.wsdl";
        
        URL wsdl;
        try {
            wsdl = new URL(wsdlUrl);
        } catch (MalformedURLException e) {
            try {
                wsdl = new URL(String.format("file:%s", wsdlUrl));
            } catch (MalformedURLException e2) {
                throw e;
            }
        }
        System.out.println("--- wsdl = " + wsdl.toString());
        wsc.generate(wsdl, destDirectory);
    }
    

}
