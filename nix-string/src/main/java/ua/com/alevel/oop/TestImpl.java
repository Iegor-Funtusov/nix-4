package ua.com.alevel.oop;

public class TestImpl extends Test {


    public CarService carService = new CarServiceimpl();


    @Override
    public B getA() {
        carService.add(new Car());
        carService.add(new Mers());
        return null;
    }

    public static void test() {
        new CarServiceimpl();
    }

    @Override
    public B getB() {
        return null;
    }

    @Override
    public C getC() {
        return null;
    }

    @Override
    public void setA(A a) {

    }

    //    @Override
    public void setA(B a) {

    }

//    @Override
//    public void setB(B b) {
//        super.setB(b);
//    }
//
//    @Override
//    public void setC(C c) {
//        super.setC(c);
//    }

    public TestImpl(B a) {
        super(a);
    }

//    public TestImpl(B b) {
//        super(b);
//    }

//    public TestImpl(C c) {
//        super(c);
//    }
}
