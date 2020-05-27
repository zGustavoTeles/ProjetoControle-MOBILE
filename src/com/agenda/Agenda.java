package com.agenda;

import com.agenda.ArtCalendarBox;
import totalcross.sys.Settings;
import totalcross.ui.Edit;
import totalcross.util.Date;

public class Agenda {
	

	public static void setDateByCalendarBox(Edit editreceived) throws Exception {
		ArtCalendarBox cal = new ArtCalendarBox();

		cal.popup();
		Date d = cal.getSelectedDate();
		if (d == null) {
			editreceived.setText("");
		} else {
			editreceived.setText(d.toString(Settings.DATE_DMY));
		}
	}

}
