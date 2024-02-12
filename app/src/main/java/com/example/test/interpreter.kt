import android.content.Context
import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class interpreter(context: Context) {
    //var model = File("app/src/main/assets/model2.tflite") //remplacer par le bon modèle
    var assetFiles: AssetManager = context.getAssets()
    var model : InputStream = assetFiles.open("model2.tflite")
    var inputs: MutableMap<String, Any> = HashMap()
    var input1 = 0
    var outputs: MutableMap<String, Any> = HashMap()
    var output1 = 0
    fun launch() {
        //inputs["input_1"] = input1 // Autant que le nombre d'inputs
        outputs["output_1"] = Array(1) { FloatArray(53){0f} }// Autant que le nombre d'outputs
        //var file = convertInputStreamToFile(model,"app/src/main/assets/model2")
        var modelByte : ByteBuffer = ModelByteBuffer(model)
        var myInterpreter = Interpreter(modelByte)
        myInterpreter.run(inputs["input_1"], outputs["output_1"])
        myInterpreter.close()
    }
        fun convertInputStreamToFile(inputStream: InputStream, filePath: String?): File {
            val file = File(filePath)
            try {
                val outputStream = FileOutputStream(file)
                val buffer = ByteArray(1024)
                var length: Int
                while (inputStream.read(buffer).also { length = it } > 0) {
                    outputStream.write(buffer, 0, length)
                }
                outputStream.close()
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return file
        }

    @Throws(IOException::class)
    fun ModelByteBuffer(inputStream: InputStream): ByteBuffer {
        val buffer = ByteArray(8192)
        var bytesRead: Int
        val output = ByteArrayOutputStream()
        try {
            while (inputStream?.read(buffer).also { bytesRead = it?:0 } != -1) {
                output.write(buffer, 0, bytesRead)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val file = output.toByteArray()
        val bb : ByteBuffer = ByteBuffer.allocateDirect(file.size)
        bb.order(ByteOrder.nativeOrder())
        bb.put(file)
        return bb
    }

    fun loadOnLineModelFile(): MappedByteBuffer{
        val file: File = File("app/src/main/assets/model2.tflite")
        val `is` = FileInputStream(file)

        return `is`.channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length())
    }

}