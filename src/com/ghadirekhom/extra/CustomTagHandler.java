package com.ghadirekhom.extra;

import org.xml.sax.XMLReader;

import android.text.Editable;
import android.text.Html.TagHandler;

public class CustomTagHandler implements TagHandler {

	@Override
	public void handleTag(boolean opening, String tag, Editable output,
			XMLReader xmlReader) {
		// you may add more tag handler which are not supported by android here
		if ("li".equals(tag)) {
			if (opening) {
				output.append(" � ");
			} else {
				output.append("\n");
			}
		}
	}
}