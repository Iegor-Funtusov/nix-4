package ua.com.alevel;

public class UserDto {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserDto() {  }

    public UserDto(User user) {
        this.age = user.getAge();
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "age=" + age +
                '}';
    }
}
