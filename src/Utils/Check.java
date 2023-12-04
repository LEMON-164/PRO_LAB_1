/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Books;
import Entities.Publishers;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Check {

    

    public int getBookbybookID(String ID, ArrayList<Books> list) {
        for (int i = 0; i < list.size(); i++) {
            if (ID.equals(list.get(i).getID())) {
                return i;
            }
        }
        return 0;
    }
}
