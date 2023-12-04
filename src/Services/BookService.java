package Services;

import Entities.Books;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BookService {

    private static BookService instance = new BookService();

    public static BookService getInstance() {
        return instance;
    }

    private ArrayList<Books> booklist = new ArrayList<>();

//    ham ngoai lai ===============
    private int getBookbyID(String ID) {
        if (booklist.isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < booklist.size(); i++) {
                if (ID.equals(booklist.get(i).getID())) {
                    return i;
                }
            }
            return -1;
        }
    }

    private List<Books> getBooknamebyname(String name) {
        List<Books> resultList = new ArrayList();
        if (name != null && !name.isEmpty() && !booklist.isEmpty()) {
            for (Books book : booklist) {
                if (book.getName().contains(name)) {
                    resultList.add(book);
                }
            }
        }
        return resultList;
    }

    private List<Books> getBooknamebynameandid(String name, String pubID) {
        List<Books> List = new ArrayList();
        if (name != null && !name.isEmpty()
                && pubID != null && !pubID.isEmpty() && !booklist.isEmpty()) {
            for (Books book : booklist) {
                if (book.getName().contains(name)
                        && book.getPubID().equalsIgnoreCase(pubID)) {
                    List.add(book);
                }
            }
        }
        return List;
    }

    private int getPublisherbyPubID(String ID) {
        if (PublisherService.getInstance().getPublist().isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < PublisherService.getInstance().getPublist().size(); i++) {
                if (ID.equals(PublisherService.getInstance().getPublist().get(i).getID())) {
                    return i;
                }
            }
            return -1;
        }
    }

    private void sortlist() {
        Collections.sort(booklist, new Comparator<Books>() {
            @Override
            public int compare(Books o1, Books o2) {
                int d = Integer.compare(o2.getQuantity(), o1.getQuantity());
                if (d == 0) {
                    return Float.compare(o1.getPrice(), o2.getPrice());
                }
                return d;
            }
        });
    }

//    ham chinh ===================
    public void addBook() {
        boolean cont = false;
        do {
            String id;
            boolean check = false;
            do {
                id = Utils.input.inputStringwithrex("Book's ID: ", "B\\d{5}$",
                        "ID cannot be blank", "please input true format(B*****)");

                if (getBookbyID(id) == -1) {
                    check = true;
                } else {
                    System.out.println("this ID has already existed");
                    check = false;
                }
            } while (!check);

            boolean checkname = false;
            String name;
            do {
                name = Utils.input.inputString("Name: ", "name can not be blank");
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                if (name.length() > 5 && name.length() < 30) {
                    checkname = true;
                } else {
                    System.out.println("Name has length from 5 to 30 characters.");
                    checkname = false;
                }
            } while (!checkname);

            float price = Utils.input.inputfloat("Price: ", 0);

            int quantity = Utils.input.inputInt("Quantity: ", 0);

            String pubID;
            boolean checkpubid = false;
            do {
                pubID = Utils.input.inputStringwithrex("Publisher's ID: ", "P\\d{5}$",
                        "ID cannot be blank", "please input true format(P*****)");

                if (getPublisherbyPubID(pubID) == -1) {
                    System.out.println("this Publisher's ID is not found.");
                    checkpubid = false;
                } else {
                    checkpubid = true;
                }
            } while (!checkpubid);

            boolean statuscheck = false;
            String status;
            do {
                status = Utils.input.inputString("status: ", "status can not be blank");
                if (status.equalsIgnoreCase("Available")) {
                    statuscheck = true;
                } else if (status.equalsIgnoreCase("Not Available")) {
                    statuscheck = true;
                } else {
                    System.out.println("please input true format(Available or Not Available)");
                    statuscheck = false;
                }
            } while (!statuscheck);

            Books book = new Books(id, name, price, quantity, pubID, status);
            booklist.add(book);
            System.out.println("Book has been added");
            System.out.println("==================");
            String choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
            if (choice.equalsIgnoreCase("yes")) {
                cont = false;
            } else {
                cont = true;
            }
        } while (!cont);
    }

    private void searchBookbybookname() {
        boolean checkname = false;
        String name;
        do {
            name = Utils.input.inputString("Name: ", "name can not be blank");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if (name.length() > 5 && name.length() < 30) {
                checkname = true;
            } else {
                System.out.println("Name has length from 5 to 30 characters.");
                checkname = false;
            }
        } while (!checkname);

        List<Books> reslist = getBooknamebyname(name);

        if (reslist.isEmpty()) {
            System.out.println("Book is not found.");
        } else {
            for (Books book : reslist) {
                System.out.println("------------------");
                System.out.println(book);
            }
        }
    }

    private void searchBookbyPubID() {
        String pubID;
        boolean checkpubid = false;
        do {
            pubID = Utils.input.inputStringwithrex("Publisher's ID: ", "P\\d{5}$",
                    "ID cannot be blank", "please input true format(P*****)");

            if (getPublisherbyPubID(pubID) == -1) {
                System.out.println("this Publisher's ID is not found.");
                checkpubid = false;
            } else {
                checkpubid = true;
            }
        } while (!checkpubid);

        for (int i = 0; i < booklist.size(); i++) {
            if (booklist.get(i).getPubID().equalsIgnoreCase(pubID)) {
                System.out.println("------------------");
                System.out.println(booklist.get(i));
            }
        }
    }

    private void searchBookbyPubIDandbookname() {
        boolean checkname = false;
        String name;
        do {
            name = Utils.input.inputString("Name: ", "name can not be blank");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if (name.length() > 5 && name.length() < 30) {
                checkname = true;
            } else {
                System.out.println("Name has length from 5 to 30 characters.");
                checkname = false;
            }
        } while (!checkname);

        String pubID;
        boolean checkpubid = false;
        do {
            pubID = Utils.input.inputStringwithrex("Publisher's ID: ", "P\\d{5}$",
                    "ID cannot be blank", "please input true format(P*****)");

            if (getPublisherbyPubID(pubID) == -1) {
                System.out.println("this Publisher's ID is not found.");
                checkpubid = false;
            } else {
                checkpubid = true;
            }
        } while (!checkpubid);

        List<Books> reslist = getBooknamebynameandid(name, pubID);
        if (reslist.isEmpty()) {
            System.out.println("Book is not found.");
        } else {
            for (Books book : reslist) {
                System.out.println("------------------");
                System.out.println(book);
            }
        }
    }

    public void searchBook() {
        if (booklist.isEmpty()) {
            System.out.println("==================");
            System.out.println("Book's List is empty.");
            System.out.println("==================");
            return;
        } else {
            int choice;
            do {
                System.out.println("==================");
                System.out.println("1. Search by Book's Name.");
                System.out.println("2. Search by Publisher's ID.");
                System.out.println("3. Search by Book's Name & Publisher's ID.");
                System.out.println("4. Back.");
                choice = Utils.input.inputintlimit("Your choice: ", 0, 4);
                switch (choice) {
                    case 1:
                        searchBookbybookname();
                        break;
                    case 2:
                        searchBookbyPubID();
                        break;
                    case 3:
                        searchBookbyPubIDandbookname();
                        break;
                    default:
                        break;
                }
            } while (choice > 0 && choice < 4);
        }
    }

    public void updateBook() {
        Scanner sc = new Scanner(System.in);
        if (booklist.isEmpty()) {
            System.out.println("==================");
            System.out.println("Book's List is empty.");
            System.out.println("==================");
            return;
        } else {
            boolean cont = false;
            do {
                String id;
                boolean check = false;
                sortlist();
                System.out.println("==================");
                for (Books book : booklist) {
                    System.out.println(book);
                    System.out.println("------------------");
                }
                System.out.println("==================");
                do {
                    id = Utils.input.inputStringwithrex("Book's ID: ", "B\\d{5}$",
                            "ID cannot be blank", "please input true format(B*****)");

                    if (getBookbyID(id) == -1) {
                        System.out.println("this Book's ID is not found.");
                        check = false;
                    } else {
                        check = true;
                    }
                } while (!check);
                int pos = getBookbyID(id);

                boolean checkname = false;
                String name;
                do {
                    System.out.print("Name change to: ");
                    name = sc.nextLine();
                    if (name.isEmpty()) {
                        checkname = true;
                    } else if (name.length() > 5 && name.length() < 30 || name.isEmpty()) {
                        checkname = true;
                    } else {
                        System.out.println("Name has length from 5 to 30 characters.");
                        checkname = false;
                    }
                } while (!checkname);
                if (name.isEmpty()) {
                    System.out.println("Name is not changed.");
                } else {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    booklist.get(pos).setName(name);
                    System.out.println("Name update successful.");
                }

                String price = Utils.input.inputStringwithrexalowemty("Price change to: ", "^(?:-(?:[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))|(?:0|(?:[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))))(?:.\\d+|)$", "Input number please!");
                if (price.isEmpty()) {
                    System.out.println("Price is not changed");
                } else {
                    float pr = Float.parseFloat(price);
                    booklist.get(pos).setPrice(pr);
                    System.out.println("Price update successful.");
                }

                String quantity = Utils.input.inputStringwithrexalowemty("Quantity change to: ", "^\\d+$", "Input number please!");
                if (quantity.isEmpty()) {
                    System.out.println("Quantity is not changed");
                } else {
                    int qu = Integer.parseInt(quantity);
                    booklist.get(pos).setQuantity(qu);
                    System.out.println("Quantity update successful.");
                }

                String pubID;
                boolean checkpubid = false;
                do {
                    pubID = Utils.input.inputStringwithrexalowemty("Publisher's ID change to: ", "P\\d{5}$",
                            "please input true format(P*****)");
                    if (pubID.isEmpty()) {
                        checkpubid = true;
                    } else if (getPublisherbyPubID(pubID) == -1) {
                        System.out.println("this Publisher's ID is not found.");
                        checkpubid = false;
                    } else {
                        checkpubid = true;
                    }
                } while (!checkpubid);
                if (pubID.isEmpty()) {
                    System.out.println("Publisher's ID is not changed");
                } else {
                    booklist.get(pos).setPubID(pubID);
                    System.out.println("Publisher's ID update successful.");
                }

                boolean statuscheck = false;
                String status;
                do {
                    System.out.print("Status change to: ");
                    status = sc.nextLine();
                    if (status.isEmpty()) {
                        statuscheck = true;
                    } else if (status.equalsIgnoreCase("Available")) {
                        statuscheck = true;
                    } else if (status.equalsIgnoreCase("Not Available")) {
                        statuscheck = true;
                    } else {
                        System.out.println("please input true format(Available or Not Available)");
                        statuscheck = false;
                    }
                } while (!statuscheck);
                if (status.isEmpty()) {
                    System.out.println("Status is not changed");
                } else {
                    booklist.get(pos).setStatus(status);
                    System.out.println("Status update successful.");
                }

                System.out.println("==================");
                String choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
                if (choice.equalsIgnoreCase("yes")) {
                    cont = false;
                } else {
                    cont = true;
                }
            } while (!cont);
        }

    }

    public void deleteBook() {
        if (booklist.isEmpty()) {
            System.out.println("==================");
            System.out.println("Book's List is empty.");
            System.out.println("==================");
            return;
        } else {
            String id;
            String choice;
            boolean check = false;
            sortlist();
            System.out.println("==================");
            for (Books book : booklist) {
                System.out.println(book);
                System.out.println("------------------");
            }
            System.out.println("==================");
            do {
                System.out.println("==================");
                id = Utils.input.inputStringwithrex("Book's ID: ", "B\\d{5}$",
                        "ID cannot be blank", "please input true format(B*****)");

                if (getBookbyID(id) == -1) {
                    System.out.println("this ID is not found.");
                    choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
                    if (choice.equalsIgnoreCase("yes")) {
                        System.out.println("==================");
                        check = false;
                    } else {
                        System.out.println("==================");
                        check = true;
                    }
                } else {
                    booklist.remove(getBookbyID(id));
                    System.out.println("Delete successfully.");
                    choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
                    if (choice.equalsIgnoreCase("yes")) {
                        System.out.println("==================");
                        check = false;
                    } else {
                        System.out.println("==================");
                        check = true;
                    }
                }
            } while (!check);
        }
    }

    public void savetoFile() {
        if (booklist.isEmpty()) {
            System.out.println("==================");
            System.out.println("Book's list is empty.");
            System.out.println("==================");
            return;
        } else {
            sortlist();
            try {
                File f = new File("booklist.csv");
                FileWriter fw = new FileWriter(f);
                PrintWriter bw = new PrintWriter(fw);
                for (int i = 0; i < booklist.size(); i++) {
                    Books x = booklist.get(i);
                    bw.println(x.getID() + ","
                            + x.getName() + ","
                            + x.getPrice() + ","
                            + x.getQuantity() + ","
                            + x.getPubID() + ","
                            + x.getStatus());
                }
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        System.out.println("Save to file successfully.");
        System.out.println("==================");
    }

    public void readDatafromfile() {
        try {
            File f = new File("booklist.csv");
            if (!f.exists()) {
                System.out.println("file does not exist.");
                System.out.println("==================");
                return;
            } else {
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                String detail;
                while ((detail = bf.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(detail, ",");
                    String id = stk.nextToken();
                    String name = stk.nextToken();
                    float price = Float.parseFloat(stk.nextToken());
                    int quantity = Integer.parseInt(stk.nextToken());
                    String pubid = stk.nextToken();
                    String status = stk.nextToken();

                    Books book = new Books(id, name, price, quantity, pubid, status);
                    booklist.add(book);
                }
                bf.close();
                fr.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void display() {
        if (booklist.isEmpty()) {
            System.out.println("==================");
            System.out.println("Book's list is empty.");
            System.out.println("==================");
            return;
        } else {
            sortlist();
            System.out.println("==================");
            for (Books book : booklist) {
                System.out.println(book);
                System.out.println("------------------");
            }
            System.out.println("==================");
        }
    }
}
