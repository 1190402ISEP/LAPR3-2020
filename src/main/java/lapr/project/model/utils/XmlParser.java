package lapr.project.model.utils;

import lapr.project.ui.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class XmlParser {

    public static Document getDocument ( String path ) {

        Document document = null;

        try {
            File xmlFile = new File(path);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);

        } catch ( ParserConfigurationException | SAXException | IOException e ) {
            Logger.log( e.getMessage() );
        }

        return document;

    }

    public static Document normalizeDocument ( Document document ) {

        document.getDocumentElement().normalize();
        return document;
    }

    public static String parse ( Document document ) {

        StringBuilder builder =  new StringBuilder();
        NodeList pharmacyFolder = document.getElementsByTagName( "Folder" ).item( 0 ).getChildNodes();
        NodeList clientFolder = document.getElementsByTagName( "Folder" ).item( 1 ).getChildNodes();

        builder.append( "#pharmacies" ).append( "\n" );
        folderToBuffer( builder, pharmacyFolder );

        builder.append( "\n" ).append( "#clients" ).append( "\n" );
        folderToBuffer( builder, clientFolder );

        return builder.toString();
    }

    private static void folderToBuffer ( StringBuilder buffer, NodeList folder ) {

        for (int i = 3; i < folder.getLength(); i += 2 ) {

            NodeList placemark = folder.item( i ).getChildNodes();

            Node name = placemark.item( 1 );
            Node pointCoordinates = placemark.item( 5 );

            String [] coordinates =
                    pointCoordinates
                            .getTextContent()
                            .replaceAll("\\s+", "")
                            .split( "," );

            buffer.append( name.getTextContent() )
                    .append( "--" )
                    .append( coordinates[1] )
                    .append( ", " )
                    .append( coordinates[0] )
                    .append( ";" )
                    .append( coordinates[2] )
                    .append( "\n" );
        }
    }

    public static void setupLocationsFile ( String data ) {

        String clientOutput = Utils.getPath().concat( "clients.txt" );
        String pharmacyOutput = Utils.getPath().concat( "pharmacies.txt" );
        String pharmaciesData = data.substring( 0, data.indexOf( "#clients" ) ).trim();
        String clientData = data.substring( data.indexOf( "#clients" ) ).trim();

        writeToFile( pharmacyOutput, pharmaciesData);
        writeToFile( clientOutput, clientData );
    }

    private static void writeToFile ( String output , String data ) {

        try ( BufferedWriter writer = new BufferedWriter( new FileWriter( output, false ) ) ) {

            writer.write( data );

        } catch ( IOException ex ) {
            Logger.log( ex.getMessage() );
        }
    }
}
