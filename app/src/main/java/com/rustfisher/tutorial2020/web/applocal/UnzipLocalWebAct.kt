package com.rustfisher.tutorial2020.web.applocal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rustfisher.tutorial2020.R
import com.rustfisher.tutorial2020.web.WebViewLoadAssetsAct
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream

/**
 * app本地存储里的网页
 */
class UnzipLocalWebAct : AppCompatActivity() {

    companion object {
        const val TAG = "rustAppUnzipAct"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_app_local_web)

        val targetDir = applicationContext.filesDir.absolutePath + File.separator + "cocos-web1"

        val startBtn = findViewById<Button>(R.id.start_btn)
        startBtn.setOnClickListener {
            unzipAssetsFile("ccc2.zip", targetDir)
            Toast.makeText(applicationContext, "解压完成", Toast.LENGTH_SHORT).show()
        }

        val delBtn = findViewById<Button>(R.id.del_btn)
        delBtn.setOnClickListener {
            val dir = File(targetDir)
            if (dir.exists()) {
                val res = dir.deleteRecursively()
                Log.d(TAG, "删除目标目录 $res")
            } else {
                Log.d(TAG, "目标目录不存在 $dir")
            }
        }

        val showWebBtn = findViewById<Button>(R.id.show_web_btn)
        showWebBtn.setOnClickListener {
            val url = "file://$targetDir/web-mobile/index.html" // 加载app存储的网页需要 file:// 开头
            Log.d(TAG, "url: $url")
            val intent = Intent(applicationContext, WebViewLoadAssetsAct::class.java)
            intent.putExtra(WebViewLoadAssetsAct.K_URL, url)
            startActivity(intent)
        }
    }

    /**
     * 解压assets里指定的某个zip
     */
    private fun unzipAssetsFile(assetsZipName: String, targetLocation: String) {
        Log.d(TAG, "[unzipAssetsFile] targetLocation: $targetLocation")
        val targetDir = File(targetLocation)
        if (!targetDir.exists()) {
            targetDir.mkdirs()
        }
        val tempFile = File(targetLocation, "tmp-$assetsZipName.zip")
        try {
            val inputStream = assets.open(assetsZipName)
            if (tempFile.exists()) {
                tempFile.delete()
            }
            tempFile.createNewFile()
            val copyOs: OutputStream = FileOutputStream(tempFile)
            val tmp = ByteArray(1024)
            var len: Int
            while (((inputStream.read(tmp)).also { len = it }) != -1) {
                copyOs.write(tmp, 0, len)
            }
            copyOs.flush()
            copyOs.close()
            inputStream.close()
            Log.d(TAG, "临时文件复制完毕")
        } catch (e: Exception) {
            Log.e(TAG, "unzipAssetsFile: ", e)
            return
        }

        val zipInputStream = ZipInputStream(FileInputStream(tempFile))
        val zipFile = ZipFile(tempFile)
        var entry: ZipEntry?

        while (zipInputStream.nextEntry.also { entry = it } != null) {
            val outFile = File(targetLocation, entry!!.name)
            Log.d(TAG, "当前文件: $entry -> $outFile")
            if (outFile.parentFile != null && !outFile.parentFile!!.exists()) {
                outFile.parentFile!!.mkdir()
            }

            if (!outFile.exists()) {
                if (entry!!.isDirectory) {
                    outFile.mkdirs()
                    continue
                } else {
                    outFile.createNewFile()
                }
            }

            val bis = BufferedInputStream(zipFile.getInputStream(entry))
            val bos = BufferedOutputStream(FileOutputStream(outFile))
            val entryTmpArr = ByteArray(1024)
            while (true) {
                val readLen = bis.read(entryTmpArr)
                if (readLen == -1) {
                    break
                }
                bos.write(entryTmpArr, 0, readLen)
            }
            bos.close()
            bis.close()
            Log.d(TAG, "解压得到文件 $outFile")
        }
        val delTmp = tempFile.delete()
        Log.d(TAG, "解压完毕 删除临时文件$delTmp")
    }

}