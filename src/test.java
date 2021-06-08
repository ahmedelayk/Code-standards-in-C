import java.io.FileInputStream;
import java.io.FileReader;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class test {
    public static  void main(String[] args) throws Exception
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        JSONObject declare;
        JSONObject declareObject = new JSONObject();
        JSONObject globalObject = new JSONObject();
        JSONObject localObject = new JSONObject();

        //Read JSON file
        FileReader reader = new FileReader("regex.json");
        Object obj = jsonParser.parse(reader);
        declare = (JSONObject) obj;
        declareObject = (JSONObject) declare.get("declare");
        globalObject = (JSONObject) declare.get("global");
        localObject = (JSONObject) declare.get("local");


        String inputFile = "t.c";
        FileInputStream is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        CLexer lexer = new CLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);
        ParseTree tree = parser.compilationUnit();
        C_rules r = new C_rules(declareObject,globalObject,localObject,inputFile);
        r.visit(tree);

    }
}
