import java.io.*;
import java.util.Scanner;

/**
 *  The class creates an image in form of a greyscale image which is
 *  read in from a file. It contains a method to crop the left upper
 *  half of the picture and write it out again.
 *
 *  @version 2017-11-01
 *  @author Manfred Kerber updated by lxf736
 */
public class PGMImage{
    private int width;
    private int height;
    private int maxShade;
    private String typeOfFile;
    private short[][] pixels;

    /**
     *  @param filename The name of a file that contains an image in
     *  pgm format of type P2.
     *
     */
    public PGMImage(String filename) {
        // try since the file may not exist.
        try {
            // we read from the scanner s which is linked to the file filename.
            Scanner s = new Scanner(new File(filename));

            /* The field variables are assigned by reading in from a
               file. The file should start with something like
               P2
               150 176
               255

               where P2 is the file type, 150 the width of the image, 176
               the height, and 255 the maximal grey value. Then follow
               150*176 grey values between 0 and 255.
            */
    
            // We read the initial element that is in our case "P2"
            typeOfFile = s.next();
            // Next we read the width, the height, and the maxShade.
            width = s.nextInt();
            height = s.nextInt();
            maxShade = s.nextInt();
            //We initialize and read in the different pixels.
            pixels = new short[height][width];
            for (int i=0; i<height; i++){
                for (int j=0; j<width; j++) {
                    pixels[i][j] = s.nextShort();
                }
            }
            // We finished reading and close the scanner s.
            s.close();
        }
        catch (IOException e){
            //If the file is not found, an error message is printed,
            //and an empty image is created.
            System.out.println("File not found.");

            typeOfFile = "P2";
            width = 0;
            height = 0;
            maxShade = 0;
            pixels = new short[width][height];
        }
    }

    /**
     *  @return The width of the image.
     */
    public int getWidth(){
        return width;
    }

    /**
     *  @return The height of the image.
     */
    public int getHeight(){
        return height;
    }
    
    /**
     *  @return The maximal grey value of the image.
     */
    public int getMaxShade(){
        return maxShade;
    }

    /**
     *  @return The file type of the image.
     */
    public String getTypeOfFile(){
        return typeOfFile;
    }

    /**
     *  @return The matrix representing the pixels of the image.
     */
    public short[][] getPixels(){
        return pixels;
    }

    
    /**
     *  The method crops the left upper half of an image.
     *  @param filename The filename of the file in which the cropped
     *  image should be saved.
     */
    public void crop (String filename){
	try {
	    BufferedWriter out = 
		new BufferedWriter(new FileWriter(filename));
	    // We write the file type to out.
	    out.write(getTypeOfFile() + "\n");

	    // We write the dimensions to out.
	    out.write((getWidth()/2) + " " + (getHeight()/2) +"\n");
	    
	    // We write maximal number.
	    out.write(getMaxShade() + "\n");
	    
	    byte counter = 0;
	    for (int i=0; i<getHeight()/2; i++){
		for (int j=0; j<getWidth()/2; j++){
		    out.write(getPixels()[i][j] + " ");
		    counter++;
		    if (counter == 15){		 
                        out.write("\n");
                        counter = 0;
                    }
		}
	    }
	    out.write("\n");
	    // We close the file.
	    out.close();
	}
	catch (IOException e){
            //Errors are caught.
            System.out.println("File not found.");
        }
    }
    
    public int [] [] quarter (String filename) {
    		//We declare the variables required to calculate the average of each block of 4 pixels
	    	int counter = 0;
	    	double store1 = 0;
	    	double store2 = 0;
	    	double store3 = 0;
	    	double store4 = 0;
	    	double average = 0;
	    	
	    	//We check for even numbers of rows and columns, if either is not even we ignore the final row/column
	    	int height;
	    	int width;
	    	if (this.height % 2 ==0){
	    		height = this.height;
	    	} else {
	    		height = this.height - 1;
	    	}
	    	if (this.width % 2 ==0){
	    		width = this.width;
	    	} else {
	    		width = this.width - 1;
	    	}
	    	
	    	int result [] [] = new int [height/2] [width/2];
	    	
	    	try {
		    	BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		    	// We write the file type to out.
		    	out.write(getTypeOfFile() + "\n");
		
		    	// We write the dimensions to out.
		    	out.write((width/2) + " " + (height/2) +"\n");
	 
		    	// We write maximal number.
		   	out.write(getMaxShade() + "\n");
		    	
		   	//We compress the image and write out the pixels
			for (int i=0; i < height; i+=2){
		   		for (int j=0; j < width; j+=2){
		   			store1 = pixels[i][j];
		           	store2 = pixels[i][j+1];
		           	store3 = pixels[i+1][j];
		           	store4 = pixels[i+1][j+1];
		           	average = Math.round((store1 + store2 + store3 + store4) / 4);
		           	result [i/2][j/2] = (int) average;
		           	counter++;
		           	out.write(result [i/2][j/2] + " ");
				    if (counter == 15) {		 
				    		out.write("\n");
			            counter = 0;
			        }
			   	}
			}
			
			out.write("\n");
			// We close the file.
			out.close();
			
	    	} catch (IOException e){
		        System.out.println("File not found.");
		}
	    	return result;
    }
    
    public int[][] rotate(String filename) {
	    	int result [] [] = new int [width] [height];
	    	int counter = 0;
	    	
	    	try {
	    	    BufferedWriter out = 
	    		new BufferedWriter(new FileWriter(filename));
	    	    // We write the file type to out.
	    	    out.write(getTypeOfFile() + "\n");

	    	    // We write the dimensions to out.
	    	    out.write(getHeight() + " " + getWidth() +"\n");
	    	    
	    	    // We write maximal number.
	    	    out.write(getMaxShade() + "\n");
	    	    
		   	//We rotate the array storing rotated values in result
	    		for (int i = 0; i < width; i++){
			   		for (int j = 0; j < height; j++){
			   			result [i] [j] = pixels [height-j-1][i];
			   			System.out.println(result [i] [j]);
			   			counter++;
			           	out.write(result [i] [j]+ " ");
					    if (counter == 15) {		 
					    		out.write("\n");
				            counter = 0;
				        }
				    }
			 }
	    		
	    		out.write("\n");
			// We close the file.
			out.close();
			
	    	} catch (IOException e){
		        System.out.println("File not found.");
		}
		return result;
    }
    	
    public int[][] bw(String filename){
	    	int result [] [] = new int [height] [width];
	    	try {
	    	    BufferedWriter out = 
	    		new BufferedWriter(new FileWriter(filename));
	    	    // We write the file type to out.
	    	    out.write("P1" + "\n");
	
	    	    // We write the dimensions to out.
	    	    out.write(getWidth() + " " + getHeight() +"\n");
	    	    
	    	    // We write maximal number.
	    	    out.write(1 + "\n");
	    	    
	    	    // We calculate the average grey value to determine which pixels should be white and which should be black
		    	int average = 0;
		    	int counter = 0;
		    	for (int i = 0; i < height; i++){
			   		for (int j = 0; j < width; j++){
			   			average = average + pixels[i][j];
			   			counter++;
		   			}
		    	}
		    	average = average / counter;
		    	counter = 0;
		    	
		    	// We compare each pixel with the average to determine whether the pixel should be black or white
		    	for (int i = 0; i < height; i++){
			   		for (int j = 0; j < width; j++){
			   			if (pixels[i][j] > average) {
			   				result[i][j] = 0;
			   			} else {
			   				result[i][j] = 1;
			   			}
			   			System.out.println(result[i][j]);
			   			counter++;
			   			if (result [i][j] == 0) {
			   				out.write(1 + " ");
			   			} else {
			   				out.write(0 + " ");
			   			}
					    if (counter == 15) {		 
					    		out.write("\n");
				            counter = 0;
				        }
		   			}
		    	}
		    	
	    		out.write("\n");
			// We close the file.
			out.close();
			
	    	} catch (IOException e){
		        System.out.println("File not found.");
		}
	    	return result;
    }


    /*
     * An example.
     */
    public static void main(String[] args) {
    PGMImage cs = new PGMImage("ComputerScience.pgm");
    cs.crop("ComputerScienceCrop.pgm");
    }
}