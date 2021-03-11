package validator;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.io.FileNotFoundException;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JSONVerify {

  /* 
  * A regex to verify a zipcode if it valid or not.
  * A valid US zipcode must consist of five digits, a hyphen (or dash), 
  * and four digits
  * reference: https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s14.html
  */
  public static String regex = "^[0-9]{5}(?:-[0-9]{4})?$";

  /* A set to check for duplicates existance */
  public static HashSet<JSONObject> set = new HashSet<>();

  /*
  * A function to verify a customer record; check if the name,
  * address, and zipcode is not empty, null or invalid.
  * Also finds duplicates records and prints out in the 
  * console
  * @param  customer  a json object of a customer record
  */
  public static void verify(JSONObject customer) {

    // Get the data from the customer record; if the tag
    // does not exist it returns back null
    String name = (String) customer.get("name");
    String address = (String) customer.get("address");
    String zip = (String) customer.get("zip");


    // I disabled the extra print statement that provided
    // extra info of what was missing. Ff you want to print all 
    // the messages that a record was missing without breaking
    // you can make a string and append all the messages into
    // it and print it in the end 

    if (name == "" || name == null) {
      // System.out.println("name is missing or NULL");
      System.out.println(customer);
      return;
    }
    else if (address == "" || address == null) {
      // System.out.println("address is missing or NULL");
      System.out.println(customer);
      return;
    }
    else if (zip == "" || zip == null || !zip.matches(regex)) {
      // System.out.println("zip is missing, NULL, invalid");
      System.out.println(customer);
      return;
    }

    // If it a valid record; it will added to set, but if the add returns
    // false it means the record is a duplicate
    if (!set.add(customer)) {
      // System.out.println("This customer record is a duplicate");
      System.out.println(customer);
    }
  }

  public static void main(String[] args) {
    // A try-with-resources which will close the file when it done
    try(FileReader fr = new FileReader("data.json")) {

      // A json parser that parse the json file and later will cast
      // into an array of json object 
      JSONParser jp = new JSONParser();
      Object jsonObj = jp.parse(fr);

      JSONArray customerList = (JSONArray) jsonObj;
      
      // Iterates the array of json object and checks if a valid record
      for (Object customer: customerList) {
        verify((JSONObject) customer);
      }
    } 
    //Exception handling 
    catch (FileNotFoundException e) {
          e.printStackTrace();
    } 
    catch (IOException e) {
          e.printStackTrace();
    } 
    catch (ParseException e) {
          e.printStackTrace();
    }
  }
}