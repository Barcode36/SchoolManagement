
package gui.util.enums;

public enum ParcelStatusEnum {

	CANCELADA("#F63C41"), ATRASADA("#FFA36C"), ABERTA("#FFFFFF"), PAGA("#a2d6f9"), ACORDO("#333");

	private String hexColor;

	ParcelStatusEnum(String hexColor){
		this.hexColor = hexColor;
	}

	public String getHexColor() {
		return this.hexColor;
	}

	public static ParcelStatusEnum fromString(String status) {
		for (ParcelStatusEnum s : ParcelStatusEnum.values()) {
			if (s.toString().equalsIgnoreCase(status)) {
				return s;
			}
		}
		return null;
	}

}
