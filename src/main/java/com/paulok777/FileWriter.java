package com.paulok777;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static void writeToOutputFile(byte[] resultData, File output) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output))) {
            bos.write(resultData);
        }
    }

}
