package gb.builder;


import gb.domain.Person;

public class PersonBuilder {

    private final Person person;

    public PersonBuilder() {
        person = new Person();
    }

    public PersonBuilder withFirstName(String firstName) {
        person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        person.setLastName(lastName);
        return this;
    }

    public PersonBuilder withMiddleName(String middleName) {
        person.setMiddleName(middleName);
        return this;
    }

    public PersonBuilder withCountry(String country) {
        person.setCountry(country);
        return this;
    }

    public PersonBuilder withAddress(String address) {
        person.setAddress(address);
        return this;
    }

    public PersonBuilder withPhone(String phone) {
        person.setPhone(phone);
        return this;
    }

    public PersonBuilder withAge(int age) {
        person.setAge(age);
        return this;
    }

    public PersonBuilder withGender(String gender) {
        person.setGender(gender);
        return this;
    }

    public Person build() {
        return person;
    }
}
