package GameTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Game.Engines;
import Game.Subsystem;
import junit.framework.Assert;

public class EnginesTest {

	Engines engine;
	
	
	@Before
	public 
	void setUp()  {
		Engines engine = new Engines();
	}

	@Test
	public void testShieldGenDefaultRepairDays() {
		Assert.assertEquals(0, engine.defaultRepairDays);
	}
	@Test
	public void testShieldGenMaxRepairDays() {
		Assert.assertEquals(7, engine.maxRepairDays);
	}
}