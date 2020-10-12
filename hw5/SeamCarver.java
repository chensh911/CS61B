import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    /** current picture.*/
    public Picture picture() {
        return new Picture(this.picture);
    }

    /** width of current picture */
    public int width() {
        return picture.width();
    }

    /** height of current picture */
    public int height() {
        return picture.height();
    }

    /** energy of pixel at column x and row y */
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height()) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int leftx = (x - 1 + width()) % width();
        int rightx = (x + 1) % width();
        int upy = (y - 1 + height()) % height();
        int downy = (y + 1) % height();
        Color left = picture.get(leftx, y);
        Color right = picture.get(rightx, y);
        Color down = picture.get(x, downy);
        Color up = picture.get(x, upy);
        double rx = Math.pow(left.getRed() - right.getRed(), 2);
        double gx = Math.pow(left.getGreen() - right.getGreen(), 2);
        double bx = Math.pow(left.getBlue() - right.getBlue(), 2);
        double ry = Math.pow(up.getRed() - down.getRed(), 2);
        double gy = Math.pow(up.getGreen() - down.getGreen(), 2);
        double by = Math.pow(up.getBlue() - down.getBlue(), 2);
        return rx + gx + bx + ry + gy + by;
    }

    /** sequence of indices for horizontal seam */
    public int[] findHorizontalSeam() {
        double[][] e = new double[height()][width()];
        double[][] M = new double[height()][width()];
        // set e
        for (int i = 0; i < height(); i += 1) {
            for (int j = 0; j < width(); j += 1) {
                e[i][j] = energy(j, i);
            }
        }
        // set M
        for (int i = 0; i < height(); i += 1) {
            M[i][0] = e[i][0];
        }
        for (int i = 1; i < width(); i += 1) {
            for (int j = 0; j < height(); j += 1) {
                double up, left, down;
                if (j == 0) {
                    up = Double.MAX_VALUE;
                } else {
                    up = M[j - 1][i - 1];
                }
                left = M[j][i - 1];
                if (j == height() - 1) {
                    down = Double.MAX_VALUE;
                } else {
                    down = M[j + 1][i - 1];
                }
                M[j][i] = e[j][i] + Math.min(Math.min(down, left), up);
            }
        }
        int[] ret = new int[width()];
        int minIndex = -1;
        double minValue = Double.MAX_VALUE;
        for (int i = 0; i < height(); i += 1) {
            if (M[i][width() - 1] < minValue) {
                minIndex = i;
                minValue = M[i][width() - 1];
            }
        }
        ret[width() - 1] = minIndex;
        for (int i = width() - 2; i >= 0; i -= 1) {
            double down, up, left;
            if (minIndex == 0) {
                up = Double.MAX_VALUE;
            } else {
                up = M[minIndex - 1][i];
            }
            left = M[minIndex][i];
            if (i == height() - 1) {
                down = Double.MAX_VALUE;
            } else {
                down = M[minIndex + 1][i];
            }
            if (left < Math.min(up, down)) {
                ret[i] = minIndex;
            } else if (up < down) {
                minIndex -= 1;
                ret[i] = minIndex;
            } else {
                minIndex += 1;
                ret[i] = minIndex;
            }
        }
        return ret;
    }

    /** sequence of indices for vertical seam */
    public int[] findVerticalSeam() {
        double[][] e = new double[height()][width()];
        double[][] M = new double[height()][width()];
        // set e
        for (int i = 0; i < height(); i += 1) {
            for (int j = 0; j < width(); j += 1) {
                e[i][j] = energy(j, i);
            }
        }
        // set M
        for (int i = 0; i < width(); i += 1) {
            M[0][i] = e[0][i];
        }
        for (int i = 1; i < height(); i += 1) {
            for (int j = 0; j < width(); j += 1) {
                double left, up, right;
                if (j == 0) {
                    left = Double.MAX_VALUE;
                } else {
                    left = M[i - 1][j - 1];
                }
                up = M[i - 1][j];
                if (j == width() - 1) {
                    right = Double.MAX_VALUE;
                } else {
                    right = M[i - 1][j + 1];
                }
                M[i][j] = e[i][j] + Math.min(Math.min(left, right), up);
            }
        }
        int[] ret = new int[height()];
        int minIndex = 0;
        double minValue = Double.MAX_VALUE;
        for (int i = 0; i < width(); i += 1) {
            if (M[height() - 1][i] < minValue) {
                minIndex = i;
                minValue = M[height() - 1][i];
            }
        }
        ret[height() - 1] = minIndex;
        for (int i = height() - 2; i >= 0; i -= 1) {
            double left, up, right;
            if (minIndex == 0) {
                left = Double.MAX_VALUE;
            } else {
                left = M[i][minIndex - 1];
            }
            up = M[i][minIndex];
            if (i == width() - 1) {
                right = Double.MAX_VALUE;
            } else {
                right = M[i][minIndex + 1];
            }
            if (left < Math.min(up, right)) {
                minIndex -= 1;
                ret[i] = minIndex;
            } else if (up < right) {
                ret[i] = minIndex;
            } else {
                minIndex += 1;
                ret[i] = minIndex;
            }
        }
        return ret;
    }

    /** remove horizontal seam from picture */
    public void removeHorizontalSeam(int[] seam) {
        if (width() == 1) {
            return;
        }
        for (int i = 0; i < seam.length - 2; i += 1) {
            if (seam[i] > seam[i + 1]) {
                return;
            }
        }
        this.picture = SeamRemover.removeHorizontalSeam(picture, seam);
    }

    /** remove vertical seam from picture */
    public void removeVerticalSeam(int[] seam) {
        if (height() == 1) {
            return;
        }
        for (int i = 0; i < seam.length - 2; i += 1) {
            if (seam[i] > seam[i + 1]) {
                return;
            }
        }
        this.picture = SeamRemover.removeVerticalSeam(picture, seam);
    }
}

