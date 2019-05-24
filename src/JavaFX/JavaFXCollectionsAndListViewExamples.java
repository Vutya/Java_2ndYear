package javaFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class JavaFXCollectionsAndListViewExamples extends Application {
    private ListView<Integer> listView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Примеры коллекций и ListView");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        initData();
    }

    private void initData() {
        // ListView отображает данные, хранящиеся в списке.
        // Это может быть список любых объектов: String, File, Image, Student
        // В ListView описывается, как отображать отдельные объекты.
        // Например, отобразить String - это просто написать текст
        // Отобразить Integer - превратить его в String и написать
        // Отобразить Image - нарисовать изображение, можно дополнительно написать
        // формат, размер изображения.
        // Данные для ListView хранятся в структуре данных, похожей на List<>,
        // но обычный List<> был бы неудобен. Он не сообщает о том, что в нём
        // что-то изменилось. В javaFX используем ObservableList, он генерирует
        // событие на любое изменение своего содержимого. ListView подпишется
        // на эти события, и после этого любое изменение содержимого списка будет
        // сразу видно на экране.

        // Создать такой список. Вспомогательный класс FXCollections
        ObservableList<Integer> nums =
                FXCollections.observableArrayList(10, 20, 30);
        listView.setItems(nums);
        // Дописываем число уже после того, как назначали список в ListView
        nums.add(42);
        // И всё равно будет видно 42

        // Чаще всего мы будем делать observableArrayList, потому что это список, похожий
        // на обычный ArrayList, он все данные хранит в памяти, их можно легко добавлять и удалять.

        // Продемонстрируем, как можно создать список, который не хранит данные в памяти.
        // В принципе, это же можно было бы делать и без javaFX с обычными списками,
        // но тут получается наглядней.
        // Этот список будет хранить 1000 чисел от 1 до 1000.
        nums = new ObservableListBase<Integer>() {
            @Override
            public Integer get(int index) {
                return index + 1;
            }

            @Override
            public int size() {
                return 1000;
            }
        };

        listView.setItems(nums);
    }

    private Parent initInterface() {
        listView = new ListView<>();

        // Давайте теперь настроим отображение, т.е. научим ListView показывать
        // элементы как-нибудь нестандартно. Сейчас он вызывает toString для всех объектов
        // и пишет полученный текст на экран.
        listView.setCellFactory(// установим "фабрику ячеек"
                // Фабрике даётся ListView (у нас lv == ListView)
                // А вернуть надо "ячейку", т.е. такой элемент интерфейса, который
                // отображает один элемент списка. ListCell устроен как Button и Label.
                // У всех трёх элементов интерфейся есть свойство text и свойство image -
                // изображене, которые показываются обычно слева от текста.
                (lv) -> new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        //переопределяем метод, который настраивает отображение
                        //ячейки. В начале вызываем этот же метод базового класса
                        super.updateItem(item, empty);

                        // Важно обеспечить правильное отображение ячейки, если она пустая.
                        // Т.е. если на эту ячейку не хватило элементов.
                        if (empty)
                            setText("");
                        else {
                            // То, что нужно отобразить, хранится в переменной item
                            String i = Integer.toString(item);
                            String oddity = item % 2 == 0 ? "чёт" : "нечёт";
                            setText(i + " " + oddity);
                        }
                    }
                }
        );

        return listView;
    }


}
