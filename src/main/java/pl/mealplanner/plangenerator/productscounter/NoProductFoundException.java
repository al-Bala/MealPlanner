package pl.mealplanner.plangenerator.productscounter;

class NoProductFoundException extends RuntimeException{
    NoProductFoundException(String message){
        super(message);
    }
}
