package controller;

public class OptionsController {
    private static OptionsController instance;

    public static OptionsController getInstance(){
        if (instance == null){
            instance = new OptionsController();
        }
        return instance;
    }
}
