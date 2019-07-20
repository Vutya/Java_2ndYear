public interface StringTransformer {
    String transform(String s);

    default String transform(String s, int times) {
        /* if (times == 1)
            return transform(s);
        else if (times > 1)
            return transform(transform(s), times - 1);
        else
            return ""; */
        if (times < 1)
            return s;
        else {
            for (int i = 0; i < times; i++)
                s = transform(s);
            return s;
        }
    }
}
