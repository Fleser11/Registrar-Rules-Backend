package org.formalmethods.registrarproject.alloy;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.ast.VisitQuery;
import edu.mit.csail.sdg.ast.VisitReturn;
import edu.mit.csail.sdg.parser.CompModule;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;
import kodkod.engine.config.Options;


public class AlloyRunner {
    /**
     * Basic class that wraps alloy operations for usage by the rest of the service.
     */


    //temp implementation of class for testing
    private A4Options options = new A4Options();

    public static class AlloyResult {
        String result;
        public AlloyResult(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }

    public AlloyRunner() {
        options.inferPartialInstance = true;
        options.symmetry = 5;
    }

    public AlloyResult runAlloySpec(String alloySpec){
        CompModule world = CompUtil.parseEverything_fromString(A4Reporter.NOP, alloySpec);
        Command cmd = world.getAllCommands().get(0);

        A4Solution ans = TranslateAlloyToKodkod.execute_command(A4Reporter.NOP, world.getAllSigs(), cmd, null); 
        AlloyResult result = new AlloyResult(ans.toString());
        return result;
    }
    
    /**
     * public X translateResult(A4Solution ans) {
     * }
     */
}
