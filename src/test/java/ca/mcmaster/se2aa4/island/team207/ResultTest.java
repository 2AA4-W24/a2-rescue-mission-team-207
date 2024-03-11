package ca.mcmaster.se2aa4.island.team207;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    public void testPrintResult() {
        Result result = new Result();
        String jsonResponse = "{\"cost\": 10, \"status\": \"OK\", \"extras\": {\"extraInfo\": \"test\"}}";

        JSONObject extraInfo = result.printResult(jsonResponse);
        assertNotNull(extraInfo);
        assertTrue(extraInfo.has("extraInfo"));
        assertEquals("test", extraInfo.getString("extraInfo"));
    }

    @Test
    public void testEchoResultGroundFound() {
        Result result = new Result();
        JSONObject extraInfo = new JSONObject("{\"found\": \"GROUND\", \"range\": 5}");

        int range = result.echo_result(extraInfo);
        assertEquals(5, range);
    }

    @Test
    public void testEchoResultGroundNotFound() {
        Result result = new Result();
        JSONObject extraInfo = new JSONObject("{\"found\": \"WATER\", \"range\": 10}");

        int range = result.echo_result(extraInfo);
        assertEquals(200, range);
    }
}