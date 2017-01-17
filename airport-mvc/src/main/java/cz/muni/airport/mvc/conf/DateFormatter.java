package cz.muni.airport.mvc.conf;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Richard Bariny on 13.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
@Component
public class DateFormatter implements Formatter<Date> {

    @Autowired
    private MessageSource messageSource;


    public DateFormatter() {
        super();
    }

    public Date parse(final String text, final Locale locale) throws ParseException {
        DateTime dateTime_Utc = new DateTime( text , DateTimeZone.UTC );
        // Specifying a time zone to apply, rather than implicitly assigning the JVM’s current default.
        return dateTime_Utc.toDate();
    }

    public String print(final Date object, final Locale locale) {
      // final SimpleDateFormat dateFormat = createDateFormat(locale);
      //  return dateFormat.format(object);
        DateTime dateTime_Utc = new DateTime(object);
        return dateTime_Utc.toString(createDateFormat());
    }

    private DateTimeFormatter createDateFormat() {
        final String format = "YYYY-MM-DD'T'hh:mm";
        return DateTimeFormat.forPattern(format);
    }

}
