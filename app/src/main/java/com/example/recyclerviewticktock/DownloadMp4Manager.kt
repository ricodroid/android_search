import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.net.URL

/**
 * mp4をダウンロードする
 */
class DownloadMp4Manager(private val context: Context) {

    fun downloadVideo(url: String, fileName: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        Thread {
            try {
                val connection = URL(url).openConnection()
                val inputStream = connection.getInputStream()

                val outputStream = FileOutputStream(File(context.filesDir, fileName))
                val buffer = ByteArray(1024)// ここでダウンロードの速度調整4096、2048、1024
                var bytesRead: Int

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }

                outputStream.close()
                inputStream.close()

                onSuccess()
            } catch (e: Exception) {
                onFailure(e)
            }
        }.start()
    }

    // ダウンロードディレクトリ取得
    fun getVideoFilePath(): String {
        return context.filesDir.absolutePath + "/"
    }
}