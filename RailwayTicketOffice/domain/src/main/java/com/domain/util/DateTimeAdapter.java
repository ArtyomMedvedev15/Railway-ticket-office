package com.domain.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateTimeAdapter extends XmlAdapter<String, Date> {
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public Date unmarshal(String xml) throws Exception {
        return (Date) dateFormat.parse(xml);
    }

    @Override
    public String marshal(Date object) throws Exception {
        return dateFormat.format(object);
    }
}
