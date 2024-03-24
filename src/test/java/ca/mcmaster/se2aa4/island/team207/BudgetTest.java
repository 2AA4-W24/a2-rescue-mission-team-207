package ca.mcmaster.se2aa4.island.team207.Power;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BudgetTest {

    @Test
    public void testGetDifference() {
        int battery = 100; 
        int totalCost = 30; 

        int expectedDifference = battery - totalCost;

        Budget budget = new Budget();
        int difference = budget.getDifference(battery, totalCost);

        assertEquals(expectedDifference, difference);
    }
}

