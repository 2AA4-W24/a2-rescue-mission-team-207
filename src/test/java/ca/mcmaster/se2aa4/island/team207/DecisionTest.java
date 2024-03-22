// package ca.mcmaster.se2aa4.island.team207;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import org.json.JSONObject;


// public class DecisionTest {

//     @Test
//     public void testDecisionControl() {
        
//         Decision decision = new Decision();

//         // Test decision making when ground is not found
//         String decisionString = decision.decisionControlEast();
//         assertNotNull(decisionString);
//         assertTrue(decisionString.contains("action"));

//         // Test decision making after ground is found
//         decision.useEchoResults(new JSONObject("{\"result\": 0}")); 
//         decisionString = decision.decisionControlEast();
//         assertNotNull(decisionString);
//         assertTrue(decisionString.contains("action"));
//     }
    
// }
