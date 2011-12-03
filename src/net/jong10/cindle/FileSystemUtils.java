package net.jong10.cindle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class FileSystemUtils {
    public static boolean createFolderIfNotExists(String path) {
        File folder = new File(path);
        if (folder.exists())
            return true;
        else
            return folder.mkdirs();
    }

    public static void copyAssetToInternalStorage(Context context, String src, String dest) throws IOException {
        InputStream is = context.getAssets().open(src);
        createFolderIfNotExists(dest);

        FileOutputStream fos = context.openFileOutput(dest, Context.MODE_PRIVATE);
        byte[] bytes = new byte[102400];

        for (int c = is.read(bytes); c != -1; c = is.read(bytes))
            fos.write(bytes, 0, c);

        fos.close();
        is.close();
    }

    public static void setExecutableFile(String file) {
        File f = new File(file);
        f.setExecutable(true);
    }

    public static String getProjectPath(Context context, String project) {
        return String.format("%s/%s", context.getExternalFilesDir(null), project);
    }
}
