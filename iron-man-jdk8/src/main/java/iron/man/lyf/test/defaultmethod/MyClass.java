package iron.man.lyf.test.defaultmethod;

/**
 * 如果继承的抽象类和 实现的接口中有同样的方法（接口是默认方法），那么以抽象方法为先
 *
 *
 *
 */


public class MyClass extends MyAbstractClass implements MyInterface2{

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }

    @Override
    public void myMethod() {

    }
}
