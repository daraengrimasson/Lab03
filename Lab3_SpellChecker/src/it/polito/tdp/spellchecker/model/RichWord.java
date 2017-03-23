package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String textInput;
	private boolean corretto;
	
	public RichWord(String textInput, boolean corretto) {
		this.textInput = textInput;
		this.corretto = corretto;
	}

	public String getTextInput() {
		return textInput;
	}

	public void setTextInput(String textInput) {
		this.textInput = textInput;
	}

	public boolean isCorretto() {
		return corretto;
	}

	public void setCorretto(boolean corretto) {
		this.corretto = corretto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (corretto ? 1231 : 1237);
		result = prime * result + ((textInput == null) ? 0 : textInput.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichWord other = (RichWord) obj;
		if (corretto != other.corretto)
			return false;
		if (textInput == null) {
			if (other.textInput != null)
				return false;
		} else if (!textInput.equals(other.textInput))
			return false;
		return true;
	}

		
}
