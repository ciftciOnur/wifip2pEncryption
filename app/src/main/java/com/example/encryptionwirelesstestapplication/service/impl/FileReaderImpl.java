package com.example.encryptionwirelesstestapplication.service.impl;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;

import com.example.encryptionwirelesstestapplication.R;
import com.example.encryptionwirelesstestapplication.service.FileReader;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReaderImpl implements FileReader {

    @Override
    public String fileToString(Context ctx) {
        String result;
        try {
            Resources res = ctx.getResources();
            InputStream in_s = res.openRawResource(R.raw.source);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            result = new String(b);
        } catch (Exception e) {
            // e.printStackTrace();
            result = "Error: can't show help.";
        }
        return result;
    }
}
