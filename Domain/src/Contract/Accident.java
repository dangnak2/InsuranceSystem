package Contract;


public class Accident {

	public enum Causes {
		ePolicyholder,
		eAccident,
		eNaturalDisaster,
	};
	public enum OccurringArea {
		eHouse,
		eApartment,
		eEfficiencyApartment,
	};
	public enum Originator {
		eFireOrExplosion,
		eKinIntentionalOrEmployerIntentional,
		eElectricalAppliancesOrElectricalAccident,
		eDeliberateAccident,
		eGrossNegligence,
		eSlightMistake,
	};

	private int brokenCondition;
	private int humanDamage;
	private int surroundingDamage;
	private Causes causes;
	private OccurringArea occurringArea;
	private Originator originator;

	public Accident(){

	}


	public int getBrokenCondition() {
		return brokenCondition;
	}

	public void setBrokenCondition(int brokenCondition) {
		this.brokenCondition = brokenCondition;
	}

	public int getHumanDamage() {
		return humanDamage;
	}

	public void setHumanDamage(int humanDamage) {
		this.humanDamage = humanDamage;
	}

	public int getSurroundingDamage() {
		return surroundingDamage;
	}

	public void setSurroundingDamage(int surroundingDamage) {
		this.surroundingDamage = surroundingDamage;
	}

	public Causes getCauses() {
		return causes;
	}

	public void setCauses(int causesIndex) {
		for (Causes causes : Causes.values()) {
			if (causesIndex == causes.ordinal()) {
				this.causes = causes;
			}
		}

	}

	public OccurringArea getOccurringArea() {
		return occurringArea;
	}

	public void setOccurringArea(int occurringAreaIndex) {

		for (OccurringArea occurringArea : OccurringArea.values()) {
			if (occurringAreaIndex == occurringArea.ordinal()) {
				this.occurringArea = occurringArea;
			}
		}
	}

	public Originator getOriginator() {
		return originator;
	}

	public void setOriginator(int originatorIndex) {

		for (Originator originator : Originator.values()) {
			if (originatorIndex == causes.ordinal()) {
				this.originator = originator;
			}
		}
	}
}
