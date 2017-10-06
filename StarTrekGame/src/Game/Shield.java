package Game;

public class Shield {
	private int Energy;
	public static final int maxEnergy=10000;
	public Boolean ShieldUp;
		
	public Shield(){
		ShieldUp=false;
		Energy=maxEnergy;
	}
	public int getEnergy() {
		return Energy;
	}
	public void setEnergyTestOnly(int energy) {
		if(energy<0) {
			throw new IllegalArgumentException("Energy cannot be negative");
		}
		else if(energy>maxEnergy) {
			throw new IllegalArgumentException("Energy cannot exceed "+Shield.maxEnergy);
		}
		Energy = energy;
	}

	public void raise() {
		ShieldUp=true;	
	}

	public void transferEnergy(int energyToAdd) {
	
		Energy+=energyToAdd;
		if(Energy>maxEnergy) {
			Energy=maxEnergy;
		}else if(Energy<=0) {
			Energy=0;
			drop();
		}
	}

	public int getAvailableCapacity() {
		return (maxEnergy-Energy);
	}

	public void takeHit(int damageAmount) {
		if(damageAmount<0) {
			throw new ArithmeticException("damageAmount cannot be negative");
		}
		Energy= (Energy-damageAmount);
		if(Energy<=0) {
			Energy=0;
			drop();
		}
	}
	public void drop() {
		ShieldUp=false;
	}

}