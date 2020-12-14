package interfaces;

import customExceptions.InvalidInputException;

public interface MenuInterface {
	public void showMainMenu() throws InvalidInputException;
	public void showSubMenu();
}
