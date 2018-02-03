package shared.model;



public class Contact {
    private String name;
    private String phoneNumber;
    private String address;
    private String note;
    private ContactValidation validator;


    public Contact() {
        this.name = "";
        this.phoneNumber = "";
        this.address = "";
        this.note = "";
        validator = new ContactValidation();
    }

    public Contact(String name, String phoneNumber, String address, String note) throws DataValidationException {
        validator = new ContactValidation();
        setName(name);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setNote(note);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DataValidationException {
        if(validator.isRealisticSize(name, ContactValidation.Type.NAME)) {
            this.name = name;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws DataValidationException {
        if(validator.isRealisticSize(phoneNumber, ContactValidation.Type.NUMBER)) {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws DataValidationException {
        if(validator.isRealisticSize(address, ContactValidation.Type.ADDRESS)) {
            this.address = address;

        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) throws DataValidationException {
        if(validator.isRealisticSize(note, ContactValidation.Type.NOTE)) {
            this.note = note;
        }
    }



    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("\tName: " + getName() +
                "\n\tPhone Number: " + getPhoneNumber() +
                "\n\tAddress: " + getAddress() +
                "\n\tNotes: " + getNote() + "\n");
        return output.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        if(obj.getClass() != this.getClass()) {
            return false;
        }

        Contact that = (Contact) obj;
        if(that.getName().equals(this.getName())
                && that.getAddress().equals(this.getAddress())
                && that.getNote().equals(this.getNote())
                && (that.getPhoneNumber().equals(this.getPhoneNumber()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 50 + (this.getName().length() + this.getNote().length()) * (this.getAddress().hashCode()  - this.getPhoneNumber().hashCode());
    }


}
