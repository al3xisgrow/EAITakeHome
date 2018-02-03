package shared.model;

public class ContactValidation {
    public static enum Type {
        NAME, NUMBER, ADDRESS, NOTE
    }

    /** Values that I've decided are the maximum reasonable sizes for each of these fields. */
    private static final int NAME_MAX_SIZE = 40; // Longer than 40 chars you'd never write in an address book; you'd abbreviate
    private static final int PHONE_NUMBER_MAX_SIZE = 17; // Max character space would be +1 (801) 995-2656
    private static final int ADDRESS_MAX_SIZE = 100; // This way you can include address details if necessary
    private static final int NOTE_MAX_SIZE = 500;

    public boolean isRealisticSize(String val, Type type) throws DataValidationException {
        switch(type){
            case NAME:
                if(val.length() > NAME_MAX_SIZE) {
                    throw new DataValidationException(DataValidationException.NOT_REALISTIC + NAME_MAX_SIZE);
                }
                break;
            case NUMBER:
                if(val.length() > PHONE_NUMBER_MAX_SIZE) {
                    throw new DataValidationException(DataValidationException.NOT_REALISTIC + PHONE_NUMBER_MAX_SIZE);
                }
                break;
            case ADDRESS:
                if(val.length() > ADDRESS_MAX_SIZE) {
                    throw new DataValidationException(DataValidationException.NOT_REALISTIC + ADDRESS_MAX_SIZE);
                }
                break;
            case NOTE:
                if(val.length() > NOTE_MAX_SIZE) {
                    throw new DataValidationException(DataValidationException.NOT_REALISTIC + NOTE_MAX_SIZE);
                }
                break;
        }
        return true;

    }
}
