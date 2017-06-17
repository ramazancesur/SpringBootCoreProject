package com.stok.ramazan.settings;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by LocalAdmin on 09.06.2017.
 */
public class AdapterCDATA extends XmlAdapter<String, String> {
    @Override
    public String marshal(String arg0) throws Exception {
        return "<![CDATA[" + arg0 + "]]>";
    }

    @Override
    public String unmarshal(String arg0) throws Exception {
        return arg0;
    }

}