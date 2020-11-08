package util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public interface DateMapper {
    static Date toDate(String date){
        return Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    static LocalDate toLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
