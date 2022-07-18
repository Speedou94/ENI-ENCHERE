package fr.eni.encheres.groupe_2.bll;

public class BuissnessException extends Exception{
    public BuissnessException() {
        super();
    }

    public BuissnessException(int code) {
        super(String.valueOf(code));
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
