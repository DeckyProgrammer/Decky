import java.util.HashMap;
import java.util.Map;
import org.tensorflow:tensorflow-lite;
public class interpreter{

    public Interpreter interpreter(@NotNull File modelFile);

    Map<String, Object> inputs = new HashMap<>();
    int input1;
    Map<String, Object> outputs = new HashMap<>();
    int output1;

    public void launch(){
        inputs.put("input_1", input1); // Autant que le nombre d'inputs
        outputs.put("output_1", output1); // Autant que le nombre d'outputs
        interpreter.runSignature(inputs, outputs);
        interpreter.close();
    }
}