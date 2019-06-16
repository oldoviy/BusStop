import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import  java.util.*;
import java.awt.*;
import  java.math.*;
import java.io.File;
import java.util.List;
import  java.util.Scanner;


public class TimeTable {

//    \Users\Александр\IdeaProjects\BusTimetable\input.txt

  public static String path;


    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the path to the input .txt file");
        path = scanner.nextLine();
        scanner.close();

        List<Note> list = new ArrayList<Note>();
        List<Note> modifiedList = new ArrayList<Note>();
        List<String> listStr = new ArrayList<String>();
        try { listStr = ReadingFromFile.readingFromFile(path);        // получение текстового документа ввиде массива строк
        }catch (Exception e){}

    for (int i = 0; i < listStr.size(); i++) {                        //Добавление в лист записей
        list.add(ReadingFromFile.lineToNoteValues(listStr.get(i)));
     //\Users\Александр\IdeaProjects\BusTimetable\input.txt   System.out.println(list.get(i).toString());
    }

        for (int i = 0; i < list.size(); i++) {                       //Проверка на эффективность
           outterLoop:
           if(list.get(i).getDuration()<=60){
                for(int j=0; j<list.size();j++){
                    if(i==j)continue ;
                    if((list.get(i).getDepartureTimeShrt()==list.get(j).getDepartureTimeShrt()&list.get(i).getArrivalTimeShrt()>list.get(j).getArrivalTimeShrt())) {
                        break outterLoop;
                    }else if((list.get(i).getDepartureTimeShrt()<list.get(j).getDepartureTimeShrt()&list.get(i).getArrivalTimeShrt()==list.get(j).getArrivalTimeShrt())) {
                        break outterLoop;
                    }else if((list.get(i).getDepartureTimeShrt()<list.get(j).getDepartureTimeShrt()&list.get(i).getArrivalTimeShrt()>list.get(j).getArrivalTimeShrt())) {
                        break outterLoop;
                    }else if((list.get(i).getDepartureTimeShrt()==list.get(j).getDepartureTimeShrt()&list.get(i).getArrivalTimeShrt()==list.get(j).getArrivalTimeShrt())&list.get(j).getService().equals("Posh")) {
                        break outterLoop;
                    }

                }
               modifiedList.add(list.get(i)) ;
            }
        }


        Collections.sort(modifiedList);                                             //сортировка через переопределение метода compareTo()
        Collections.sort(modifiedList,Note.DepartureTimeComparator);                //сортировка через Comparator

        PrintWriter printWriter = new PrintWriter(new File("output"));    //инициализация для записи в файл

                                                                                    //вывод и запись конечноо результата
        System.out.println();
        for (int i = 0; i < modifiedList.size(); i++) {
            if (modifiedList.get(i).getService().equals("Posh")) {
                System.out.println(modifiedList.get(i).toString());
                printWriter.println(modifiedList.get(i).toString());
           }


        }
        printWriter.println();
        System.out.println();
        for (int i = 0; i < modifiedList.size(); i++) {
            if (modifiedList.get(i).getService().equals("Grotty")) {
                System.out.println(modifiedList.get(i).toString());
                printWriter.println(modifiedList.get(i).toString());
            }


        }

        printWriter.close();
    }
}