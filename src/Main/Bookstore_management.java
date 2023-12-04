/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Services.BookService;
import Services.PublisherService;

/**
 *
 * @author Admin
 */
public class Bookstore_management {

    public static void main(String[] args) {
        int choice = 0;
        PublisherService.getInstance().readDatafromfile();
        BookService.getInstance().readDatafromfile();
        do {
            System.out.println("___Bookstore Management___");
            System.out.println("1. Publishers’ management");
            System.out.println("2. Books management");
            System.out.println("3. Quit");
            choice = Utils.input.inputintlimit("Your choice: ", 0, 3);
            switch (choice) {
                case 1:
                    int ch;

                    do {
                        System.out.println("==================");
                        System.out.println("__Publishers’ management__");
                        System.out.println("1. Create a Publisher");
                        System.out.println("2. Delete the Publisher");
                        System.out.println("3. Save the Publishers list to file");
                        System.out.println("4. Print the Publisher list from the file.");
                        System.out.println("5. Back to main menu");
                        ch = Utils.input.inputintlimit("Your choice: ", 0, 5);
                        switch (ch) {
                            case 1:
                                PublisherService.getInstance().add();
                                break;
                            case 2:
                                PublisherService.getInstance().deletePublisher();
                                break;
                            case 3:
                                PublisherService.getInstance().savetoFile();
                                break;
                            case 4:
                                PublisherService.getInstance().display();
                                break;
                            default:
                                break;
                        }
                    } while (ch > 0 && ch < 5);
                    break;
                case 2:
                    int c;

                    do {
                        System.out.println("__Books management__");
                        System.out.println("1. Create a Book");
                        System.out.println("2. Search the Book");
                        System.out.println("3. Update a Book");
                        System.out.println("4. Delete the Book");
                        System.out.println("5. Save the Books list to file");
                        System.out.println("6. Print the Books list from the file.");
                        System.out.println("7. Back to main menu");
                        c = Utils.input.inputintlimit("Your choice: ", 0, 7);
                        switch (c) {
                            case 1:
                                BookService.getInstance().addBook();
                                break;
                            case 2:
                                BookService.getInstance().searchBook();
                                break;
                            case 3:
                                BookService.getInstance().updateBook();
                                break;
                            case 4:
                                BookService.getInstance().deleteBook();
                                break;
                            case 5:
                                BookService.getInstance().savetoFile();
                                break;
                            case 6:
                                BookService.getInstance().display();
                                break;
                            default:
                                break;
                        }
                    } while (c > 0 && c < 7);
                    break;
                default:
                    break;
            }
        } while (choice > 0 && choice < 3);
    }
}
