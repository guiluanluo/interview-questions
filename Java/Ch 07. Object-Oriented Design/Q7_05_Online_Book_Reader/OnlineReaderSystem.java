package Q7_05_Online_Book_Reader;

/**
 * Online book reader: design the data structures for an online book reader system
 *
 * hint 344: think about all the different funcitonality a system to read books online would have to support. you don't
 * have to do everything, but you should think about makeing your assuptions explicit.
 */
public class OnlineReaderSystem {

  private Library library;
  private UserManager userManager;
  private Display display;

  private Book activeBook;
  private User activeUser;

  public OnlineReaderSystem() {
    userManager = new UserManager();
    library = new Library();
    display = new Display();
  }

  public Library getLibrary() {
    return library;
  }

  public UserManager getUserManager() {
    return userManager;
  }

  public Display getDisplay() {
    return display;
  }

  public Book getActiveBook() {
    return activeBook;
  }

  public void setActiveBook(Book book) {
    display.displayBook(book);
    activeBook = book;
  }

  public User getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(User user) {
    activeUser = user;
    display.displayUser(user);
  }
}
