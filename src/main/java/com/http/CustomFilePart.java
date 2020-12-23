package com.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.util.EncodingUtil;


 
public class CustomFilePart extends FilePart

{

    public CustomFilePart(String name, File file) throws FileNotFoundException

    {

        	super(name, file, null, "utf-8");

    }

    protected void sendDispositionHeader(OutputStream out)

        throws IOException {

        String filename = getSource().getFileName();   

        out.write(CONTENT_DISPOSITION_BYTES);

        out.write(QUOTE_BYTES);

        out.write(EncodingUtil.getBytes(getName(),"utf-8"));

        out.write(QUOTE_BYTES);

        if (filename != null) {   

            out.write(EncodingUtil.getAsciiBytes(FILE_NAME));   

            out.write(QUOTE_BYTES);   

            out.write(EncodingUtil.getBytes(filename, "utf-8"));   

            out.write(QUOTE_BYTES);   

            }   

    }

}


 