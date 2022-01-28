package com.cocos.base.kotlinutils

import android.content.Context
import android.text.TextUtils
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * Date: 2019-07-30
 * Time: 16:03
 * author:wanyu
 *
 */
class FileUtils {

    companion object {
        private val instance = FileUtils()
        @JvmStatic
        fun getInstance(): FileUtils {
            return instance
        }
    }

    /**
     * 文件是否存在
     *
     * @param filePath 文件路径
     * @return 文件是否存在
     */
    fun isFileExist(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists()
    }


    /**
     * 在某路径下创建目录
     *
     * @param filePath 文件路径
     * @return 文件
     */
    fun createDirs(filePath: String): File {
        val dir = File(filePath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    /**
     * 在某路径下创建文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    fun createNewFile(filePath: String): File {
        val dir = File(filePath)
        if (!dir.exists()) {
            dir.createNewFile()
        }
        return dir
    }

    /**
     * 创建文件夹
     *
     * @param filePath 文件夹名称
     * @return 是否创建成功
     */
    fun createFileFolder(filePath: String): Boolean {
        val isSuccess: Boolean
        if (TextUtils.isEmpty(filePath)) {
            isSuccess = false
        } else {
            val file = File(filePath)
            isSuccess = file.exists() || file.mkdirs()
        }
        return isSuccess
    }


    /**
     * 文件复制 新文件覆盖老文件
     *
     * @param oldFile 老文件
     * @param newFile 新文件
     */
    fun copyFile(oldPath: String, newPathFile: String) {
        try {
            val oldFile = File(oldPath)
            if (TextUtils.isEmpty(oldPath) || !oldFile.exists()) {
                return
            }
            //要拷贝的文件存在，删除原来的
            val newFile = File(newPathFile)
            if (!newFile.exists()) {
                newFile.createNewFile()
            }
            //创建父级目录
            val parentFile = newFile.getParentFile()
            if (!parentFile.exists()) {
                parentFile.mkdirs()
            }
            newFile.copyTo(oldFile, true, DEFAULT_BUFFER_SIZE)
//            deleteFile(oldFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


//    fun File.readBytes(): ByteArray = FileInputStream(this).use { input ->
//        var offset = 0
//        var remaining = this.length().let {
//            if (it > Int.MAX_VALUE) throw OutOfMemoryError("File $this is too big ($it bytes) to fit in memory.") else it
//        }.toInt()
//        val result = ByteArray(remaining)
//        while (remaining > 0) {
//            val read = input.read(result, offset, remaining)
//            if (read < 0) break
//            remaining -= read
//            offset += read
//        }
//        if (remaining == 0) result else result.copyOf(offset)
//    }


    /**
     * 拷贝Assets目录下的文件
     *
     * @param copyFileName Assets目录下的文件名称
     * @param outFileName  输出目录的文件名
     */
    fun copyAssetsFile(context: Context, copyFileName: String, outFileName: String): Boolean {
        var isSuccess: Boolean
        val outFile = File(outFileName)
        if (outFile.exists()) {//文件存在，不需要拷贝
            isSuccess = true
        } else {
            var outputStream: OutputStream= outFile.outputStream()
            var inputStream: InputStream? = null
            try {
                inputStream = context.assets.open(copyFileName)
                val buffer = ByteArray(1024)
                var len = inputStream.read(buffer)
                while (len != -1) {
                    outputStream.write(buffer, 0, len)
                    len = inputStream.read(buffer)
                }
                outputStream.flush()

            } catch (exception: IOException) {
                exception.printStackTrace()
            } finally {
                try {
                    outputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                try {
                    inputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            isSuccess = true
        }
        if (copyFileName.endsWith(".zip")) {//解压文件
            isSuccess = unzipFile(outFileName.substring(0, outFileName.lastIndexOf("/")), copyFileName)
        }
        return isSuccess
    }


    /**
     * 解压文件
     *
     * @param filePath 解压的文件夹名
     * @param fileName 解压的文件名
     */
    private fun unzipFile(filePath: String, fileName: String): Boolean {
        var isScucess = true
        val zipFileName = filePath + File.separator + fileName
        var file = File(filePath)
        if (!file.exists()) {
            file.mkdirs()
        }
        var inputStream: FileInputStream? = null
        var zipInputStream: ZipInputStream? = null
        var fileOutputStream: FileOutputStream? = null
        try {
            inputStream = FileInputStream(zipFileName)
            zipInputStream = ZipInputStream(inputStream)
            var zipEntry: ZipEntry? = zipInputStream.nextEntry
            val buffer = ByteArray(1024 * 2)
            var count = inputStream.read(buffer)
            while (zipEntry != null) {
                if (zipEntry.isDirectory) {
                    file = File(filePath + File.separator + zipEntry.name)
                    file.mkdir()
                } else {
                    file = File(filePath + File.separator + zipEntry.name)
                    file.createNewFile()
                    fileOutputStream = FileOutputStream(file)
                    while (count != -1) {
                        fileOutputStream.write(buffer, 0, count)
                        count = zipInputStream.read(buffer)
                    }
                    closeCloseable(fileOutputStream)
                }
                zipEntry = zipInputStream.nextEntry
            }
        } catch (e: IOException) {
            e.printStackTrace()
            isScucess = false
        } finally {
            closeCloseable(zipInputStream)
            closeCloseable(inputStream)
            closeCloseable(fileOutputStream)
        }

        val zip = File(zipFileName)
        val zipName = zip.name
        val suffix = fileName.substring(zipName.lastIndexOf(".") + 1)
        if (zip.exists() && suffix == "zip") {
            zip.delete()
        }

        return isScucess
    }

    /**
     * 拷贝文件 只支持单级目录拷贝文件
     *
     * @param originFilePath 已经存在的文件路径
     * @param newFilePath    将要拷贝的文件路径
     * @return 是否拷贝完成
     */
    fun copySingleStageFileDirectory(originFilePath: String, newFilePath: String): Boolean {
        val originFile = File(originFilePath)
        if (originFile.exists()) {
            val newFile = File(newFilePath)
            if (originFile.isDirectory) {//判断是否是文件夹
                //首先判断要拷贝的文件目录是否存在
                if (!newFile.exists()) {
                    newFile.mkdirs()
                }
                val files = originFile.listFiles()
                for (file in files) {
                    //文件直接拷贝
                    var outputStream: OutputStream? = null
                    var inputStream: InputStream? = null
                    try {
                        outputStream = FileOutputStream(File(newFilePath + File.separator + file.name))
                        inputStream = FileInputStream(file)
                        val buffer = ByteArray(1024)
                        var len = inputStream.read(buffer)
                        while (len != -1) {
                            outputStream.write(buffer, 0, len)
                            len = inputStream.read(buffer)
                        }
                        outputStream.flush()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        try {
                            outputStream?.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        try {
                            inputStream?.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }


                }
            } else {
                //文件直接拷贝
                var outputStream: OutputStream? = null
                var inputStream: InputStream? = null
                try {
                    outputStream = FileOutputStream(newFilePath)
                    inputStream = FileInputStream(originFile)
                    val buffer = ByteArray(1024)
                    var len = inputStream.read(buffer)
                    while (len != -1) {
                        outputStream.write(buffer, 0, len)
                        len = inputStream.read(buffer)
                    }
                    outputStream.flush()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    try {
                        outputStream?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    try {
                        inputStream?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        } else {
            return false
        }
        //拷贝完成删除之前的文件
        deleteFile(originFile)
        return true
    }


    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    fun deleteFile(file: File) {
        if (file.isFile) {
            file.delete()
            return
        }
        if (file.isDirectory) {
            val childFile = file.listFiles()
            if (childFile == null || childFile.size == 0) {
                file.delete()
                return
            }
            for (f in childFile) {
                deleteFile(f)
            }
            file.delete()
        }
    }


    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath
     * 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
     lateinit var file: File
     var flag: Boolean = false
    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    fun deleteFile(sPath: String): Boolean {
        flag = false
        file = File(sPath)
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete()
            flag = true
        }
        return flag
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    fun deleteDirectory(sPath: String): Boolean {
        var sPath = sPath
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator
        }
        val dirFile = File(sPath)
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory) {
            return false
        }
        flag = true
        // 删除文件夹下的所有文件(包括子目录)
        val files = dirFile.listFiles()
        if (files != null && files.size > 0) {
            for (i in files.indices) {
                // 删除子文件
                if (files[i].isFile) {
                    flag = deleteFile(files[i].absolutePath)
                    if (!flag)
                        break
                } // 删除子目录
                else {
                    flag = deleteDirectory(files[i].absolutePath)
                    if (!flag)
                        break
                }
            }
        }
        if (!flag)
            return false
        // 删除当前目录
        return if (dirFile.delete()) {
            true
        } else {
            false
        }
    }

    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小 Kb
     */
    fun getFileSpaceSize(filePath: String): Int {
        var size: Long = 0
        try {
            val downloadFile = File(filePath)
            if (downloadFile.exists()) {
                if (downloadFile.isDirectory) {
                    size = getFileSizes(downloadFile)
                } else {
                    size = getFileSize(downloadFile)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return (size / 1048576).toInt()
    }


    /**
     * 获取指定文件夹
     *
     * @param f 文件
     * @return 文件大小
     */
    private fun getFileSizes(f: File): Long {
        var size: Long = 0
        val fileList = f.listFiles()
        for (aFileList in fileList) {
            size = if (aFileList.isDirectory) {
                size + getFileSizes(aFileList)
            } else {
                size + getFileSize(aFileList)
            }
        }
        return size
    }

    /**
     * 获取指定文件大小
     *
     * @return
     * @throws IOException 异常
     */
    private fun getFileSize(file: File): Long {
        var size: Long = 0
        if (file.exists()) {
            var fis: FileInputStream? = null
            try {
                fis = FileInputStream(file)
                size = fis.available().toLong()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                closeCloseable(fis)
            }
        } else {
            file.createNewFile()
        }
        return size
    }


    /**
     * 计算文件或者文件夹的大小 ，单位 MB
     *
     * @param file 要计算的文件或者文件夹 ， 类型：java.io.File
     * @return 大小，单位：MB
     */
    fun getFilesSizeToDouble(file: File): Double {
        // 判断文件是否存在
        return if (file.exists()) {
            // 如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小
            if (!file.isFile) {
                // 获取文件大小
                val fl = file.listFiles()
                var ss = 0.0
                for (f in fl)
                    ss += getFilesSizeToDouble(f)
                ss
            } else {
                file.length().toDouble() / 1024.0 / 1024.0
            }
        } else {
            0.0
        }
    }

    /**
     * 关系资源，例如：数据库，cursor，io流，
     *
     * @param closeable 实现了Closeable接口的实例
     */
    fun closeCloseable(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}