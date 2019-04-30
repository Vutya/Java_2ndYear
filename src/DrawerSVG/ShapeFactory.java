package DrawerSVG;

public class ShapeFactory {
    public Shape create(String shapeName) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class<?> shapeClass = Class.forName(shapeName);
        Object s = shapeClass.newInstance();
        return (Shape) s;
    }
}
