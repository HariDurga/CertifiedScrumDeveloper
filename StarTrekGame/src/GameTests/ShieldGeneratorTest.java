package GameTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Game.ShieldGenerator;
import Game.Subsystem;
import junit.framework.Assert;

public class ShieldGeneratorTest {

	ShieldGenerator shieldGen;
	
	
	@Before
	public 
	void setUp()  {
		ShieldGenerator shieldGen = new ShieldGenerator();
	}

	@Test
	public void testShieldGenDefaultRepairDays() {
		Assert.assertEquals(0, shieldGen.defaultRepairDays);
	}
	
	@Test
	public void testShieldGen() {
		Assert.assertEquals(7, shieldGen.maxRepairDays);
	}
}
