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
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;
import kodkod.engine.config.Options;

import edu.mit.csail.sdg.ast.Sig;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.XML;


public class AlloyRunner {
    /**
     * Basic class that wraps alloy operations for usage by the rest of the service.
     */


    //temp implementation of class for testing
    private A4Options options = new A4Options();


    /**
     * Class to hold the result of an Alloy execution.
     */
    public static class AlloyResult {
        JSONObject jsonResult;
        String XMLResult;
        public AlloyResult(A4Solution ans) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            // System.out.println(ans.debugExtractKInstance().toString());
            ans.writeXML(printWriter, new ArrayList<Func>(), null);
            this.jsonResult = XML.toJSONObject(stringWriter.toString());
            this.XMLResult = stringWriter.toString();
        }

        public JSONObject getJsonResult() {
            return jsonResult;
        }

        public String getXMLResult() {
            return XMLResult;
        }
    }

    /**
     * Constructor for AlloyRunner that initializes the options for the Alloy execution.
     */
    public AlloyRunner() {
        options.inferPartialInstance = true;
        options.symmetry = 20;
        options.noOverflow = true;

        // options.solver = A4Options.SatSolver.SAT4j;
        // options.skolemDepth = 3;
        // options.noOverflow = false;
    }

    /**
     * Runs the given Alloy specification and returns the result.
     * @param alloySpec The Alloy specification to run.
     * @return An AlloyResult containing the result of the execution.
     */
    public AlloyResult runAlloySpec(String alloySpec){
        System.out.print(alloySpec);
        CompModule world = CompUtil.parseEverything_fromString(A4Reporter.NOP, alloySpec);
        // System.out.println("All Commands" + world.getAllCommands().toString());
        Command cmd = world.getAllCommands().get(0);

        A4Solution ans = TranslateAlloyToKodkod.execute_command(A4Reporter.NOP, world.getAllSigs(), cmd, options); 
        AlloyResult result = new AlloyResult(ans);
        return result;
    }
    
   
    
}
