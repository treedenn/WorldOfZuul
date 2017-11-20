package BLL.ACQ;

import BLL.ACQ.Domain;

public interface UserInterface {
	void injectDomain(Domain domain);
	void startApplication();
}