package downloadmanager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created on 14-12-5.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public class ErrorHandler implements ErrorListener {


  private final String urlAddress;
  private final String fileName;
  Display display;

  public ErrorHandler(String urlAddress, String fileName, Display display) {

    this.urlAddress = urlAddress;
    this.fileName = fileName;
    this.display = display;
  }

  @Override
  public boolean catchEmptyURLorFileField() {
    if (urlAddress.equals("") || fileName.equals("")) {
      display.showEmptyURLorFileFieldmessage();
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean catchUnexistingURLaddres() {
    int code=0;
    try {
      URL url = new URL(urlAddress);
      HttpURLConnection huc =  ( HttpURLConnection )  url.openConnection ();
      huc.setRequestMethod ("GET");
      huc.connect () ;
      code = huc.getResponseCode() ;

    } catch (ProtocolException pe) {

    } catch (IOException ioe){}

    if (code!=200){
      display.showWrongURLAddress();
      return true;}
    return false;
  }
}
