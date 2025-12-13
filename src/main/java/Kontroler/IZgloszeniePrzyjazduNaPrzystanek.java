package Kontroler;

import Model.IModel;

public abstract class IZgloszeniePrzyjazduNaPrzystanek {
	protected IModel model;
	protected String identyfikatorKursu;
	public IModel _model;

	public void ZgloszeniePrzyjazduNaPrzystanek(String identyfikatorKursu) {
		throw new UnsupportedOperationException();
	}

	public void operation() {
		throw new UnsupportedOperationException();
	}
}