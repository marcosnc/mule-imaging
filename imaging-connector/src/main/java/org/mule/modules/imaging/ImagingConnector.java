package org.mule.modules.imaging;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Payload;

@Connector(name="imaging", friendlyName="Imaging")
public class ImagingConnector {

    @Processor
    public BufferedImage read(@Default("#[payload]") InputStream source) throws ImagingException {
    	try {
			BufferedImage image = Imaging.getBufferedImage(source);
			return image;
		} catch (Exception e) {
			throw new ImagingException("Can not read image.", e);
		}
    }
    
    @Processor(name="info-file-name", friendlyName="info-file-name")
    public ImageInfo infoFileName(String fileName) throws ImagingException {
    	try {
    		File file = new File(fileName);
    		System.out.println("Reading Info from: " + file.getAbsolutePath());
			return Imaging.getImageInfo(file);
		} catch (Exception e) {
			throw new ImagingException("Can not get info from image.", e);
		}
    }
    
    @Processor
    public InputStream write(@Default("#[payload]") BufferedImage source, @Default("JPEG") String format) throws ImagingException {
    	try {
        	ImageFormats imageFormat = ImageFormats.valueOf(format);
        	
//        	PipedOutputStream buffer = new PipedOutputStream();
//        	PipedInputStream target = new PipedInputStream(buffer);
//			Imaging.writeImage(source, buffer, imageFormat, null);
        	
        	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			Imaging.writeImage(source, buffer, imageFormat, null);
			InputStream target = new ByteArrayInputStream(buffer.toByteArray());

	    	return target;
		} catch (Exception e) {
			throw new ImagingException("Can not write image.", e);
		}
    }

    @Processor
    public String dimensions(@Default("#[payload]") BufferedImage source) {
    	return String.format("%d x %d", source.getWidth(), source.getHeight());
    }
    
    @Processor
    public BufferedImage grayScale(@Default("#[payload]") BufferedImage source) {
    	BufferedImage target = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
    	ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(source, target);
        return target;
    }

}
