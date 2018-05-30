package cn.xwj.baselibrary.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.FileProvider
import java.io.File

/**
 * Author: xw
 * Date: 2018-05-30 15:04:13
 * Description: FileProviderHelper: .
 */
class FileProviderHelper {

    companion object {
        fun fromFileUri(context: Context, file: File): Uri {
            support23 {
                return FileProvider.getUriForFile(context,
                        "${context.packageName}.fileProvider",
                        file)
            }
            return Uri.fromFile(file)
        }


        fun setIntentTypeAndData(context: Context, intent: Intent, type: String,
                                 file: File, isWriteable: Boolean) {
            intent.setDataAndType(fromFileUri(context, file), type)
            support23 {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                if (isWriteable) intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
        }

    }
}

private inline fun support23(code: () -> Unit) {
    if (android.os.Build.VERSION.SDK_INT > 23) {
        code()
    }
}