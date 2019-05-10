
import com.rz.librarycore.RandomValue;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class DbIdGenerator {

    public static void main(String args[]) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("DATE TIME: [" + simpleDateFormat.format(date) + "]");
        String newId = RandomValue.getRandId(1111, 9999);
        System.out.println("NEW ID: [" + newId + "]");
    }
}
