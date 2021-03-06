import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class lzw {
	List<String> dictionary;
	public lzw(){
       dictionary = new ArrayList<String>(256);
        for(int i = 0; i < 255; i++)
        {
            String temp = (char)(i)+"";
            dictionary.add(temp);
        }
	}
    public static void main(String args[])
    {
        String inFile = "H:/SCU/Courses/coen350/assignment/input.txt";
        String compressedFile = "H:/SCU/Courses/coen350/assignment/compressed.txt";
        String decompressedFile = "H:/SCU/Courses/coen350/assignment/decompressed.txt";
        lzw obj = new lzw();
        obj.compress(inFile, compressedFile);
        obj.decompress(compressedFile, decompressedFile);
    }

    /**
     * @param inFilePath String with the path of the
     *                   file to be compressed.
     * @param outFilePath String with the path of the
     *                    file to write the compressed
     *                    output to.
     * @return boolean - True if compression is successful,
     *                   false otherwise.
     */
    public boolean compress(String inFilePath, String outFilePath)
    {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            //Prepare Input File
            reader = new BufferedReader(new FileReader(inFilePath));
            //Prepare Output File
            writer = new BufferedWriter(new FileWriter(outFilePath, false));

            String word = "";
            String output ="";
            int i;
            int indexCount = 0;
            int count =0;
            int j=0;
            int charCount = 0;
            while ((i=reader.read())!=-1){
             char x = (char) i;
             if (x!='\0'){
            	 charCount++;
            	if (dictionary.contains(word+x)){
            		word = word + x;
            	}
            	else{
            		output = output+" "+dictionary.indexOf(word);
            		count = dictionary.indexOf(word);
                    j=0;
                    while (count>0){
                    	j++;
                    	count = count>>1;
                    }
                    indexCount= indexCount + j;           		
            		dictionary.add(word+x);
            		word = String.valueOf(x);
            	}
            }
            }
            output = output+" "+dictionary.indexOf(word);
            count = dictionary.indexOf(word);
            j=0;
            while (count>0){
            	j++;
            	count = count>>1;
            }
            indexCount= indexCount + j;
            writer.write(output);
            reader.close();
            writer.close();
            System.out.println("Indices in compressed word are "+output);
            System.out.println("Created dictionary :");
            for (String s:dictionary){
            	System.out.print(s+" ");
            }
            System.out.println();          
            System.out.println("Compression Ratio is"+(float)indexCount/(charCount*8));
            return true;
        }
            
        catch( FileNotFoundException e)
        {
            System.out.println("Input file not found. Exiting.");
            return false;
        }
        catch (IOException e)
        {
            System.out.println("Error creating output file. Exiting.");
            return false;
        }

    }

    /**
     * @param inFilePath String with the path of the file
     *                   generated by the compression function
     *                   to be decompressed.
     * @param outFilePath String with path of the file to write
     *                    the decompressed output to.
     * @return boolean - true if successful, false otherwise.
     */
    public  boolean decompress(String inFilePath, String outFilePath)
    {
        BufferedReader reader;
        BufferedWriter writer;
        Scanner in;
        try
        {
            //Prepare Input File
            reader = new BufferedReader(new FileReader(inFilePath));
            in = new Scanner(reader);

            //Prepare Output File
            writer = new BufferedWriter(new FileWriter(outFilePath, false));
            String line;
            while ((line=reader.readLine())!=null){
            	String[] values = line.trim().split("\\s+");
            	for (String s:values){
            		writer.write((dictionary.get(Integer.valueOf(s))));
            	}
            }

            in.close();
            reader.close();
            writer.close();
            return true;
        }
        catch( FileNotFoundException e)
        {
            System.out.println("Input file not found. Exiting.");
            return false;
        }
        catch (IOException e)
        {
            System.out.println("Error creating output file. Exiting.");
            return false;
        }
    }
}
