package Game;

public class Ship{

	public Shield shield;
	public int Energy;
	public static final int maxEnergy=80000;
	
	public Ship(){
		Energy=maxEnergy;
		shield=new Shield();
	}
	public Ship(Shield testShield) {
		Energy=80000;
		shield=testShield;
	}
	public void transferEnergyToShield(int energyToTransfer) {
		if(energyToTransfer<0) {
			throw new IllegalArgumentException("energyToTransfer cannot be nagative");
		}
		if(energyToTransfer<Energy) {
			if((shield.getEnergy()+energyToTransfer)>Shield.maxEnergy) {
				energyToTransfer=Shield.maxEnergy-shield.getEnergy();
			}
			
			Energy-=energyToTransfer;
			shield.transferEnergy(energyToTransfer);
		}
		
	}
	public void transferEnergyFromShield(int energyToTransfer) {
		if(energyToTransfer<0) {
			throw new IllegalArgumentException("energyToTransfer cannot be nagative");
		}
		if(energyToTransfer<shield.getEnergy()) {	
			if((Energy+energyToTransfer)>maxEnergy) {
				
				energyToTransfer=maxEnergy-Energy;
			}
		}
		else {
			energyToTransfer=shield.getEnergy();
		}
		Energy+=energyToTransfer;
		shield.transferEnergy(-energyToTransfer);	
	}
	
}
