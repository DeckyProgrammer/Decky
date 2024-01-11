import org.tensorflow.lite.Interpreter
import java.io.File

class interpreter {
    var model = File("app/src/main/assets/model2.tflite") //remplacer par le bon modèle
    var myInterpreter: Interpreter = Interpreter(model)
    var inputs: MutableMap<String, Any> = HashMap()
    var input1 = 0
    var outputs: MutableMap<String, Any> = HashMap()
    var output1 = 0
    fun launch() {
        inputs["input_1"] = input1 // Autant que le nombre d'inputs
        outputs["output_1"] = output1 // Autant que le nombre d'outputs
        myInterpreter.run(inputs, outputs)
        myInterpreter.close()
    }
}