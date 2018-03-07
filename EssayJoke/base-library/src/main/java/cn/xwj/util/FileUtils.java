package cn.xwj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Author: xw
 * Date: 2018-03-07 14:06:49
 * Description: FileUtils <this is description>.
 */

public class FileUtils {

    public static void copyFiles(File srcFile, File destFile) throws IOException {
        FileChannel srcChannel = null;
        FileChannel destChannel = null;

        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }

            srcChannel = new FileInputStream(srcFile).getChannel();
            destChannel = new FileOutputStream(destFile).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
        } finally {
            if (destChannel != null) {
                destChannel.close();
            }
            if (srcChannel != null) {
                srcChannel.close();
            }
        }
    }
}
