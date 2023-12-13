import java.util.HashMap;
import java.util.Map;
import org.tensorflow:tensorflow-lite;
import java.io.File;
public class interpreter{


    File model = new File("app/src/main/assets/model2.tflite"); //remplacer par le bon modèle
    public Interpreter myInterpreter = new Interpreter(model);

    Map<String, Object> inputs = new HashMap<>();
    int input1;
    Map<String, Object> outputs = new HashMap<>();
    int output1;

    public void launch(){
        inputs.put("input_1", input1); // Autant que le nombre d'inputs
        outputs.put("output_1", output1); // Autant que le nombre d'outputs
        myInterpreter.run(inputs, outputs);
        myInterpreter.close();
    }
}