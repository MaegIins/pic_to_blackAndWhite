import javax.swing.ImageIcon;

public class Chunk {
    private ImageIcon img;
    private int row;
    private int col;

    public Chunk(ImageIcon image, int row, int col) {
        this.img = image;
        this.row = row;
        this.col = col;
    }

    public void setImg(ImageIcon image) {
        this.img = image;
    }

    public ImageIcon getImg() {
        return this.img;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
