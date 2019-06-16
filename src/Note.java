import javafx.util.converter.TimeStringConverter;

import java.sql.Time;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Note implements Comparable<Note>, Comparator<Note> {                         // класс записей
    private String arrivalTime;
    private String departureTime;

    private byte arrivalHour;
    private byte arrivalMinute;
    private byte departureHour;
    private byte departureMinute;
    private short duration;
    private String service;



    Note(String service, byte departureHour, byte departureMinute,byte arrivalHour,byte arrivalMinute){
        this.service = service;
        this.departureHour = departureHour;
        this.departureMinute = departureMinute;
        this.arrivalHour = arrivalHour;
        this.arrivalMinute = arrivalMinute;
        this.departureTime = departureHour<10?"0":""+departureHour+ (Byte.valueOf(departureMinute)<10?":0":":") +departureMinute;
        this.arrivalTime = arrivalHour<10?"0":""+arrivalHour+ (Byte.valueOf(arrivalMinute)<10?":0":":") +arrivalMinute;
        this.duration = (short)((arrivalHour-departureHour)*60+arrivalMinute-departureMinute);


    }

    @Override
    public String toString() {
        return service+" "+ departureTime + " " +arrivalTime ;
    } ;

    public short getDuration() {
        return duration;
    }
    public short getArrivalTimeShrt(){
       return (short)(arrivalHour*100+arrivalMinute);
    }
    public short getDepartureTimeShrt(){ return (short)(departureHour*100+departureMinute); }

    public String getService(){
        return service;
    }

    @Override
    public int compareTo(Note o) {
        return this.getDepartureTimeShrt()-o.getDepartureTimeShrt();
    }

    public static Comparator<Note> DepartureTimeComparator = new Comparator<Note>() {

        @Override
        public int compare(Note n1, Note n2) {
            return n1.getDepartureTimeShrt() - n2.getDepartureTimeShrt();
        }
    };

    @Override
    public int compare(Note n1, Note n2) {
        return 0 ;
    }
}
