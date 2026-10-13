package org.formalmethods.registrarproject.alloy;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Func;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.ast.VisitQuery;
import edu.mit.csail.sdg.ast.VisitReturn;
import edu.mit.csail.sdg.parser.CompModule;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import kodkod.engine.satlab.SATFactory;

import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;
import edu.mit.csail.sdg.ast.Expr;
import kodkod.engine.config.Options;

import edu.mit.csail.sdg.ast.Sig;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.XML;

import java.io.FileWriter;
import java.io.IOException;



public class AlloyRunner {
    /**
     * Basic class that wraps alloy operations for usage by the rest of the service.
     */


    //temp implementation of class for testing
    private A4Options options = new A4Options();

    public static interface AlloyResult{
    }

    /**
     * Class to hold the result of an Alloy execution.
     */
    public static class AlloySuccessfulResult implements AlloyResult{
        JSONObject jsonResult;
        String XMLResult;
        A4Solution ans;
        CompModule world;
        public AlloySuccessfulResult(
            A4Solution ans,
            CompModule world
        ) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            // System.out.println(ans.debugExtractKInstance().toString());
            ans.writeXML(printWriter, new ArrayList<Func>(), null);
            this.jsonResult = XML.toJSONObject(stringWriter.toString());
            this.XMLResult = stringWriter.toString();
            this.ans = ans;
            this.world = world;
        }

        public JSONObject getJsonResult() {
            return jsonResult;
        }

        public String getXMLResult() {
            return XMLResult;
        }

        public String eval(String expression) {
            Expr exr = CompUtil.parseOneExpression_fromString(world, expression);
            return ans.eval(exr).toString();
        }
    }

    public static class AlloyFailedResult implements AlloyRunner.AlloyResult{
    }

    /**
     * Constructor for AlloyRunner that initializes the options for the Alloy execution.
     */
    public AlloyRunner() {
        options.inferPartialInstance = true;
        options.symmetry = 20;
        options.noOverflow = true;
        options.solver = SATFactory.find("glucose").orElse(SATFactory.DEFAULT);
        System.out.print(options.solver);
        // System.out.print("SOLVER" +options.solver);
        options.skolemDepth = 4;
        // options.maxseq=4;
        // options.noOverflow = false;
    }

    /**
     * Runs the given Alloy specification and returns the result.
     * @param alloySpec The Alloy specification to run.
     * @return An AlloyResult containing the result of the execution.
     */
    public AlloyResult runAlloySpec(String alloySpec){
        // System.out.print(alloySpec);

        try {
            FileWriter myWriter = new FileWriter("alloySpec.txt");
            myWriter.write(alloySpec);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CompModule world = CompUtil.parseEverything_fromString(A4Reporter.NOP, alloySpec);
        // System.out.println("All Commands" + world.getAllCommands().toString());
        Command cmd = world.getAllCommands().get(0);

        AlloyResult result;
        try{
            A4Solution ans = TranslateAlloyToKodkod.execute_command(A4Reporter.NOP, world.getAllSigs(), cmd, options); 
            result = new AlloySuccessfulResult(ans, world);

        }
        catch(Exception e){
            result = new AlloyFailedResult();
        }
        return result;
    }
    
   
    
}
