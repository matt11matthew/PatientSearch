package me.matthewe.patientsearch;

public class YesNoValue { //Class that allows me to exclude searching certain elements
    private boolean value;
    private boolean unset;

    public YesNoValue setValue(boolean value) {
        this.value = value;
        unset=false;
        return this;
    }

    @Override
    public String toString() {
        return "YesNoValue{" +
                "value=" + value +
                ", unset=" + unset +
                '}';
    }

    public YesNoValue(boolean value, boolean unset) {
        this.value = value;
        this.unset = unset;
    }

    public boolean getValue() {
        return value;
    }

    public boolean isUnset() {
        return unset;
    }

    public boolean matches(boolean v) {
        return !unset&&value==v;
    }
}
