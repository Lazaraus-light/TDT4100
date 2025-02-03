package oving1;
public class LineEditor {

    private String text;
    private int insertionIndex;

    public int getInsertionIndex() {
        return insertionIndex;
    }
    
    public LineEditor() {
        this.text = "";
        this.insertionIndex = 0;
    }

    public LineEditor(String text, int insertionIndex) {
        this.text = text;
        setInsertionIndex(insertionIndex);
    }

    public void left() {
        if (insertionIndex > 0) {
            insertionIndex--;
        }
    }

/*
    public void left() {
        if (insertionIndex > 0) {
            insertionIndex--;
        }
    }
    får feil på koden om denne delen ikke er tilstede når den kjøres
*/
    public void right() {
        if (insertionIndex < text.length()) {
            insertionIndex++;
        }
    }
    
    public void insertString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        text = text.substring(0, insertionIndex) + s + text.substring(insertionIndex);
        insertionIndex += s.length();
    }
    public void deleteLeft() {
        if (insertionIndex > 0) {
            text = text.substring(0, insertionIndex - 1) + text.substring(insertionIndex);
            insertionIndex--;
        }
    }
    public void deleteRight() {
        if (insertionIndex < text.length()) {
            text = text.substring(0, insertionIndex) + text.substring(insertionIndex + 1);
        }
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }
        this.text = text;
        if (insertionIndex > text.length()) {
            insertionIndex = text.length();
        }
    }
    public void setInsertionIndex(int insertionIndex) {
        if (insertionIndex < 0 || insertionIndex > text.length()) {
            throw new IllegalArgumentException("Insertion index out of bounds.");
        }
        this.insertionIndex = insertionIndex;
    }
    @Override
    public String toString() {
    return text.substring(0, insertionIndex) + "|" + text.substring(insertionIndex);
    }

    public static void main(String[] args) {
        LineEditor editor = new LineEditor("Hello World", 5);
        System.out.println(editor);
    
        editor.left();
        System.out.println(editor);
    
        editor.insertString("s");
        System.out.println(editor);
    
        editor.right();
        System.out.println(editor);
    
        editor.deleteLeft();
        System.out.println(editor);
    
        editor.deleteRight();
        System.out.println(editor);
    
        editor.setInsertionIndex(0);
        System.out.println(editor);
    
        editor.setText("New Text");
        System.out.println(editor);
    
        editor.insertString("Start ");
        System.out.println(editor);
    }
    
}