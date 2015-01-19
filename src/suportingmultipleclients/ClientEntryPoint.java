package suportingmultipleclients;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class ClientEntryPoint {
  public static void main(String[] args) {
    ClientView view = new ClientView();

//    UserMessages messages = new UserMessages() {
//      @Override
//      public String connectClient() {
//        return "Client is successful connected to Server ";
//      }
//    };

    Client client = new Client(view);

    view.createFrame(client,"localhost",3333);

  }
}
