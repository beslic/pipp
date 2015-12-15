package hr.fer.pipp.sza.webapp.dao;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -6869440535293508313L;

	public DAOException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}
	
	public DAOException(String poruka) {
		super(poruka);
	}

}
