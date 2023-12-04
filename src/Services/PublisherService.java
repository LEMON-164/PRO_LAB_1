package Services;

import Entities.Publishers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class PublisherService {

    private static PublisherService instance = new PublisherService();

    public static PublisherService getInstance() {
        return instance;
    }

    private ArrayList<Publishers> pubList = new ArrayList<>();

    int pos;

    private PublisherService() {
    }

    public ArrayList<Publishers> getPublist() {
        return pubList;
    }

    public void setPublist(ArrayList<Publishers> Publist) {
        this.pubList = Publist;
    }

//      ham ngoai lai =======================================================
    public int getPublisherbyPubID(String ID) {
        if (pubList.isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < pubList.size(); i++) {
                if (ID.equals(pubList.get(i).getID())) {
                    return i;
                }
            }
            return -1;
        }
    }

    private void sortList() {
        Collections.sort(pubList, new Comparator<Publishers>() {
            @Override
            public int compare(Publishers o1, Publishers o2) {
                int d = Integer.compare(o1.getName().charAt(0), o2.getName().charAt(0));
                return d;
            }
        });
    }

    public void readDatafromfile() {
        try {
            File f = new File("publisherList.csv");
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
                    String phone = stk.nextToken();
                    Publishers pub = new Publishers(id, name, phone);
                    pubList.add(pub);
                }
                bf.close();
                fr.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//      ham chinh===========================================================
    public void add() {
        System.out.println("==================");
        boolean cont = false;
        do {
            String id;
            boolean check = false;
            do {
                id = Utils.input.inputStringwithrex("Publisher's ID: ", "P\\d{5}$",
                        "ID cannot be blank", "please input true format(P*****)");

                if (getPublisherbyPubID(id) == -1) {
                    check = true;
                } else {
                    System.out.println("this ID has already existed");
                    check = false;
                }
            } while (!check);

            String name = Utils.input.inputString("Name: ", "name can not be blank");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

            String phone = Utils.input.inputStringwithrex("input phone: ", "^\\d{10}$",
                    "phone cannot be empty", "please input phone with 10 numbers");

            Publishers pub = new Publishers(id, name, phone);
            pubList.add(pub);
            System.out.println("Publisher has been added");
            System.out.println("==================");
            setPublist(pubList);
            String choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("==================");
                cont = false;
            } else {
                System.out.println("==================");
                cont = true;
            }
        } while (!cont);

    }

    public void deletePublisher() {
        if (pubList.isEmpty()) {
            System.out.println("==================");
            System.out.println("list is empty.");
            System.out.println("==================");
            return;
        } else {
            String ID;
            boolean check = false;
            do {
                ID = Utils.input.inputStringwithrex("Publisher's ID: ", "P\\d{5}$",
                        "ID cannot be blank", "please input true format(P*****)");

                if (getPublisherbyPubID(ID) != -1) {
                    pos = getPublisherbyPubID(ID);
                    pubList.remove(pos);
                    System.out.println("Delete successfully.");
                    System.out.println("==================");
                    String choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
                    if (choice.equalsIgnoreCase("yes")) {
                        check = false;
                    } else {
                        check = true;
                    }
                } else {
                    System.out.println("==================");
                    System.out.println("this ID does not exist");
                    System.out.println("Delete fail.");
                    System.out.println("==================");
                    String choice = Utils.input.inputString("do you want to continue?(yes/no): ", "this can not be blank");
                    if (choice.equalsIgnoreCase("yes")) {
                        check = false;
                    } else {
                        check = true;
                    }
                }
            } while (!check);

        }

    }

    public void savetoFile() {
        if (pubList.isEmpty()) {
            System.out.println("==================");
            System.out.println("list is empty.");
            return;
        } else {
            sortList();
            try {
                File f = new File("publisherList.csv");
                FileWriter fw = new FileWriter(f);
                PrintWriter bw = new PrintWriter(fw);
                for (int i = 0; i < pubList.size(); i++) {
                    Publishers x = pubList.get(i);
                    bw.println(x.getID() + "," + x.getName()
                            + "," + x.getPhone());
                }
                bw.close();
                fw.close();
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        System.out.println("==================");
        System.out.println("Save to file successfully.");
    }

    public void display() {
        if (pubList.isEmpty()) {
            System.out.println("==================");
            System.out.println("list is empty.");
            return;
        } else {
            sortList();
            for (int i = 0; i < pubList.size(); i++) {
                System.out.println("------------------");
                System.out.println(pubList.get(i));
            }
        }
    }
}
