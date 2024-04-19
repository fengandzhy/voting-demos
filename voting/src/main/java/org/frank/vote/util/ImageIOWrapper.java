package org.frank.vote.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public interface ImageIOWrapper {
    void writeImage(BufferedImage image, String formatName, OutputStream output) throws IOException;
}
