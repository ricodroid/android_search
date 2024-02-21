import android.content.Context
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
                val buffer = ByteArray(1024)
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

    // 動画再生
    fun getVideoFilePath(): String {
        // 動画ファイルのパスを返す関数。保存先のディレクトリやファイル名に注意して実装する
        return context.filesDir.absolutePath + "/video.mp4"
    }
}