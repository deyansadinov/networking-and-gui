package downloadmanager;

/**
 * Created on 14-12-3.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public interface Display {

  void udateProgresBar(int value);

  void showEmptyURLorFileFieldmessage();

  void showWrongURLAddress();
}
