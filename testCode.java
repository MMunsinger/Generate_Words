import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class testCode{

  public static void main (String[] args) throws IOException{


//instance of phoneKeypadMap
keypadMapper phone = new keypadMapper();

//Create list based on words in file
List<String> words = new ArrayList<>();
      try {   
        File myObj = new File("wordBank.txt");  //creates file in projectfolder
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          words.add(data);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }

//Creates new list of numbers from words list
List<String> listOfNumbers = new ArrayList<>();
for (int i = 0; i <= words.size()-1; i++)
    {
        String wordToNumber = "";
        char c;
        int n;
        String word = words.get(i);
        for (int j = 0; j<= word.length()-1; j++)
        {     
             c = word.charAt(j);
             //System.out.println("c: " + c);                             //logging
             n = phone.getValue(c);
             //System.out.println("n is: " + n);                          //logging
             wordToNumber += String.valueOf(n);
        }
        //System.out.println("wordToNumber: " + wordToNumber);            //logging
        listOfNumbers.add(wordToNumber);

        //System.out.println("Number added to List: " + listOfNumbers.get(i));//logging
    }

        //System.out.println(Arrays.toString(listOfNumbers.toArray()));       //logging


    //Creates new map where words are keys and numbers are values
    Map<String, String> numToWordMap = new HashMap<>();
    for ( int i = 0; i <= listOfNumbers.size()-1; i++)
    {
        numToWordMap.put(listOfNumbers.get(i), words.get(i));
    }
    
    //prints key value pairs of numToWordMap
    //System.out.println(Arrays.asList(numToWordMap));                    //logging

    //Test writing to file
    try {
        FileWriter myWriter = new FileWriter("testKeyValuePairsFile.txt"); //file created in project folder
        myWriter.write(numToWordMap.toString());
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }


      //Test calling api and write response to file named testFile.json
      URL url = new URL("https://twinword-word-associations-v1.p.rapidapi.com/associations/?entry=Coffee");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    
      connection.setRequestMethod("GET");
      connection.setRequestProperty("X-RapidAPI-Key", "1e06aa558bmsh2b6c174d4ca866ap15dad5jsn76560473eec6");
      connection.setRequestProperty("X-RapidAPI-Host", "twinword-word-associations-v1.p.rapidapi.com");
      int responseCode = connection.getResponseCode();
      if (responseCode == 200) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
        FileWriter myWriter2 = new FileWriter("testFile.json");
        myWriter2.write(response.toString());
        myWriter2.close();
      } else {
        System.out.println("Failed to get data. Response code: " + responseCode);
      }
    

  }

    //Read File that contains wordbank to be used in words[] TO CONSOLE
    // try {   
    //     File myObj = new File("wordBank.txt");
    //     Scanner myReader = new Scanner(myObj);
    //     while (myReader.hasNextLine()) {
    //       String data = myReader.nextLine();
    //       System.out.println(data);
    //     }
    //     myReader.close();
    //   } catch (FileNotFoundException e) {
    //     System.out.println("An error occurred.");
    //     e.printStackTrace();
    //   }

      //Read File that contains wordbank to be used in words[] TOWORDS[]
    //   List<String> testWords = new ArrayList<>(); //<<need to adjust size
    //   try {   
    //     File myObj = new File("wordBank.txt");
    //     Scanner myReader = new Scanner(myObj);
    //     while (myReader.hasNextLine()) {
    //       String data = myReader.nextLine();
    //       testWords.add(data);
    //     }
    //     myReader.close();
    //   } catch (FileNotFoundException e) {
    //     System.out.println("An error occurred.");
    //     e.printStackTrace();
    //   }

    //   //test print testWords[]
    //   System.out.println(Arrays.toString(testWords.toArray()));

      
}
