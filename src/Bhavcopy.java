import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Bhavcopy {

	public static void main(String[] args) {
		
		DateFormat df = new SimpleDateFormat("ddMMMyyyy", Locale.ENGLISH);
		DateFormat monthFormatter = new SimpleDateFormat("MMM", Locale.ENGLISH);
		DateFormat yearFormatter = new SimpleDateFormat("yyyy", Locale.ENGLISH);
		Calendar c = Calendar.getInstance();
		try {
			// Setting the date to the given date
			c.setTime(df.parse("05APR2018"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 365; i++) {

			// Number of Days to add
			c.add(Calendar.DAY_OF_MONTH, 1);
			// Date after adding the days to the given date
			String newDate = df.format(c.getTime()).toUpperCase();
			System.out.println(newDate);
			String newYear = yearFormatter.format(c.getTime());
			System.out.println(newYear);
			String newMonth = monthFormatter.format(c.getTime()).toUpperCase();
			System.out.println(newMonth);
			String FILE_URL = "https://www.nseindia.com/content/historical/EQUITIES/" + newYear + "/" + newMonth + "/cm"
					+ newDate + "bhav.csv.zip";
			try {
				URL url = new URL(FILE_URL);
				ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(newDate + "csv.zip");
				FileChannel fileChannel = fileOutputStream.getChannel();
				fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
				fileOutputStream.flush();

				fileOutputStream.close();
			} catch (IOException e) {
				System.out.println("no data for " + newDate);
			}
		}
	}
}
