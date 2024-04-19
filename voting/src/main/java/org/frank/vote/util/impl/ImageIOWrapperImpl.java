package org.frank.vote.util.impl;

import org.frank.vote.util.ImageIOWrapper;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageIOWrapperImpl implements ImageIOWrapper {
    @Override
    public void writeImage(BufferedImage image, String formatName, OutputStream output) throws IOException {
        ImageIO.write(image, formatName, output);
    }
}
