package downloadmanager;

/**
 * Created on 14-12-5.
 *
 * @author Panayot Kulchev <panayotkulchev@gmail.com>
 */
public interface ErrorListener {

  boolean catchEmptyURLorFileField();

  boolean catchUnexistingURLaddres();



}
