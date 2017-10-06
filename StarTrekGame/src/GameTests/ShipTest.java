package GameTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Shield;
import Game.Ship;
import junit.framework.Assert;

public class ShipTest {

	Ship ship;
	Shield shield;
	@Before
	public void setUp() {
		shield=new Shield();
		ship=new Ship(shield);
	}
	@Test
	public void testShipEnergy() {
		Assert.assertEquals(80000, ship.Energy);
	}
	@Test
	public void transferShipEnergyToShieldEnergy() {
		//Given
		ship.Energy=200;
		shield.setEnergyTestOnly(500);
		ship.transferEnergyToShield(100);
		Assert.assertEquals(100, ship.Energy);
		Assert.assertEquals(600,shield.getEnergy());	
	}
	
	@Test
	public void NoTransferIfInsufficientShipEnergy() {
		//Given
		ship.Energy=1000;
		shield.setEnergyTestOnly(9950);
		ship.transferEnergyToShield(1100);
		Assert.assertEquals(1000, ship.Energy);
		Assert.assertEquals(9950, shield.getEnergy());
	}
	@Test
	public void shipCannotTransferExcessEnergyToShield() {
		//Given
		ship.Energy=5000;
		shield.setEnergyTestOnly(9000);
		ship.transferEnergyToShield(2000);
		Assert.assertEquals(4000, ship.Energy);
		Assert.assertEquals(Shield.maxEnergy, shield.getEnergy());
	}
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@Test
	public void transferEnergyToShieldMethodCannotTakeNegative() {
		//Given
		ship.Energy=100;
		shield.setEnergyTestOnly(300);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("energyToTransfer cannot be nagative");
		ship.transferEnergyToShield(-100);
	}
	@Test
	public void transferShieldEnergyToShip() {
		//Given
		ship.Energy=300;
		shield.setEnergyTestOnly(500);
		ship.transferEnergyFromShield(100);
		Assert.assertEquals(400, ship.Energy);
		Assert.assertEquals(400, shield.getEnergy());
	}
	@Test
	public void shieldCannotTransferExcessEnergyToShip() {
		//Given
		ship.Energy=79000;
		shield.setEnergyTestOnly(5000);
		ship.transferEnergyFromShield(2000);
		Assert.assertEquals(ship.maxEnergy, ship.Energy);
		Assert.assertEquals(4000, shield.getEnergy());
	}
	@Test
	public void shiledCannotTransferMoreEnergyItHas() {
		ship.Energy=50000;
		shield.setEnergyTestOnly(500);
		shield.raise();
		ship.transferEnergyFromShield(1000);
		Assert.assertEquals(50500, ship.Energy);
		Assert.assertEquals(0, shield.getEnergy());
		Assert.assertFalse(shield.ShieldUp);
	}
	@Test
	public void transferEnergyFromShieldMethodCannotBeNegative() {
		ship.Energy=2000;
		shield.setEnergyTestOnly(1000);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("energyToTransfer cannot be nagative");
		ship.transferEnergyFromShield(-100);
	}
	

}
