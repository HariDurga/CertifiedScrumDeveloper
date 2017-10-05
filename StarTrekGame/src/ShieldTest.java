import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Shield.Shield;

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
		shield.setEnergy(10);
		//When
		shield.addEnergy(5);
		//Then
		Assert.assertEquals(15, shield.getEnergy());
	}
	@Test
	public void energyCannotExceedMaxEnergy() {
		//Given
		shield.setEnergy(9700);
		//When
		shield.addEnergy(500);
		//Then
		Assert.assertEquals(Shield.maxEnergy, shield.getEnergy());
	}
	@Test
	public void cannotAddNegativeEnergy() {
		//Given
		shield.setEnergy(600);
		//When
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Cannot add Negative Energy");
		shield.addEnergy(-20);	
	}
	@Test
	public void getShieldAvailableEnergyCapacity() {
		//Given
		shield.setEnergy(9700);
		//Then
		Assert.assertEquals(300, shield.getAvailableCapacity());
	}
	@Test
	public void correctAmountDamageSustained(){
		//Given
		shield.setEnergy(100);
		//When
		shield.takeHit(10);
		//Then
		Assert.assertEquals(90, shield.getEnergy());
	}
	@Test
	public void energyCannotGoBelowMinimumEnergy() {
		//Given
		shield.setEnergy(100);
		//When
		shield.takeHit(200);
		//Then
		Assert.assertEquals(0, shield.getEnergy());
	}
	@Rule
	public ExpectedException thrown= ExpectedException.none();

	@Test
	public void cannotTakeNegativeHit() {
		//Given
		shield.setEnergy(100);
		//Then
		thrown.expect(ArithmeticException.class);
		thrown.expectMessage("damageAmount cannot be negative");
		shield.takeHit(-200);	
	}
	@Test
	public void cannotSetEnergyBelowMinimum() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Energy cannot be negative");
		shield.setEnergy(-10);	
	}
	@Test
	public void cannotSetEnergyAboveMaximum() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Energy cannot exceed "+Shield.maxEnergy);
		shield.setEnergy(11000);
	}

}
