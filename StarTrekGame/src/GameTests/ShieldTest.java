package GameTests;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Game.Shield;

public class ShieldTest {

	Shield shield;
	
	@Before
	public void setUp() {
		shield= new Shield();
	}	
	@Test
	public void isShieldUp() {
		shield.raise();
		Assert.assertTrue(shield.ShieldUp);
	}
	
	@Test
	public void isShieldDown() {
		shield.raise();
		shield.drop();
		Assert.assertFalse(shield.ShieldUp);
	}
	@Test
	public void isShieldDownByDefault() {
		Assert.assertFalse(shield.ShieldUp);
	}
	@Test
	public void isStartingEnergyEqualsMaxEnergy() {
		Assert.assertEquals(Shield.maxEnergy, shield.getEnergy());
	}
	@Test
	public void isMaxEnergyContant10000() {
		Assert.assertEquals(10000,Shield.maxEnergy);
	}
	@Test
	public void correctAmountOfEnergyAdded() {
		//Given
		shield.setEnergyTestOnly(10);
		//When
		shield.transferEnergy(5);
		//Then
		Assert.assertEquals(15, shield.getEnergy());
	}
	@Test
	public void energyCannotExceedMaxEnergy() {
		//Given
		shield.setEnergyTestOnly(9700);
		//When
		shield.transferEnergy(500);
		//Then
		Assert.assertEquals(Shield.maxEnergy, shield.getEnergy());
	}
	@Test
	public void transferCannotCauseNegativeEnergy() {
		//Given
		shield.setEnergyTestOnly(100);
		shield.raise();
		//When
		shield.transferEnergy(-200);
		Assert.assertEquals(0, shield.getEnergy());
		Assert.assertFalse(shield.ShieldUp);
	}
	@Test
	public void getShieldAvailableEnergyCapacity() {
		//Given
		shield.setEnergyTestOnly(9700);
		//Then
		Assert.assertEquals(300, shield.getAvailableCapacity());
	}
	@Test
	public void correctAmountDamageSustained(){
		//Given
		shield.setEnergyTestOnly(100);
		//When
		shield.takeHit(10);
		//Then
		Assert.assertEquals(90, shield.getEnergy());
	}
	@Test
	public void energyCannotGoBelowMinimumEnergy() {
		//Given
		shield.setEnergyTestOnly(100);
		shield.raise();
		//When
		shield.takeHit(200);
		//Then
		Assert.assertEquals(0, shield.getEnergy());
		Assert.assertFalse(shield.ShieldUp);
	}
	@Rule
	public ExpectedException thrown= ExpectedException.none();

	@Test
	public void cannotTakeNegativeHit() {
		//Given
		shield.setEnergyTestOnly(100);
		//Then
		thrown.expect(ArithmeticException.class);
		thrown.expectMessage("damageAmount cannot be negative");
		shield.takeHit(-200);	
	}
	@Test
	public void cannotSetEnergyBelowMinimum() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Energy cannot be negative");
		shield.setEnergyTestOnly(-10);	
	}
	@Test
	public void cannotSetEnergyAboveMaximum() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Energy cannot exceed "+Shield.maxEnergy);
		shield.setEnergyTestOnly(11000);
	}

}
