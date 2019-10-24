class Player{
    public enum type {
        easy,
        medium,
        hard,
        user
    }

    static type typeFromString(String str){
        type result;
        switch(str) {
            case "easy":
                return type.easy;
            case "medium":
                return type.medium;
            case "hard":
                return type.hard;
            case "user":
                return type.user;
            default:
                return null;
        }

    }
}