package ua.com.alevel;

public class CreateQuery<E extends BaseClass> {

    public String findAll(E entity) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT FROM ");

        if (entity.getClass().isAssignableFrom(User.class)) {
            stringBuilder.append("User");
        }
        if (entity.getClass().isAssignableFrom(Profile.class)) {
            stringBuilder.append("Profile");
        }

        return stringBuilder.toString();
    }
}
