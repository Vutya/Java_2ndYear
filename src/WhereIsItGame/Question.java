package whereIsItGame;

class Question {
    private String question;
    private int x;
    private int y;
    private int width;
    private int height;

    Question(String q, int x, int y, int width, int height) {
        this.question = q;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    String getQuestion() {
        return this.question;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }
}
