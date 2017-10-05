package GameTests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Game.Subsystem;
import junit.framework.*;

public class SubSystemProperties {
	Subsystem sub;
	
	@Before
	public void setUp() {
		Subsystem sub = new Subsystem();
	}
	
	@Test
	public void testDefaultRepairDays() {
	
	Assert.assertEquals(0, sub.defaultRepairDays);
	
		
	}
	
	@Test
	public void testMaxRepairDays() {
		Assert.assertEquals(7, sub.maxRepairDays);
	}
}
