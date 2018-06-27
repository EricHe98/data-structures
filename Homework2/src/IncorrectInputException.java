public class IncorrectInputException extends Exception {
		String errorMessage;
		public IncorrectInputException(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public String toString() {
			return this.errorMessage;
		}
	}