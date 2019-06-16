import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadingFromFile   {


public static Note lineToNoteValues(String str){ //метод возвращающий запись с занесенными значениями через разделение строки
       String[]values = str.split("[ :]+");
      return new Note(values[0],Byte.valueOf(values[1]),Byte.valueOf(values[2]),Byte.valueOf(values[3]),Byte.valueOf(values[4]));
}

public static ArrayList<String> readingFromFile(String path) throws FileNotFoundException { //метод возвращающий тесктовы файл в виде массива
        Scanner scanner = new Scanner(new File(path));
        List<String> list = new ArrayList<String>();
        while (scanner.hasNextLine())  list.add( scanner.nextLine());
        scanner.close();
        if(!list.isEmpty())
            return (ArrayList<String>) list;
            else { list.add("Empty File Document");
            return  (ArrayList<String>) list;
        }

    }
}
